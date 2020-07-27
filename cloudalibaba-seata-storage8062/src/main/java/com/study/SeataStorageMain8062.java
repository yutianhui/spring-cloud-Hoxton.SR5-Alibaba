package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName SeataStorageMain8062
 * @Author Yutian Hui
 * @date 2020.07.26 16:55
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.study.mybatismbg.mappers")
public class SeataStorageMain8062 {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMain8062.class,args);
    }

}
