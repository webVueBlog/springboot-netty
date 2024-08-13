package org.dada.demo.netty.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

public abstract class ChannelAdapter implements CompletionHandler<Integer, Object> {

    // 异步Socket通道，用于与远程端进行通信
    private AsynchronousSocketChannel channel;
    // 字符集，用于对字节数据进行编码和解码
    private Charset charset;

    // 构造方法，初始化通道和字符集，并调用channelActive方法通知连接已建立
    public ChannelAdapter(AsynchronousSocketChannel channel, Charset charset) {
        this.channel = channel;
        this.charset = charset;
        if (channel.isOpen()) {
            // 通道打开时，调用channelActive方法，通知子类连接已建立
            channelActive(new ChannelHandler(channel, charset));
        }
    }

    // 当读取操作完成时触发此方法
    @Override
    public void completed(Integer result, Object attachment) {
        try {
            final ByteBuffer buffer = ByteBuffer.allocate(1024); // 分配一个1024字节的缓冲区
            final long timeout = 60 * 60L; // 设置超时时间为1小时
            // 开始异步读取数据
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    // 如果读取到的字节数为-1，表示连接已经关闭
                    if (result == -1) {
                        try {
                            // 调用channelInactive方法，通知子类连接已断开
                            channelInactive(new ChannelHandler(channel, charset));
                            // 关闭通道
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    // 将缓冲区翻转，为读取数据做准备
                    buffer.flip();
                    // 调用channelRead方法，处理读取到的数据
                    channelRead(new ChannelHandler(channel, charset), charset.decode(buffer));
                    // 清空缓冲区，为下一次读取做准备
                    buffer.clear();
                    // 再次开始异步读取数据
                    channel.read(buffer, timeout, TimeUnit.SECONDS, null, this);
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace(); // 读取失败时打印异常信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 当异步操作失败时触发此方法
    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.printStackTrace(); // 打印异常信息
    }

    // 抽象方法，子类实现，用于处理通道激活事件
    public abstract void channelActive(ChannelHandler ctx);

    // 抽象方法，子类实现，用于处理通道不活跃事件
    public abstract void channelInactive(ChannelHandler ctx);

    // 抽象方法，子类实现，用于处理读取到的数据
    public abstract void channelRead(ChannelHandler ctx, Object msg);

}
