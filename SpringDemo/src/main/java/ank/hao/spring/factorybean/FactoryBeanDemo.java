package ank.hao.spring.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class FactoryBeanDemo {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanDemo.class);
        MyFactoryBean myFactoryBean = applicationContext.getBean(MyFactoryBean.class);
        System.out.println("time sleep");
        myFactoryBean.getObject();
    }

}
