package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName NacosOrderMain9110
 * @Author Yutian Hui
 * @date 2020.07.23 14:48
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosOrderMain9110 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain9110.class,args);
    }
}
