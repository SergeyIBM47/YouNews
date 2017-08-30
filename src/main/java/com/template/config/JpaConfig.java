package com.template.config;

//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by: Sergey Volokh
 * Date: 5/13/2016
 * Time: 7:03 PM
 * Project: Spring MVC
 */
@Configuration
@EnableJpaRepositories(basePackages ={
        "com.template.domain.repository"
})
@EnableTransactionManagement
public class JpaConfig {

    @Bean(destroyMethod = "close")
    DataSource dataSource(Environment env){
        HikariDataSource hikariDataSource = new HikariDataSource();

//        hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/diplom");
        hikariDataSource.setDriverClassName("org.h2.Driver");
        hikariDataSource.setJdbcUrl("jdbc:h2:mem:diplom");
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");

        return new HikariDataSource(hikariDataSource);
    }

    @Bean(name = "entityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.template.domain.model");

        entityManagerFactoryBean.setJpaProperties(jpaProperties());

        return entityManagerFactoryBean;
    }

    private Properties jpaProperties() {
        Properties jpaProperties = new Properties();

        jpaProperties.setProperty(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        jpaProperties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create-drop");
        jpaProperties.setProperty(org.hibernate.cfg.Environment.SHOW_SQL, "true");
        jpaProperties.setProperty(org.hibernate.cfg.Environment.FORMAT_SQL, "true");

        return jpaProperties;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
