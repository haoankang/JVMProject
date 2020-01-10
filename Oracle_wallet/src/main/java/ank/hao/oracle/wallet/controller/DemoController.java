package ank.hao.oracle.wallet.controller;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@RestController
public class DemoController implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/test")
    public void test() throws Exception{
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        ResultSet resultSet = connection.createStatement().executeQuery("select * from ml_scheduler_group");
        ResultSetMetaData md = resultSet.getMetaData();
        int columns = md.getColumnCount();
        while(resultSet.next()){
            for(int i=1;i<=columns;i++){
                System.out.println(resultSet.getString(i));
                System.out.println("===========");
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
