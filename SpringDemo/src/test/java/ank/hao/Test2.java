package ank.hao;

import java.util.concurrent.TimeUnit;

public class Test2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 has done");
        });
        t1.setDaemon(false);
        t1.start();

    }
}
