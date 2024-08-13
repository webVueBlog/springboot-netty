package org.dada.demo.netty.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ChannelHandler {

    // SocketChannel 用于与远程端进行通信
    private SocketChannel channel;
    // Charset 用于处理字符编码
    private Charset charset;

    // 构造方法，初始化SocketChannel和Charset
    public ChannelHandler(SocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
    }

    // 将消息写入SocketChannel并立即发送
    public void writeAndFlush(Object msg) {
        try {
            // 将消息转换为字节数组
            byte[] bytes = msg.toString().getBytes(charset);
            // 分配一个与字节数组长度相等的ByteBuffer
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            // 将字节数组放入缓冲区
            writeBuffer.put(bytes);
            // 翻转缓冲区，准备写入通道
            writeBuffer.flip();
            // 将缓冲区的数据写入通道
            channel.write(writeBuffer);
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印IO异常
        }
    }

    // 获取当前的SocketChannel实例
    public SocketChannel channel() {
        return channel;
    }

}
