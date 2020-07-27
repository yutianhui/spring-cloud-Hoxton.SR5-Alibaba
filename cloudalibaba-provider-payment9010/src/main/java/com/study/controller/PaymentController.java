package com.study.controller;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PaymentController
 * @Author Yutian Hui
 * @date 2020.07.23 14:20
 */
@RestController
@RequestMapping("/nacos")
public class PaymentController {

    @Value("${server.port:}")
    private String serverport;

    @GetMapping(value = "/payment/get/serverport")
    public JSONObject getServerPort(){
        System.out.println(10/0);
        return new JSONObject()
                .putOnce("status","scuessful")
                .putOnce("message",serverport);
    }

}
