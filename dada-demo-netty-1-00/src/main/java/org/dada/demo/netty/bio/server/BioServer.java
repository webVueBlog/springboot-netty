package org.dada.demo.netty.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class BioServer extends Thread {

    // ServerSocket用于监听客户端的连接请求
    private ServerSocket serverSocket = null;

    // 主方法，启动BIO服务器
    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.start();
    }

    @Override
    public void run() {
        try {
            // 创建ServerSocket并绑定到指定的端口
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(7397));
            System.out.println("dada-demo-netty bio server start done.");

            // 循环等待客户端连接
            while (true) {
                // 接受客户端连接，accept()方法会阻塞，直到有客户端连接进来
                Socket socket = serverSocket.accept();

                // 每当有客户端连接进来时，创建一个新的BioServerHandler线程来处理该连接
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("GBK"));

                // 启动处理线程
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印IO异常
        }
    }
}
