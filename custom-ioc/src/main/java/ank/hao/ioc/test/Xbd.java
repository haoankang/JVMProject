package ank.hao.ioc.test;

import ank.hao.ioc.context.MyBean;

@MyBean(name = "Xbd")
public class Xbd implements Student {
    @Override
    public void say() {
        System.out.println("Xbd say.");
    }

    @Override
    public String getName() {
        return "Xbd";
    }
}
