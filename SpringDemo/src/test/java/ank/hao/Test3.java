package ank.hao;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test3 {

    @Test
    public void test(){
        Map map = new HashMap();
        map.put(1,"ww");
        map.put(2,"ss");
        map.put(1,"dd");
        System.out.println(map);
    }
}
