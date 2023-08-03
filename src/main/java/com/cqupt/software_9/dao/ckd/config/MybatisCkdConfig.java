package com.cqupt.software_9.dao.ckd.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cqupt.software_9.dao.ckd",sqlSessionTemplateRef = "ckdSqlSessionTemplate")
public class MybatisCkdConfig {

    @Bean("ckdSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ckdDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(
                        "classpath:mapper/*.xml")
        );
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("ckdSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ckdSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("ckdDataSourceTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("ckdDataSource") DataSource dataSource){
        DataSourceTransactionManager manager=new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

}


