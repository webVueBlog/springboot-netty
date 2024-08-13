package org.dada.demo.netty.aio.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

public class AioClient {

    public static void main(String[] args) throws Exception {
        // 创建一个异步的Socket通道，用于与服务器进行通信
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();

        // 发起异步连接到指定的服务器地址和端口，并返回一个Future对象以跟踪连接状态
        Future<Void> future = socketChannel.connect(new InetSocketAddress("192.168.1.116", 7397));
        System.out.println("dada-demo-netty aio client start done.");

        // 调用future.get()阻塞当前线程，直到连接完成或发生异常
        future.get();

        // 异步读取服务器发送的数据，分配一个1024字节的缓冲区，并指定处理数据的编码格式为GBK
        socketChannel.read(ByteBuffer.allocate(1024), null, new AioClientHandler(socketChannel, Charset.forName("GBK")));

        // 主线程休眠100秒，以确保客户端不会立即退出，保持连接
        Thread.sleep(100000);
    }

}
