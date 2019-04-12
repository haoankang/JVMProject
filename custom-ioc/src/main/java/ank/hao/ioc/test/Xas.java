package ank.hao.ioc.test;

import ank.hao.ioc.context.MyBean;

@MyBean(name = "Xas")
public class Xas implements Student {
    @Override
    public void say() {
        System.out.println("Xas say..");
    }

    @Override
    public String getName() {
        return "Xas";
    }
}
