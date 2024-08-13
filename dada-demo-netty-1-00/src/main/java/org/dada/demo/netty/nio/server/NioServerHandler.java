package org.dada.demo.netty.nio.server;

import org.dada.demo.netty.nio.ChannelAdapter;
import org.dada.demo.netty.nio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NioServerHandler extends ChannelAdapter {

    // 构造方法，初始化Selector和Charset
    public NioServerHandler(Selector selector, Charset charset) {
        super(selector, charset);
    }

    // 当通道激活时（即连接建立时）被调用
    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            // 打印连接报告，显示客户端的本地地址
            System.out.println("链接报告LocalAddress:" + ctx.channel().getLocalAddress());
            // 发送消息到客户端，通知连接已建立
            ctx.writeAndFlush("hi! NioServer to msg for you \r\n");
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印异常信息
        }
    }

    // 当通道中有数据可读时被调用
    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        // 打印从客户端接收到的消息，并附上当前时间戳
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        // 发送确认消息，表示服务器已经收到客户端的消息
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }
}
