package org.dada.demo.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

public abstract class ChannelAdapter extends Thread {

    private Socket socket; // 用于与客户端或服务器进行通信的Socket
    private ChannelHandler channelHandler; // 用于处理消息的ChannelHandler
    private Charset charset; // 指定的字符集，用于解码读取的消息

    // 构造方法，初始化Socket和Charset
    public ChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;

        // 等待Socket连接成功
        while (!socket.isConnected()) {
            break;
        }

        // 创建ChannelHandler实例，用于处理消息
        channelHandler = new ChannelHandler(this.socket, charset);

        // 通知连接已建立，调用抽象方法channelActive
        channelActive(channelHandler);
    }

    @Override
    public void run() {
        try {
            // 创建BufferedReader以读取来自Socket输入流的消息
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), charset));
            String str;

            // 持续读取消息，直到Socket关闭或发生异常
            while ((str = input.readLine()) != null) {
                // 调用抽象方法channelRead处理每一条读取到的消息
                channelRead(channelHandler, str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 抽象方法，连接建立时被调用，子类实现具体逻辑
    public abstract void channelActive(ChannelHandler ctx);

    // 抽象方法，读取到消息时被调用，子类实现具体逻辑
    public abstract void channelRead(ChannelHandler ctx, Object msg);

}
