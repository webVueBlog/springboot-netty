package org.dada.demo.netty.aio.client;

import org.dada.demo.netty.aio.ChannelAdapter;
import org.dada.demo.netty.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

public class AioClientHandler extends ChannelAdapter {

    // 构造方法，接收客户端的异步Socket通道和字符集编码
    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) {
        super(channel, charset);
    }

    // 当通道激活时（即连接建立时）被调用
    @Override
    public void channelActive(ChannelHandler ctx) {
        try {
            // 输出连接报告信息，打印客户端的远程地址
            System.out.println(" | 链接报告信息:" + ctx.channel().getRemoteAddress());
            // 可以在这里通知客户端连接已经成功建立
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 当通道不活跃时（即连接断开时）被调用
    @Override
    public void channelInactive(ChannelHandler ctx) {
        // 可以在这里处理连接断开后的逻辑
    }

    // 当通道中有数据可读时被调用
    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        // 打印服务端收到的信息，带上当前时间戳
        System.out.println(" | 服务端收到：" + new Date() + " " + msg + "\r\n");

        // 向服务器发送响应消息，表示客户端信息处理成功
        ctx.writeAndFlush("客户端信息处理Success！\r\n");
    }
}
