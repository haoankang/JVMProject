package oome;

public class StackDemo {

    private static int count = 1;

    public static void main(String[] args) {
        try {
            circle();
        }catch (Throwable e){
            System.out.println(count);
            e.printStackTrace();
        }

    }

    static void circle() {
        count++;
        circle();
    }
}
