package org.dada.demo.netty.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NioClient {

    public static void main(String[] args) throws IOException {
        // 创建一个Selector，用于管理通道的选择操作
        Selector selector = Selector.open();

        // 打开一个SocketChannel，并配置为非阻塞模式
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        // 尝试连接服务器
        boolean isConnect = socketChannel.connect(new InetSocketAddress("192.168.1.116", 7397));
        if (isConnect) {
            // 如果连接成功，注册读事件到Selector
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            // 如果连接未成功，注册连接事件到Selector
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

        System.out.println("dada-demo-netty nio client start done.");

        // 启动NioClientHandler处理后续的IO事件
        new NioClientHandler(selector, Charset.forName("GBK")).start();
    }
}
