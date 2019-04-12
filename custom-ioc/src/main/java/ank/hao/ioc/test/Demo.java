package ank.hao.ioc.test;

import ank.hao.ioc.context.AnnoApplicationContext;
import ank.hao.ioc.context.BeanFactory;

public class Demo {

//    @MyBean(name = "Xas")
//    private Student student;

    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnoApplicationContext(Demo.class);
        Student student = (Student) beanFactory.getBean("XX");
        student.say();
    }

}
