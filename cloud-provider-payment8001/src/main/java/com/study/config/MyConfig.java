package com.study.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName MyConfig
 * @Author Yutian Hui
 * @date 2020.07.13 17:09
 */
@Configuration
public class MyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource") // 将配置文件中的值取出并赋值给datasource
    public DataSource dataSource(){
        return new DruidDataSource();
    }
}
