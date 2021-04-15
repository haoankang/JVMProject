package ank.hao.observer.customize;

public class Observer2 implements MyObserver {
    @Override
    public void update(String msg) {
        System.out.println("observer2.."+msg);
    }
}
