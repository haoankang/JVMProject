package ank.hao.nio.channel;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) {
        URL url = FileChannelDemo.class.getClassLoader().getResource("data/nio-data");
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(url.getFile(),"rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(5000);
            int bytesRead = fileChannel.read(buf);
            while(bytesRead != -1){
                System.out.println("Read "+bytesRead);
                System.out.print(new String(buf.array(),"utf-8"));
                System.out.println("-----------------------------------");
                //将Buffer从写模式切换到读模式
                buf.flip();
                System.out.print(new String(buf.array(),"utf-8"));
//                System.out.println("_____________");
//                while (buf.hasRemaining()){
//                    System.out.print((char)buf.get());
//                }
                buf.clear();
                bytesRead = fileChannel.read(buf);
            }
            fileChannel.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
