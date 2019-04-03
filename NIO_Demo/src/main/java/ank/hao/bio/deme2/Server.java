package ank.hao.bio.deme2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

    public static void main(String[] args) {
        int port = 8811;
        if(args!=null && args.length>0){
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){

            }
        }

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("The server is start in port ："+port);
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                final Socket finalSocket = socket;
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        BufferedReader br = null;
                        PrintWriter pw = null;
                        try {
                            br = new BufferedReader(new InputStreamReader(finalSocket.getInputStream()));
                            pw = new PrintWriter(finalSocket.getOutputStream(), true);
                            String body = null;
                            while(true){
                                body = br.readLine();
                                if(body==null){
                                    break;
                                }
                                System.out.println("Sever receive order: "+body);
                                pw.println(System.currentTimeMillis());
                            }
                        }catch (Exception e){

                        }finally {
                            try {
                                br.close();
                                pw.close();
                                finalSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();
            }
        }catch (IOException ee){
            ee.printStackTrace();
        }finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
