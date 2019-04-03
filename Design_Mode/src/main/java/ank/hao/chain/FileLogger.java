package ank.hao.chain;

//DefaultLogger
public class FileLogger extends ILogger {
    @Override
    void handler(App app) {
        System.out.println("FileLogger start..");

        System.out.println("FileLogger handled");

        System.out.println("FileLogger end..");
    }
}
