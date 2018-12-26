package ank.hao.nio.demo;

import ank.hao.nio.demo.client.ClientSocket;
import ank.hao.nio.demo.server.ServerSocket;

import java.util.Scanner;

//思路：一般socket-io需要服务端和客户端，
//     服务端需要服务器接收，和处理.
public class Application {

    public static void main(String[] args) throws InterruptedException {
        //1. 启动Server
        ServerSocket.start();
        //2. 等待一段时间启动client
        Thread.sleep(1000);
        //3. 启动client,发送消息
        ClientSocket.send("my frist msg.");
    }
}
