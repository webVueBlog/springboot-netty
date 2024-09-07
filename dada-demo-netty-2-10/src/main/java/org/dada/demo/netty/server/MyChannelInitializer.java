package org.dada.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.dada.demo.netty.server.handler.MsgDemo01Handler;
import org.dada.demo.netty.server.handler.MsgDemo02Handler;
import org.dada.demo.netty.server.handler.MsgDemo03Handler;
import org.dada.demo.netty.codec.ObjDecoder;
import org.dada.demo.netty.codec.ObjEncoder;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛关注获取学习源码｝
 * Create by fuzhengwei on 2019
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        //对象传输处理[解码]
        channel.pipeline().addLast(new ObjDecoder());
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MsgDemo01Handler());
        channel.pipeline().addLast(new MsgDemo02Handler());
        channel.pipeline().addLast(new MsgDemo03Handler());
        //对象传输处理[编码]
        channel.pipeline().addLast(new ObjEncoder());
    }

}
