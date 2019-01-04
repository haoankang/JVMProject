package ank.hao.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {

    Selector selector;

    public void start() throws IOException{
        //1. 打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 服务器配置为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 进行服务端口的绑定
        serverSocketChannel.bind(new InetSocketAddress("localhost", 9922));
        // 通过open方法找到selector
        selector = Selector.open();
        // 注册到selector，等待连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(!Thread.currentThread().isInterrupted()){
            selector.select(1000);
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(!selectionKey.isValid()){
                    continue;
                }
                if(selectionKey.isAcceptable()){
                    accept(selectionKey);
                } else if(selectionKey.isReadable()){
                    read(selectionKey);
                } else if(selectionKey.isWritable()){
                    write(selectionKey);
                }
            }
        }
    }

    private void write(SelectionKey key) throws IOException{
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        String msg = "i've receive your message,you can next.";
        writeBuffer.put(msg.getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException{
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int numRead = socketChannel.read(readBuffer);
        String msg = new String(readBuffer.array(), 0, numRead);
        System.out.println("server rece msg:"+msg);
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void accept(SelectionKey key) throws IOException{
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client accept");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("server start..");
        new Server().start();
    }
}
