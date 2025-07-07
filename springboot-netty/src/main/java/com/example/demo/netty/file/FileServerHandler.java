package com.example.demo.netty.file;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2025, zaxh Group All Rights Reserved.
 * @since: 2025/07/07/17:28
 * @summary:
 */
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final String FILE_PATH = "md/"; // 上传文件保存路径

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {

        System.out.println("Received request: " + request.uri() + " with method: " + request.method());
        // 处理文件上传
        String uri = request.uri();

        if ("/upload".equals(uri) && request.method() == HttpMethod.POST) {
            handleFileUpload(ctx, request);
        } else {
            sendError(ctx, HttpResponseStatus.NOT_FOUND);
        }
    }

    private void handleFileUpload(ChannelHandlerContext ctx, FullHttpRequest request) throws IOException {
        // 获取请求内容
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);

        // 解析文件数据
        InterfaceHttpData data = decoder.getBodyHttpData("file"); // 假设字段名为 "file"

        if (data instanceof FileUpload) {
            FileUpload fileData = (FileUpload) data;
            saveFile(fileData); // 直接保存文件
        }
        sendResponse(ctx, "File uploaded successfully");
    }

    private void saveFile(FileUpload data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(FILE_PATH+data.getFilename()))) {
            fos.write(data.get());
        }
        System.out.println("File saved: " + FILE_PATH);
    }

    private void sendResponse(ChannelHandlerContext ctx, String message) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(message.getBytes()));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer("Error: " + status.toString(), io.netty.util.CharsetUtil.UTF_8));
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

}
