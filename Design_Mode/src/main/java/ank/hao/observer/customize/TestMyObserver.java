package ank.hao.observer.customize;

public class TestMyObserver {

    public static void main(String[] args) {
        MyObserved myObserved = new MyObserved();
        myObserved.addObserver(new Observer1());
        myObserved.addObserver(new Observer3());
        myObserved.addObserver(new Observer2());
        myObserved.testTrigger();
    }
}
