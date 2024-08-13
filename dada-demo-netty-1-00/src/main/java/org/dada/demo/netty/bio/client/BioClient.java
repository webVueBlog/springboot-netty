package org.dada.demo.netty.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

public class BioClient {

    public static void main(String[] args) {
        try {
            // 创建一个连接到指定IP地址和端口的Socket
            Socket socket = new Socket("192.168.1.116", 7397);
            System.out.println("dada-demo-netty bio client start done.");

            // 创建一个BioClientHandler实例，用于处理与服务器的通信
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));

            // 启动BioClientHandler，开始处理与服务器的交互
            bioClientHandler.start();
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印IO异常
        }
    }

}
