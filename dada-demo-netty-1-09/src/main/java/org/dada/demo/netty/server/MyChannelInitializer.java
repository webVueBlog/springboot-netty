package org.dada.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.dada.demo.netty.codec.MyDecoder;
import org.dada.demo.netty.codec.MyEncoder;


public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //自定义解码器
        channel.pipeline().addLast(new MyDecoder());
        //自定义编码器
        channel.pipeline().addLast(new MyEncoder());
        //在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyServerHandler());
    }

}
