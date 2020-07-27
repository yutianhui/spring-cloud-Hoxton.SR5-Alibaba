package com.study;

import io.seata.spring.annotation.datasource.AutoDataSourceProxyRegistrar;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName SeataOrderMain8052
 * @Author Yutian Hui
 * @date 2020.07.25 19:22
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.study.mybatismbg.mappers")
public class SeataOrderMain8052 {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain8052.class,args);
    }

}
