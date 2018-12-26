package ank.hao.proxy;

public class Machine_PC implements IMachine {

    private String a="a";
    public Integer b=22;

    public String run() {
        return "pc start compute.";
    }

    public void work() {
        System.out.println("pc work");
    }
}
