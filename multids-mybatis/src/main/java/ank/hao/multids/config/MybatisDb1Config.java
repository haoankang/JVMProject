package ank.hao.multids.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@Configuration
@MapperScan(basePackages = "ank.hao.multids.mapper.db1", sqlSessionFactoryRef = "sqlSessionFactory_db1")
public class MybatisDb1Config {

    @Autowired
    @Qualifier("db1_ds")
    private DataSource db1_ds;

    @Bean
    public SqlSessionFactory sqlSessionFactory_db1() throws Exception{
//        Connection connection = db1_ds.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
//        ResultSet resultSet = preparedStatement.executeQuery();
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        String name = metaData.getColumnName(1);
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1_ds);
//        Resource resource = new ClassPathResource("mapperxml/PageDemoMapper.xml");
//        Resource[] resources = new Resource[]{resource};
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapperxml/db1/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception{
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory_db1());
        return sqlSessionTemplate;
    }
}
