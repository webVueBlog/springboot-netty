package org.dada.demo.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.dada.demo.netty.domain.MsgInfo;
import org.dada.demo.netty.codec.ObjDecoder;
import org.dada.demo.netty.codec.ObjEncoder;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛关注获取学习源码｝
 * Create by fuzhengwei on 2019
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        //对象传输处理
        channel.pipeline().addLast(new ObjDecoder(MsgInfo.class));
        channel.pipeline().addLast(new ObjEncoder(MsgInfo.class));
        // 在管道中添加我们自己的接收数据实现方法
        channel.pipeline().addLast(new MyClientHandler());
    }

}
