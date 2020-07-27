package com.study.controller;

import com.study.entities.CommonResult;
import com.study.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName OrderController
 * @Author Yutian Hui
 * @date 2020.07.16 16:50
 */
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    // 定义访问的URI
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    // 使用Eureka注册中心的服务名
    // 使用zookeeper作为注册中心时使用小写的服务名
//    public static final String PAYMENT_URL = "http://cloud-payment-service";
    // 注入操作模版
    @Autowired
    private RestTemplate restTemplate;

    // 创建订单的调用
    @PostMapping("/payment/creat")
    public CommonResult<Payment> creat(@RequestBody Payment payment){
        log.info("将会调用创建订单!");
        return restTemplate.postForObject(PAYMENT_URL + "/payment/creat",payment,CommonResult.class);
    }

    // 获取订单的调用
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Integer id){
        log.info("将会查询订单的内容!");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/result/" + id,CommonResult.class);
    }

}
