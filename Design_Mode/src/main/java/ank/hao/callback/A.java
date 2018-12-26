package ank.hao.callback;

public class A implements ICallBack{

    void a(){
        System.out.println("a... please answer me");
       new B().b(this);
    }

    @Override
    public void callback() {
        System.out.println("i have get b callback");
    }
}
