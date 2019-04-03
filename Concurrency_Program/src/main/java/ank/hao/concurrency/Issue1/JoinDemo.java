package ank.hao.concurrency.Issue1;

public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        String param1 = "kaka";
        Integer param2 = 33;
        Long param3 = 44L;
        param3 = 55L;
        Thread t1 = new Thread(new Worker(Thread.currentThread()));
        Thread t2 = new Thread(new Worker(t1));
        Thread t3 = new Thread(new Worker(t2));

        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(3000);
        System.out.println("the join thread have been done");
    }



}


class Worker implements Runnable{

    private Thread former;

    public Worker(Thread thread){
        former = thread;
    }

    public Worker(String p1,Integer p2,Long p3){

    }

    public void run() {
        try {
            former.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+"--"+former);
    }
}