package com.study.service;

import com.study.entities.CommonResult;
import com.study.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// 使用feign来进行服务调用
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
@RequestMapping("/payment")
public interface PaymentFeignService {

    @GetMapping("/result/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    // 延时的测试方法
    @GetMapping("/tsleep/{id}")
    public CommonResult<Payment> getPaymentTsleepById(@PathVariable("id") Long id);
}
