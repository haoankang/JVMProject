package ank.hao;

public class Test4 implements Runnable {

    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new Test4(i)).start();
        }
    }

    private Integer i;
    public Test4(Integer i){
        this.i = i;
    }

    @Override
    public void run() {
        threadLocal.set(i);

        System.out.println(threadLocal.get());
    }
}
