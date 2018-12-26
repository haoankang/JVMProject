package ank.hao.nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;

public class FileTransferDemo {
    public static void main(String[] args) throws IOException {
        URL url = FileChannelDemo.class.getClassLoader().getResource("data/nio-data");

        RandomAccessFile fromFile = new RandomAccessFile(url.getFile(),"rw");
        FileChannel fromChannel = fromFile.getChannel();

        String path = url.getPath();
        String rootPath = path.substring(0, path.lastIndexOf("/nio-data"));
        RandomAccessFile toFile = new RandomAccessFile(rootPath+"/to-file","rw");
        FileChannel toChannel = toFile.getChannel();
        toChannel.position(200);


        fromChannel.transferTo(0, fromChannel.size(), toChannel);
    }
}
