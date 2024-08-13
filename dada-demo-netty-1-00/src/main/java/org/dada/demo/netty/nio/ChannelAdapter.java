package org.dada.demo.netty.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public abstract class ChannelAdapter extends Thread {

    private Selector selector;  // 选择器，用于检测多个通道的I/O事件

    private ChannelHandler channelHandler;  // 通道处理器，用于处理通道上的操作
    private Charset charset;  // 字符集，用于字符编码和解码

    // 构造方法，初始化选择器和字符集
    public ChannelAdapter(Selector selector, Charset charset) {
        this.selector = selector;
        this.charset = charset;
    }

    @Override
    public void run() {
        while (true) {  // 持续运行的循环，用于处理I/O事件
            try {
                selector.select(1000);  // 选择一组准备好I/O操作的通道，超时为1000毫秒
                Set<SelectionKey> selectedKeys = selector.selectedKeys();  // 获取选择的键集
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();  // 从集合中移除已处理的键
                    handleInput(key);  // 处理输入事件
                }
            } catch (Exception ignore) {
                // 忽略异常，保持循环运行
            }
        }
    }

    // 处理输入事件
    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {  // 检查键是否有效
            return;
        }

        // 处理客户端SocketChannel
        Class<?> superclass = key.channel().getClass().getSuperclass();
        if (superclass == SocketChannel.class) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            if (key.isConnectable()) {  // 如果通道处于连接状态
                if (socketChannel.finishConnect()) {  // 完成连接
                    channelHandler = new ChannelHandler(socketChannel, charset);  // 初始化通道处理器
                    channelActive(channelHandler);  // 通知连接已建立
                    socketChannel.register(selector, SelectionKey.OP_READ);  // 注册读操作
                } else {
                    System.exit(1);  // 连接失败，退出程序
                }
            }
        }

        // 处理服务端ServerSocketChannel
        if (superclass == ServerSocketChannel.class) {
            if (key.isAcceptable()) {  // 如果通道处于可接受状态
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();  // 接受客户端连接
                socketChannel.configureBlocking(false);  // 设置非阻塞模式
                socketChannel.register(selector, SelectionKey.OP_READ);  // 注册读操作

                channelHandler = new ChannelHandler(socketChannel, charset);  // 初始化通道处理器
                channelActive(channelHandler);  // 通知连接已建立
            }
        }

        // 处理读操作
        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);  // 分配1024字节的缓冲区
            int readBytes = socketChannel.read(readBuffer);  // 读取数据到缓冲区
            if (readBytes > 0) {
                readBuffer.flip();  // 切换为读模式
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);  // 从缓冲区读取字节数组
                channelRead(channelHandler, new String(bytes, charset));  // 调用读取消息的抽象方法
            } else if (readBytes < 0) {
                key.cancel();  // 取消选择键
                socketChannel.close();  // 关闭通道
            }
        }
    }

    // 链接通知抽象方法，需由子类实现
    public abstract void channelActive(ChannelHandler ctx);

    // 读取消息抽象方法，需由子类实现
    public abstract void channelRead(ChannelHandler ctx, Object msg);

}
