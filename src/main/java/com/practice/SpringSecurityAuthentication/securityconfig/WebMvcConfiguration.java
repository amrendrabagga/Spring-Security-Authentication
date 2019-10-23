package com.practice.SpringSecurityAuthentication.securityconfig;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

//Spring @Configuration annotation helps in Spring annotation based configuration.,
// @Configuration annotation indicates that a class declares one or more @Bean methods and ,
// may be processed by the Spring container to generate bean definitions and ,
// service requests for those beans at runtime.

@Configuration
@EnableWebMvc
@ComponentScan("com.practice.SpringSecurityAuthentication")
public class WebMvcConfiguration implements WebMvcConfigurer {

//    @Bean
//    public InternalResourceViewResolver viewResolver(){
//        InternalResourceViewResolver vr=new InternalResourceViewResolver();
//        vr.setSuffix(".html");
//        vr.setPrefix("/resources/templates/");
//        return vr;
//    }
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("index");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

    //configuring datasource
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.practice.SpringSecurityAuthentication.model");

        Properties props=new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.use_sql_comments", "true");
        props.put("hibernate.connection.release_mode", "on_close");

        sessionFactory.setHibernateProperties(props);

        return sessionFactory;
    }
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj."
                + "jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/spring_security");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root123@");

        hikariConfig.setMaximumPoolSize(10);
//	    hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }

}
