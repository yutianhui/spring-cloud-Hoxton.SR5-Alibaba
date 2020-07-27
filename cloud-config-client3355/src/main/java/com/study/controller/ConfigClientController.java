package com.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ConfigClientController
 * @Author Yutian Hui
 * @date 2020.07.22 00:48
 */
@RestController
@RefreshScope //动态刷新
@RequestMapping("/yaml")
public class ConfigClientController {

    @Value("${spring.info}")
    private String configinfo;

    @GetMapping("/get/info")
    public String hello(){
        return configinfo;
    }

}
