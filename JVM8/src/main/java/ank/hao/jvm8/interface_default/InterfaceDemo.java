package ank.hao.jvm8.interface_default;

/**
 * 1. 函数式接口
 * 2. 接口中default声明默认方法
 */
public class InterfaceDemo implements Interface1{
    @Override
    public void say() {
        System.out.println("say() needs override.");
    }

    public static void main(String[] args) {
        Interface1 demo = new InterfaceDemo();
        demo.say();
        demo.talk();
    }
}

@FunctionalInterface
interface Interface1{
    void say();
    default void talk(){
        System.out.println("people alwasy need talk..");
    }
}
