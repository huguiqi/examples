package com.example.demo.netty.car;

import com.example.demo.bean.Car;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @version v1
 * @Author: sam.hu (huguiqi@zaxh.cn)
 * @Copyright (c) 2025, zaxh Group All Rights Reserved.
 * @since: 2025/07/02/17:16
 * @summary:
 */
public class CarEncoder extends MessageToByteEncoder<Car> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Car car, ByteBuf byteBuf) throws Exception {
        System.out.println("收到："+car.getModel());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(car);
        byteBuf.writeBytes(byteArrayOutputStream.toByteArray());
    }
}
