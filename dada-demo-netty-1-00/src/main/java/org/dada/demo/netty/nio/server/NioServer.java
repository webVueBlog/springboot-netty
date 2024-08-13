package org.dada.demo.netty.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;

public class NioServer {

    // Selector，用于管理通道的选择操作
    private Selector selector;
    // ServerSocketChannel，用于接受客户端连接
    private ServerSocketChannel socketChannel;

    // 主方法，启动NIO服务器
    public static void main(String[] args) throws IOException {
        new NioServer().bind(7397);
    }

    // 绑定端口并启动服务器
    public void bind(int port) {
        try {
            // 打开一个Selector
            selector = Selector.open();

            // 打开一个ServerSocketChannel，用于接受客户端连接
            socketChannel = ServerSocketChannel.open();
            socketChannel.configureBlocking(false); // 设置为非阻塞模式

            // 绑定服务器到指定端口，并设置连接请求的最大队列长度为1024
            socketChannel.socket().bind(new InetSocketAddress(port), 1024);

            // 将ServerSocketChannel注册到Selector上，并监听OP_ACCEPT事件
            socketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("dada-demo-netty nio server start done.");

            // 启动NioServerHandler处理后续的I/O事件
            new NioServerHandler(selector, Charset.forName("GBK")).start();
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印IO异常
        }
    }

}
