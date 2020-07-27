package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //开启服务发现
@EnableFeignClients // feign的服务调用,feign自带负载均衡的效果(轮询)
@EnableHystrix // 开启hystrix功能
@EnableCircuitBreaker // 开启hystrix的服务熔断机制
@EnableHystrixDashboard // 开启Hystrix的监控面板
public class OrderHystrixDashboardMain8042 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixDashboardMain8042.class,args);
    }
}
