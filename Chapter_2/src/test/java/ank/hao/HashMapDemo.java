package ank.hao;

import org.junit.jupiter.api.Test;

public class HashMapDemo {

    @Test
    public void test1(){
        String[] strings = new String[2];
        String sss = "name";
        System.out.println(sss.hashCode());
        System.out.println(sss.hashCode()^(0>>>16));
        int h;
        System.out.println((h=sss.hashCode())^(h>>>16));
    }

    @Test
    public void test2(){

    }
}
