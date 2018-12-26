package three.two;

public class IsDeadDemo {

    public Object obj = null;

    public static void main(String[] args) {
        IsDeadDemo isDeadDemo = new IsDeadDemo();
        IsDeadDemo isDeadDemo1 = new IsDeadDemo();
        isDeadDemo.obj = isDeadDemo1;
        isDeadDemo1.obj = isDeadDemo;

        isDeadDemo = null;
        isDeadDemo1 = null;

        System.gc();

        System.out.println("kagkd");
    }
}
