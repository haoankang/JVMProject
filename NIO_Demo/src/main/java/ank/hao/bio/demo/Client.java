package ank.hao.bio.demo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public void start() throws IOException{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(9933));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        new Thread(()->{
            try {
                while (true){
                    int read;
                    byte[] bytes = new byte[1024];
                    while((read=socket.getInputStream().read(bytes))!=-1){
                        System.out.println("客户端接收到数据:"+new String(bytes,0,read));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        while(true){
            Scanner scanner = new Scanner(System.in);

            if(scanner.hasNext()){
                String line = scanner.nextLine();
                if(line!=null){
                    socket.getOutputStream().write(line.getBytes(StandardCharsets.UTF_8));
                    socket.getOutputStream().flush();
                    if("bye".equals(line)){
                        break;
                    }
                }
            }

        }
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        new Client().start();
    }
}
