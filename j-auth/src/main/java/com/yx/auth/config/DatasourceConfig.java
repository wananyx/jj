//package com.yx.auth.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.sql.DataSource;
//
///**
// * 配置的datasource,原应用于存储oauth相关参数数据,现改用redis
// */
//@Configuration
//public class DatasourceConfig {
//
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.driverClassName}")
//    private String driverClassName;
//
//    @Bean
//    public DataSource datasource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setUrl(url);
//        return dataSource;
//    }
//
//    @Bean
//    public TokenStore tokenStore(){
//        return new JdbcTokenStore(datasource());
//    }
//
//}
