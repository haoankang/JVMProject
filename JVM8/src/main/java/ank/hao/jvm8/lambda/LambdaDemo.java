package ank.hao.jvm8.lambda;

import java.util.Arrays;
import java.util.Optional;

public class LambdaDemo {

    public static void main(String[] args) {

        //无参数无返回值
        Runnable r1 = () -> System.out.println("lambda demo first");
        new Thread(r1).start();
        //有一个参数无返回值

//        LambdaInterface lambdaInterface = s -> {
//            System.out.println("interface implements,the result is: "+s);
//        };
//        lambdaInterface.test2("dkgds");

        //有多个参数有返回值
        LambdaInterface lambdaInterface = (m,n,q) -> m+n+q;
        System.out.println(lambdaInterface.test3("test",22, 33.45d));


        Arrays.asList(22,33,66).forEach(integer -> System.out.println(integer));

    }

}

@FunctionalInterface
interface LambdaInterface {
    //void test2(String s);

    static void test1(){
        System.out.println("static method");
    }

    String test3(String s, Integer i, Double d);

    default void test4(){
        System.out.println("default method");
    }

    default void test5(){
    }
}
