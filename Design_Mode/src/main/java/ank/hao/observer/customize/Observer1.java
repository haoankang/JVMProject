package ank.hao.observer.customize;

public class Observer1 implements MyObserver{
    @Override
    public void update(String msg) {
        System.out.println("observer1.."+msg);
    }
}
