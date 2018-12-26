package ank.hao.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();

            SocketChannel sc1 = SocketChannel.open(new InetSocketAddress(9911));
            sc1.configureBlocking(false);
            SelectionKey sk1 = sc1.register(selector, SelectionKey.OP_READ | SelectionKey.OP_CONNECT);
            SocketChannel sc2 = SocketChannel.open(new InetSocketAddress(9912));
            sc2.configureBlocking(false);
            SelectionKey sk2 = sc2.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_ACCEPT);

            while (true){
                int readyChannels = selector.select();
                if(readyChannels==0)
                    continue;
                Set selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
                while(keyIterator.hasNext()){
                    SelectionKey key = keyIterator.next();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
