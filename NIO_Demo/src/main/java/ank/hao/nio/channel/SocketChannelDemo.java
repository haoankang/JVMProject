package ank.hao.nio.channel;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        new Thread(new Runnable() {
            public void run() {
                ServerSocketChannel serverSocketChannel = null;
                try {
                    serverSocketChannel = ServerSocketChannel.open();
                    serverSocketChannel.socket().bind(new InetSocketAddress(8122));
                    ByteBuffer byteBuffer = ByteBuffer.allocate(20);
                    while (true){
                        SocketChannel socketChannel1 = serverSocketChannel.accept();
                        socketChannel1.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()));
                        byteBuffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        serverSocketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(8122));

            InputStream is = System.in;
            byte[] bytes = new byte[20];
            int m = is.read(bytes);
            while(m>0){
                ByteBuffer buffer = ByteBuffer.wrap(bytes,0, m);
                socketChannel.write(buffer);
                buffer.clear();
                is.read(bytes);
                m = is.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            try {
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
