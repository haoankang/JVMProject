package ank.hao.bio.demo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Server {

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9933));
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了");
            new Thread(()->{
                try {
                    int read;
                    byte[] bytes = new byte[1024];
                    while((read=socket.getInputStream().read(bytes))!=-1){
                        System.out.println("服务端接收到数据:"+new String(bytes,0,read));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }).start();

            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNext()){
                String message = scanner.nextLine();
                socket.getOutputStream().write(message.getBytes(StandardCharsets.UTF_8));
                socket.getOutputStream().flush();
                if("bye".equals(message)){
                    break;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        new Server().start();
    }
}
