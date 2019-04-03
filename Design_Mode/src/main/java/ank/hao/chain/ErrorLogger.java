package ank.hao.chain;

public class ErrorLogger extends ILogger {
    @Override
    void handler(App app) {
        System.out.println("ErrorLogger start..");

        int flag = app.getFlag();
        if(flag<=6){
            System.out.println("ErrorLogger handled");
        }else{
            super.nextHandler(app);
        }

        System.out.println("ErrorLogger end..");
    }
}
