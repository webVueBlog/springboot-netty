package org.dada.demo.netty.bio.client;

import org.dada.demo.netty.bio.ChannelAdapter;
import org.dada.demo.netty.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BioClientHandler extends ChannelAdapter {

    // 构造方法，初始化Socket和Charset
    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    // 当通道激活时（即连接建立时）被调用
    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        // 向服务器发送一条消息，通知连接已建立
        ctx.writeAndFlush("hi! BioClient to msg for you \r\n");
    }

    // 当通道中有数据可读时被调用
    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        // 打印从服务器接收到的消息，并附上当前时间戳
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        // 向服务器发送确认消息，表示已经收到消息
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }
}
