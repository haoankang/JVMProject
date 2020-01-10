package ank.hao.oracle.wallet;

import java.sql.*;
import java.util.Properties;

public class DemoMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:oracle:thin:/@myorcl";
        String wallets = "D:\\oracle_wallet\\wallet_23";
        Connection connection = null;
        Properties properties = new Properties();
        properties.put("oracle.net.tns_admin", wallets);
        properties.put("oracle.net.wallet_location", wallets);
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection(url,properties);
        ResultSet resultSet = connection.createStatement().executeQuery("select * from ml_scheduler_group");
        ResultSetMetaData md = resultSet.getMetaData();
        int columns = md.getColumnCount();
        while(resultSet.next()){
            for(int i=1;i<=columns;i++){
                System.out.println(resultSet.getString(i));
                System.out.println("===========");
            }
        }
        System.out.println(resultSet);
    }
}
