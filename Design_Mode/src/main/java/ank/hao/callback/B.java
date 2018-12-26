package ank.hao.callback;


public class B {

    public void b(ICallBack a) {
        System.out.println("b .. please wait");

        //do something
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("b... i have done");
                    a.callback();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
