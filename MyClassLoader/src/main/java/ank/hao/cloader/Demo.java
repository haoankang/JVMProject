package ank.hao.cloader;

public class Demo {

    public static void main(String[] args) throws ClassNotFoundException {

        AnkClassLoader ankClassLoader = new AnkClassLoader();
        Class clazz = ankClassLoader.loadClass("com.alibaba.fastjson.JSONObject");
        Class clazz1 = ankClassLoader.loadClass("ank.hao.cloader.B");
        new B();
        System.out.println(ankClassLoader);

    }
}
