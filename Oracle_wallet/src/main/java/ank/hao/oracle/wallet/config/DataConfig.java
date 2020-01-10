package ank.hao.oracle.wallet.config;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataConfig {

    @Bean
    public DataSource dataSource(){
        OracleDataSource dataSource = null;
        try {
            dataSource = new OracleDataSource();
            Properties properties = new Properties();
            String oracle_net_wallet_location = "D:\\oracle_wallet\\wallet_23";
            properties.put("oracle.net.tns_admin", oracle_net_wallet_location);
            properties.put("oracle.net.wallet_location", "(source=(method=file)(method_data=(directory="+oracle_net_wallet_location+")))");
            dataSource.setConnectionProperties(properties);
            dataSource.setURL("jdbc:oracle:thin:@myorcl");
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataSource;
    }
}
