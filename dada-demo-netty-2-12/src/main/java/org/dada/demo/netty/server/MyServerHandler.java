package org.dada.demo.netty.server;

import io.netty.channel.*;
import org.dada.demo.netty.server.common.MyServerCommonHandler;

import java.util.function.Consumer;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔 | 欢迎关注并获取专题&源码
 * Create by fuzhengwei on 2019
 */
public class MyServerHandler extends MyServerCommonHandler {

    @Override
    protected void sendData(ChannelHandlerContext ctx) {
        sentFlag = true;
        ctx.writeAndFlush( "111111111122222222223333333333\r\n", getChannelProgressivePromise(ctx, new Consumer<ChannelProgressiveFuture>() {
            @Override
            public void accept(ChannelProgressiveFuture channelProgressiveFuture) {
                if (ctx.channel().isWritable() && !sentFlag) {
                    sendData(ctx);
                }
            }
        }));
    }

}
