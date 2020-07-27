package com.study.config;

import feign.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ApplicationConfiguration
 * @Author Yutian Hui
 * @date 2020.07.16 16:56
 */
@Configuration
public class ApplicationConfiguration {

    @Bean
    @LoadBalanced //开启resttemplate默认的负载均衡的策略-轮询
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    // 配置feign打印日志的bean
    @Bean
    public Logger.Level getLoggerLevel(){
        return Logger.Level.FULL;
    }

}
