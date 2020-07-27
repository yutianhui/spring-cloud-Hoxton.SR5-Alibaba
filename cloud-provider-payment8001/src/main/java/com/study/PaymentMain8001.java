package com.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName PayMentMain8081
 * @Author Yutian Hui
 * @date 2020.07.13 17:06
 */
@SpringBootApplication
@EnableEurekaClient //入驻eureka注册中心
@EnableDiscoveryClient //开启服务注册发现的功能
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class,args);
    }
}
