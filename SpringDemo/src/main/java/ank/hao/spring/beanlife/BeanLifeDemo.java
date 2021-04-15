package ank.hao.spring.beanlife;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanLifeDemo {

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    @Scope("singleton")
    public BeanDemo getBeanDemo(){
        return new BeanDemo();
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanLifeDemo.class);
        BeanDemo beanDemo = applicationContext.getBean(BeanDemo.class);
        beanDemo.test();
        applicationContext.getBeanFactory().destroyBean(beanDemo);
        applicationContext.destroy();
        TimeUnit.SECONDS.sleep(1);
    }
}

class BeanDemo implements InitializingBean, DisposableBean {

    void test(){
        System.out.println("BeanDemo test run..");
    }

    void initMethod(){
        System.out.println("init method run..");
    }
    void destroyMethod(){
        System.out.println("destory method run..");
    }
    @PostConstruct
    void postConstruct(){
        System.out.println("postConstruct run..");
    }
    @PreDestroy
    void preDestory(){
        System.out.println("preDestory run..");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("disposableBean destory run..");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initializingBean afterPropertiesSet run..");
    }
}
