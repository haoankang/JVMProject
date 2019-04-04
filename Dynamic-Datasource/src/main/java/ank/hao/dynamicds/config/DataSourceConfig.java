package ank.hao.dynamicds.config;

import ank.hao.dynamicds.dynamic.DynamicDS;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean("db1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource1(){
        return DataSourceBuilder.create().build();
    }

    @Bean("db2")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }

    @Bean("dynamicDS")
    public DataSource dataSourcex(){
        DynamicDS dynamicDS = new DynamicDS();
        dynamicDS.setDefaultTargetDataSource(dataSource1());

        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("db1", dataSource1());
        dsMap.put("db2", dataSource2());
        dynamicDS.setTargetDataSources(dsMap);
        return dynamicDS;
    }
}
