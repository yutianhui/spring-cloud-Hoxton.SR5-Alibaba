package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName StreamRabbitConsumer8802
 * @Author Yutian Hui
 * @date 2020.07.22 21:05
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitConsumer8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitConsumer8802.class,args);
    }
}
