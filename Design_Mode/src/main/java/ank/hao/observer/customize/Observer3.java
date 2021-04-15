package ank.hao.observer.customize;

public class Observer3 implements MyObserver {
    @Override
    public void update(String msg) {
        System.out.println("observer3.."+msg);
    }
}
