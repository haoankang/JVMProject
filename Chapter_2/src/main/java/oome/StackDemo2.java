package oome;

@Deprecated
//会使pc假死，不建议执行
public class StackDemo2 {

    public static void main(String[] args) {
        new StackDemo2().createT();
    }

    void createT(){
        while(true){
            new Thread(new Runnable() {
                public void run() {
                    nostop();
                }
            }).start();
        }

    }

    void nostop(){
        while(true){

        }
    }
}
