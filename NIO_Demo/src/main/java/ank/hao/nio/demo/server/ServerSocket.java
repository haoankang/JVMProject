package ank.hao.nio.demo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class ServerSocket {

    public static void start(){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9911));
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, (SelectionKey.OP_ACCEPT));

            while(true){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    if(selectionKey.isAcceptable()){
                        System.out.println("server .. accept");
                    } else if(selectionKey.isConnectable()){
                        System.out.println("server .. connect");
                    } else if(selectionKey.isReadable()){
                        System.out.println("server .. read");
                        new Thread(new ServerHandler(selectionKey)).start();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
