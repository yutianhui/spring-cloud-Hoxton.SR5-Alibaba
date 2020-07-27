package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName NacosPaymentMain9001
 * @Author Yutian Hui
 * @date 2020.07.23 14:17
 */
@SpringBootApplication
@EnableDiscoveryClient //开启服务发现
public class NacosPaymentMain9010 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentMain9010.class,args);
    }
}
