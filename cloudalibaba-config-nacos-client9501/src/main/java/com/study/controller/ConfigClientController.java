package com.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ConfigClientController
 * @Author Yutian Hui
 * @date 2020.07.23 15:32
 */
@RestController
@RequestMapping("/nclient")
@RefreshScope //表示支持配置动态刷新的注解
public class ConfigClientController {

    @Value("${spring.info}")
    private String info;

    @GetMapping("/info")
    public String info(HttpServletResponse httpResponse){
        httpResponse.addHeader("Name-Service-Provider","Leibohui");
        return info;
    }


}
