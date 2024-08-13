package org.dada.demo.netty.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class ChannelHandler {

    // 用于与客户端或服务器进行通信的Socket
    private Socket socket;
    // 指定的字符集，用于编码发送的消息
    private Charset charset;

    // 构造方法，初始化Socket和Charset
    public ChannelHandler(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
    }

    // 将消息写入Socket并立即发送
    public void writeAndFlush(Object msg) {
        OutputStream out = null;
        try {
            // 获取Socket的输出流
            out = socket.getOutputStream();
            // 将消息转换为字节数组并写入输出流
            out.write((msg.toString()).getBytes(charset));
            // 刷新输出流，确保消息立即发送
            out.flush();
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印IO异常
        }
    }

    // 获取当前的Socket实例
    public Socket socket() {
        return socket;
    }
}
