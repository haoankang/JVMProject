package ank.hao.observer.jdk;

import java.util.Observable;

public class JdkObserved extends Observable {


    public void testChange(){
        System.out.println("jdkObserved changed begin..");
        this.setChanged();
        this.notifyObservers();
        System.out.println("jdkObserved changed end..");
    }
}
