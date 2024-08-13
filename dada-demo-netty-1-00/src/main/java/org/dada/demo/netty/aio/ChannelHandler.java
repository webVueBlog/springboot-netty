package org.dada.demo.netty.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class ChannelHandler {

    // 异步Socket通道，用于与远程端进行通信
    private AsynchronousSocketChannel channel;
    // 字符集，用于对字节数据进行编码和解码
    private Charset charset;

    // 构造方法，初始化通道和字符集
    public ChannelHandler(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
    }

    // 将消息写入通道并刷新
    public void writeAndFlush(Object msg) {
        // 将消息转换为字节数组
        byte[] bytes = msg.toString().getBytes(charset);
        // 分配一个与字节数组长度相等的缓冲区
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        // 将字节数组放入缓冲区
        writeBuffer.put(bytes);
        // 翻转缓冲区，准备写入通道
        writeBuffer.flip();
        // 将缓冲区中的数据写入通道
        channel.write(writeBuffer);
    }

    // 获取当前通道
    public AsynchronousSocketChannel channel() {
        return channel;
    }

    // 设置通道
    public void setChannel(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }
}
