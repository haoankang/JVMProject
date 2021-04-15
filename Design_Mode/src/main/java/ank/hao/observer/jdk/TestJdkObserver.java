package ank.hao.observer.jdk;

public class TestJdkObserver {

    public static void main(String[] args) {
        JdkObserved jdkObserved = new JdkObserved();
        jdkObserved.addObserver(new JdkObserver1());
        jdkObserved.addObserver(new JdkObserver2());
        jdkObserved.testChange();
        System.out.println("end..");
    }
}
