package ank.hao.multids.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "db1_ds")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2_ds")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }
}
