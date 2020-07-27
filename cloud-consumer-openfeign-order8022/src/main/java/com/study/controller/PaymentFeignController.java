package com.study.controller;

import com.study.entities.CommonResult;
import com.study.entities.Payment;
import com.study.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PaymentFeignController
 * @Author Yutian Hui
 * @date 2020.07.19 18:24
 */
@RestController
@RequestMapping("/feign")
public class PaymentFeignController {

    // 注入服务
    @Autowired
    PaymentFeignService paymentFeignService;

    // 调用服务
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getOrder(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/tsleep/{id}")
    public CommonResult<Payment> getPaymentTsleepById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentTsleepById(id);
    }

}
