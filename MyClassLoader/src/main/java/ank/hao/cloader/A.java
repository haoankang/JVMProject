package ank.hao.cloader;

public class A {
    static {
        System.out.println("begin..1");
    }

    public A(){
        System.out.println("...2");
    }
}

class B extends A{

    public B(){
        System.out.println("..3");
    }
}