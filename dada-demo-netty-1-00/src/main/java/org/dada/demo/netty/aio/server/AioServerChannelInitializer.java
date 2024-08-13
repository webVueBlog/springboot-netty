package org.dada.demo.netty.aio.server;

import org.dada.demo.netty.aio.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public class AioServerChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(AsynchronousSocketChannel channel) throws Exception {
        // 分配一个1024字节的ByteBuffer作为读取数据的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 开始异步读取客户端数据，设置超时时间为10秒
        channel.read(buffer, 10, TimeUnit.SECONDS, null,
                new AioServerHandler(channel, Charset.forName("GBK")));
    }
}
