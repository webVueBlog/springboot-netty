package org.dada.demo.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.*;

public class AioServerTest {

    public final static int PORT = 7397;  // 服务器监听端口
    private AsynchronousServerSocketChannel server;  // 异步服务器Socket通道

    // 构造方法，初始化服务器
    public AioServerTest() throws IOException {
        // 创建一个异步服务器Socket通道，并绑定到指定端口
        server = AsynchronousServerSocketChannel.open(
                        AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(), 10))
                .bind(new InetSocketAddress(PORT));
    }

    // 使用Future模式接收和处理客户端请求
    public void startWithFuture() throws InterruptedException, ExecutionException, TimeoutException {
        while (true) {  // 循环接收客户端请求
            Future<AsynchronousSocketChannel> future = server.accept();  // 接收客户端连接
            AsynchronousSocketChannel socket = future.get();  // 阻塞等待，直到接收到一个连接
            handleWithFuture(socket);  // 处理客户端连接
        }
    }

    // 使用Future模式处理客户端请求
    public void handleWithFuture(AsynchronousSocketChannel channel) throws InterruptedException, ExecutionException, TimeoutException {
        ByteBuffer readBuf = ByteBuffer.allocate(1024);  // 分配缓冲区
        readBuf.clear();

        while (true) {  // 循环读取客户端数据
            // 读取数据，并设置超时时间为10秒，防止DOS攻击
            Integer integer = channel.read(readBuf).get(10, TimeUnit.SECONDS);
            System.out.println("read: " + integer);
            if (integer == -1) {  // 客户端关闭连接
                break;
            }
            readBuf.flip();  // 切换为读模式
            System.out.println("received: " + Charset.forName("UTF-8").decode(readBuf));  // 打印接收到的数据
            readBuf.clear();  // 清空缓冲区
        }
    }

    // 使用CompletionHandler模式接收和处理客户端请求
    public void startWithCompletionHandler() {
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            // 接收客户端连接成功后的回调
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                server.accept(null, this);  // 再次接收客户端连接
                handleWithCompletionHandler(result);  // 处理客户端连接
            }

            // 接收客户端连接失败后的回调
            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();  // 打印异常信息
            }
        });
    }

    // 使用CompletionHandler模式处理客户端请求
    public void handleWithCompletionHandler(final AsynchronousSocketChannel channel) {
        try {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);  // 分配缓冲区
            final long timeout = 10L;  // 超时时间为10秒
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {
                // 读取数据成功后的回调
                @Override
                public void completed(Integer result, Object attachment) {
                    System.out.println("read:" + result);
                    if (result == -1) {  // 客户端关闭连接
                        try {
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();  // 切换为读模式
                    System.out.println("received message:" + Charset.forName("GBK").decode(buffer));  // 打印接收到的数据
                    buffer.clear();  // 清空缓冲区
                    channel.read(buffer, timeout, TimeUnit.SECONDS, null, this);  // 继续读取数据
                }

                // 读取数据失败后的回调
                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();  // 打印异常信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        // 使用CompletionHandler模式启动服务器
        new AioServerTest().startWithCompletionHandler();
        Thread.sleep(100000);  // 让主线程保持运行，以便处理客户端请求
    }
}
