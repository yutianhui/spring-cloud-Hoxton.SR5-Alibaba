package com.study.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName MyConfiguration
 * @Author Yutian Hui
 * @date 2020.07.25 19:23
 */
@Configuration
public class MyConfiguration {

    @Bean
    @ConfigurationProperties(value = "spring.datasource")
    public DataSource dataSource(){
        return new DruidDataSource();
    }

}
