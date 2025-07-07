package com.example.demo.netty.file;

import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2025, zaxh Group All Rights Reserved.
 * @since: 2025/07/07/17:26
 * @summary:
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class NettyFileServer {

    private final int port = 8888; // 端口可以根据需要调整

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new HttpObjectAggregator(10 * 1024 * 1024)); // 10MB
                            ch.pipeline().addLast(new FileServerHandler()); // 添加处理器
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 12)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 启动服务器
            b.bind(port).sync();
            System.out.println("Netty server started on port: " + port);
        } finally {
            // 关闭线程池
            // bossGroup.shutdownGracefully();
            // workerGroup.shutdownGracefully();
        }
    }
}
