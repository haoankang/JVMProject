package ank.hao.proxy;

import java.lang.reflect.Field;

public class ProxyDemo {
    public static void main(String[] args) throws Exception{
        IMachine pc = new Machine_PC();
//        IMachine machine_proxy = (IMachine) new ProxyFactory(pc).getProxyInstance();
//        System.out.println(machine_proxy.run());;

        for(Field field: pc.getClass().getDeclaredFields()){
            //field.setAccessible(true);
            System.out.println(field.getName());
            System.out.println(field.getType());
            System.out.println(field.get(pc));
        }
    }
}
