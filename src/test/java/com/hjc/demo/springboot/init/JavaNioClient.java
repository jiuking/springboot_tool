package com.hjc.demo.springboot.init;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author : Administrator
 * @date : 2019/1/17 0017 14:37
 * @description : nio客户端 需要重启线程执行处理过程
 */
public class JavaNioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 9999));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("测试123".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }
}
