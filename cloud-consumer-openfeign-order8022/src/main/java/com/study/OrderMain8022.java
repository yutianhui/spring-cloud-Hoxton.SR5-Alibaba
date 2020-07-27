package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //开启服务发现
@EnableFeignClients // feign的服务调用,feign自带负载均衡的效果(轮询)
public class OrderMain8022 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain8022.class,args);
    }
}
