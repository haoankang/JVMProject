package ank.hao.nio.demo.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ServerHandler implements Runnable{

    SelectionKey selectionKey;

    public ServerHandler(SelectionKey selectionKey){
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int cap = socketChannel.read(byteBuffer);
            while (cap>0){
                System.out.println("rece.."+new String(byteBuffer.array(), StandardCharsets.UTF_8));
                cap = socketChannel.read(byteBuffer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
