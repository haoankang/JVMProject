package ank.hao.observer.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
public class MyCustomEventDemo {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyCustomEventDemo.class);
        System.out.println("start..");
        context.addApplicationListener(new MyListen());
        context.publishEvent(new MyEvent("dsg"));
        TimeUnit.SECONDS.sleep(1);
        System.out.println("end..");
    }

}

@Service
class MyListenService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void service(){
        System.out.println("myService trigger..");
        applicationEventPublisher.publishEvent(new MyEvent("skg"));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}

@Component
class MyListen implements ApplicationListener<MyEvent>{

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("my listen trigger..");
    }
}

class MyEvent extends ApplicationEvent{

    public MyEvent(Object source) {
        super(source);
        System.out.println("myEvent constructor..");
    }
}
