package com.study.controller;

import com.study.entities.CommonResult;
import com.study.entities.Payment;
import com.study.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PaymentHystrixController
 * @Author Yutian Hui
 * @date 2020.07.20 16:45
 */
@Slf4j
@RestController
@RequestMapping("/consumer")
public class PaymentHystrixController {

    @Autowired
    PaymentFeignService paymentFeignService;

    // 测试服务的熔断机制
    @GetMapping("/payment/hystrix/get/{id}")
    public CommonResult<Payment> getOrder(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/hystrix/tsleep/{id}")
    public CommonResult<Payment> getTsleepOrder(@PathVariable("id") Long id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }
        log.info("将会调用方法!");
        return paymentFeignService.getPaymentTsleepById(id);
    }

    // 调用被监控端添加熔断的方法
    @GetMapping("/payment/circuit/get/{id}")
    public Object getCircuitResult(@PathVariable("id") long id){
        return paymentFeignService.getinfo(id);
    }
}
