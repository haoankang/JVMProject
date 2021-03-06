package ank.hao.multids.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "ank.hao.multids.mapper.db2", sqlSessionFactoryRef = "sqlSessionFactory_db2")    //basePackageClasses实际是扫描class所在的整个包
public class MybatisDb2Config {

    @Resource(name="db2_ds")
    private DataSource db2_ds;

    @Bean
    public SqlSessionFactory sqlSessionFactory_db2() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2_ds);
//        org.springframework.core.io.Resource resource = new ClassPathResource("mapperxml/StudentMapper.xml");
//        org.springframework.core.io.Resource[] resources = new org.springframework.core.io.Resource[]{resource};
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapperxml/db2/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate2() throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory_db2());
        return sqlSessionTemplate;
    }

}
