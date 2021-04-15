package ank.hao.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class JdkObserver1 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("jdkObserver1.."+arg);
    }
}
