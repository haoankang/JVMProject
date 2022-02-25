package ank.hao.observer.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class MyContextListener {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyContextListener.class);
        System.out.println("start..");
        context.close();
        System.out.println("end..");
    }

    @EventListener
    public void onListener(ApplicationEvent event){
        System.out.println("my applicationEvent trigger");
    }
}
