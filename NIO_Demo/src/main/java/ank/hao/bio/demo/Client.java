package ank.hao.bio.demo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void start() throws IOException{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(9933));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner scanner = new Scanner(System.in);

            String line = scanner.nextLine();
            if(line!=null){
                bufferedWriter.write(line);
            }
            bufferedWriter.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        new Client().start();
    }
}
