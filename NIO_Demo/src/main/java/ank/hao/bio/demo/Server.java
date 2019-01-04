package ank.hao.bio.demo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9933));
        Socket socket;
        while(true){
            socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = bufferedReader.readLine();
                System.out.println("server rece:"+line);
                bufferedWriter.close();

        }

    }

    public static void main(String[] args) throws IOException {
        new Server().start();
    }
}
