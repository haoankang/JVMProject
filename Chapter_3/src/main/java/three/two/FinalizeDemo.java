package three.two;

public class FinalizeDemo {

    private static FinalizeDemo finalizeDemo = null;

    @Override
    public void finalize(){
        finalizeDemo = this;
    }

    public static void main(String[] args) {
        finalizeDemo = new FinalizeDemo();

        finalizeDemo = null;
        System.gc();
        if(finalizeDemo==null){
            System.out.println("dead..");
        }else {
            System.out.println("alive..");
        }

        finalizeDemo = null;
        System.gc();
        if(finalizeDemo==null){
            System.out.println("dead..");
        }else {
            System.out.println("alive..");
        }
    }
}
