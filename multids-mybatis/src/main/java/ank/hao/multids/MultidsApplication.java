package ank.hao.multids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MultidsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultidsApplication.class, args);
    }
}
