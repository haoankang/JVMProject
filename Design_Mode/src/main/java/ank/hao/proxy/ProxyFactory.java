package ank.hao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    Object target;

    public ProxyFactory(Object object){
        this.target = object;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("start proxy");
                //执行目标对象方法
                Object returnObject = method.invoke(target,args);
                System.out.println("end proxy");
                return returnObject;
            }
        });
    }

}
