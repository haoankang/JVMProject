package ank.hao.bio.deme2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        Socket socket = new Socket();
        BufferedReader br = null;
        BufferedReader brr = null;
        PrintWriter pw = null;
        try {
            socket.connect(new InetSocketAddress(8811));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(),true);
            pw.println("QUERY TIME ORDER");
            String resp = br.readLine();
            System.out.println("Client receive msg: "+resp);
            pw.println("try again..");
            String resp2 = br.readLine();
            System.out.println("Client receive msg: "+resp2);

            brr = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                pw.println(brr.readLine());
                System.out.println("Client receive message: "+br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                pw.close();
                brr.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
