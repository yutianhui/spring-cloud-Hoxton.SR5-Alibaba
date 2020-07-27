package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName StreamRabbitProvider8801
 * @Author Yutian Hui
 * @date 2020.07.22 17:59
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitProvider8801 {
    public static void main(String[] args) {

        SpringApplication.run(StreamRabbitProvider8801.class,args);
    }
}
