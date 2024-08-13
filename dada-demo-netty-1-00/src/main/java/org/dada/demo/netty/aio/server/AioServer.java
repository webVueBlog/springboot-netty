package org.dada.demo.netty.aio.server;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class AioServer extends Thread {

    // 异步服务器Socket通道，用于接收客户端连接
    private AsynchronousServerSocketChannel serverSocketChannel;

    @Override
    public void run() {
        try {
            // 创建一个异步服务器Socket通道，并绑定到指定的端口
            serverSocketChannel = AsynchronousServerSocketChannel.open(
                    AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10)
            );
            serverSocketChannel.bind(new InetSocketAddress(7397));
            System.out.println("dada-demo-netty aio server start done.");

            // 使用 CountDownLatch 来保持主线程存活，等待客户端连接
            CountDownLatch latch = new CountDownLatch(1);
            serverSocketChannel.accept(this, new AioServerChannelInitializer());

            // 等待，直到 CountDownLatch 的计数器减到0
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取服务器Socket通道的实例
    public AsynchronousServerSocketChannel serverSocketChannel() {
        return serverSocketChannel;
    }

    // 主方法，启动AIO服务器
    public static void main(String[] args) {
        new AioServer().start();
    }
}
