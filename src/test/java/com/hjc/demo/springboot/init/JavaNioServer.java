package com.hjc.demo.springboot.init;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author : Administrator
 * @date : 2019/1/17 0017 14:38
 * @description : nio服务端
 */
public class JavaNioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress(9999);
        serverSocketChannel.bind(addr);
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        System.out.println(byteBuffer.hasRemaining());
        System.out.println("解析："+Charset.forName("UTF-8").decode(byteBuffer).asReadOnlyBuffer().toString());
        System.out.println("测试");
    }
}
