package ank.hao;

public class SemaphoreDemo {

    volatile int sign = 0;

    public static void main(String[] args) {
        new SemaphoreDemo().run();


    }

    public void run(){
        new Thread(new TA()).start();
        new Thread(new TB()).start();
        new Thread(new TC()).start();
    }

    class TA implements Runnable{

//    private Thread next;
//
//    public TA(Thread next){
//        this.next = next;
//    }

        @Override
        public void run() {
            while(true){
                if(sign==0){
                    System.out.println("A");
                    sign = 1;
                }

            }

        }
    }

    class TB implements Runnable{

        @Override
        public void run() {
            while(true){
                if(sign==1){
                    System.out.println("B");
                    sign =2;
                }
            }

        }
    }

    class TC implements Runnable{

        @Override
        public void run() {
            while(true){
                if(sign==2){
                    System.out.println("C");
                    sign =0;
                }
            }

        }
    }
}

