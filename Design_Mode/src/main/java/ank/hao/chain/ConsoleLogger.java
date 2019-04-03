package ank.hao.chain;

public class ConsoleLogger extends ILogger {

    @Override
    public void handler(App app) {
        System.out.println("ConsoleLogger handler start..");
        int flag = app.getFlag();
        if(flag<=3){
            System.out.println("ConsoleLogger handled");
        }else{
            super.nextHandler(app);
        }
        System.out.println("ConsoleLogger handler end..");
    }
}
