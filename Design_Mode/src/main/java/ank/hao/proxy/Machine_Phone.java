package ank.hao.proxy;

public class Machine_Phone implements IMachine{
    public String run() {
        return "phone start work";
    }

    public void work() {
        System.out.println("phone work");
    }
}
