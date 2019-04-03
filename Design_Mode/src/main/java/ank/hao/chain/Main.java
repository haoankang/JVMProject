package ank.hao.chain;

/**
 * 责任链模式/职责链模式
 */
public class Main {
    public static void main(String[] args) {
        App app = new App1(11);
        ILogger consoleLogger = new ConsoleLogger();
        ILogger errorLogger = new ErrorLogger();
        ILogger fileLogger = new FileLogger();

        consoleLogger.addNextLogger(errorLogger);
        errorLogger.addNextLogger(fileLogger);

        consoleLogger.handler(app);
    }
}
