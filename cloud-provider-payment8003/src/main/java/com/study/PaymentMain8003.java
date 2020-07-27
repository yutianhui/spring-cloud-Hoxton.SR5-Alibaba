package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName PayMentMain8081
 * @Author Yutian Hui
 * @date 2020.07.13 17:06
 */
@SpringBootApplication
@EnableDiscoveryClient //开启服务发现
public class PaymentMain8003 {
    // 将会把这个微服务注册进consul服务注册中心
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8003.class,args);
    }
}
