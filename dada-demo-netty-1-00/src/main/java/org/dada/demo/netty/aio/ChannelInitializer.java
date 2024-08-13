package org.dada.demo.netty.aio;

import org.dada.demo.netty.aio.server.AioServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {

    // 当异步操作（如客户端连接）成功时调用
    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer attachment) {
        try {
            // 初始化通道，子类实现此方法
            initChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 再次调用accept方法，准备接收下一个客户端连接
            attachment.serverSocketChannel().accept(attachment, this);
        }
    }

    // 当异步操作失败时调用
    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace(); // 打印异常堆栈信息
    }

    // 抽象方法，子类实现，用于初始化通道
    protected abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;

}
