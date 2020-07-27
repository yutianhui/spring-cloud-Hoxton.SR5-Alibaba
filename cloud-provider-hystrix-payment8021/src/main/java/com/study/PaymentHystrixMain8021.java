package com.study;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName PayMentMain8081
 * @Author Yutian Hui
 * @date 2020.07.13 17:06
 */
@SpringBootApplication
@EnableEurekaClient //入驻eureka注册中心
@EnableHystrix // 开启hystrix的服务熔断
@EnableCircuitBreaker // 开启熔断器
public class PaymentHystrixMain8021 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8021.class,args);
    }

    /*@Bean
    public ServletRegistrationBean servletRegistrationBean(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(streamServlet);
        servletRegistrationBean.setLoadOnStartup(1);
        servletRegistrationBean.addUrlMappings("/hystrix.stream");
        servletRegistrationBean.setName("HystrixMetricsStreamServlet");
        return servletRegistrationBean;
    }*/


}
