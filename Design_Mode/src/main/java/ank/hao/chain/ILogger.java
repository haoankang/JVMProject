package ank.hao.chain;

public abstract class ILogger {

    private ILogger nextLogger;

    void addNextLogger(ILogger iLogger){
        this.nextLogger = iLogger;
    }

    void nextHandler(App app){
        this.nextLogger.handler(app);
    }

    abstract void handler(App app);
}
