package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName PayMentMain8081
 * @Author Yutian Hui
 * @date 2020.07.13 17:06
 */
@SpringBootApplication
@EnableEurekaClient //入驻eureka注册中心
public class PaymentMain8011 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8011.class,args);
    }
}
