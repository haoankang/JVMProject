package ank.hao;

public class Test1 {

    public static void main(String[] args) {
        A a = new A(){
            @Override
            public void say() {
                System.out.println("Test1 say..");
            }
        };
        a.say();
    }
}

class A{
    private Integer a;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public void say(){
        System.out.println("A say..");
    }
}
