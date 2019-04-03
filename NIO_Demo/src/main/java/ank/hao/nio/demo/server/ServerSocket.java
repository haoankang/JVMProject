package ank.hao.nio.demo.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

public class ServerSocket {

    public static void start(){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9911));
            serverSocketChannel.configureBlocking(false);

            final Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!Thread.currentThread().isInterrupted()){
                        try {
                            selector.select(1000);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                        while(iterator.hasNext()){
                            SelectionKey selectionKey = iterator.next();
                            iterator.remove();  //该事件已经处理，可以丢弃
                            if(selectionKey.isValid()){
                                if(selectionKey.isAcceptable()){
                                    System.out.println("server .. accept");
                                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                                    try {
                                        SocketChannel socketChannel = serverSocketChannel1.accept();
                                        socketChannel.configureBlocking(false);
                                        socketChannel.register(selector, SelectionKey.OP_READ);
                                    } catch (ClosedChannelException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else if(selectionKey.isConnectable()){
                                    System.out.println("server .. connect");
                                } else if(selectionKey.isReadable()){
                                    System.out.println("server .. read");
                                    new ServerHandler(selectionKey).run();

                                } else{
                                    System.out.println("dkjgsd");
                                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                                    try {
                                        socketChannel.register(selector, SelectionKey.OP_READ);
                                    } catch (ClosedChannelException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
