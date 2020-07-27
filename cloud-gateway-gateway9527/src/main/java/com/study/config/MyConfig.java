package com.study.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyConfig
 * @Author Yutian Hui
 * @date 2020.07.21 18:38
 */
@Configuration
public class MyConfig {

    /* 通过代码的配置使用路由的匹配 */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();
        // https://blog.csdn.net/u010590120/article/details/94736800
        builder.route("route_news",f -> f.path("/u010590120/article/details/94736800").uri("http://blog.csdn.net")).build();
        return builder.build();
    }

}
