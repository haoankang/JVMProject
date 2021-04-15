package ank.hao.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyFactoryBean implements FactoryBean<String> {

    public MyFactoryBean(){
        System.out.println("myFactoryBean init..");
    }

    @Override
    public String getObject() throws Exception {
        System.out.println("myFactoryBean getObject..");
        return "success";
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
