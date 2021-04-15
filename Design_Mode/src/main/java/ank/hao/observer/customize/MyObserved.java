package ank.hao.observer.customize;

import java.util.ArrayList;
import java.util.List;

//被观察者
public class MyObserved {

    private List<MyObserver> observerList = new ArrayList<>();

    private void notifyObservers(String msg){
        observerList.stream().forEach(myObserver -> {
            myObserver.update(msg);
        });
    }

    public void testTrigger(){
        System.out.println("test trigger start..");
        notifyObservers("kpi");
        System.out.println("test trigger end..");
    }

    public void addObserver(MyObserver myObserver){
        observerList.add(myObserver);
    }
}
