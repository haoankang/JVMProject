package ank.hao.dynamicds.dynamic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {

    public static final String DEFAULT_DS = "db1";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDB(String db){
        contextHolder.set(db);
        log.info("数据源切换到："+db);
    }

    public static String getDB(){
        return contextHolder.get();
    }

    public static void clearDB(){
        contextHolder.remove();
    }
}
