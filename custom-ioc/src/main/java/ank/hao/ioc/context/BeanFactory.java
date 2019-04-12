package ank.hao.ioc.context;

public interface BeanFactory {

    Object getBean(String name);

    void putBean(String name, Class c);
}
