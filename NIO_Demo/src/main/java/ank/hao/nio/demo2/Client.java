package ank.hao.nio.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Client {

    public void start() throws IOException{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 9922));
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        Scanner scanner = new Scanner(System.in);
        while (true){
            selector.select(2000);
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if(key.isConnectable()){
                    socketChannel.finishConnect();
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                    System.out.println("client connected..");
                    break;
                } else if(key.isWritable()){
                    System.out.println("please input message:");
                    String message = scanner.nextLine();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                    writeBuffer.put(message.getBytes());
                    writeBuffer.flip();
                    socketChannel.write(writeBuffer);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if(key.isReadable()){
                    SocketChannel socketChannel1 = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readNum = socketChannel1.read(readBuffer);
                    readBuffer.flip();
                    System.out.println(new String(readBuffer.array(),0,readNum));
                    socketChannel1.register(selector, SelectionKey.OP_WRITE);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("client start..");
        new Client().start();
    }
}
