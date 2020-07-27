package com.study.controller;

import cn.hutool.json.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
 * @ClassName PaymentFeignController
 * @Author Yutian Hui
 * @date 2020.07.19 18:24
 */
@Slf4j
@RestController
@RequestMapping("/feign")
@DefaultProperties(defaultFallback = "defaultFallbackMethod",commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
})
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

    // 在客户端进行服务的调用并熔断测试
    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {
            // 指定函数的调用时间为2s
            /*@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),*/
            // 指定开启熔断机制
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 指定请求的次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            // 指定时间的空窗期
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            // 指定失败率达到多少开启熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    @GetMapping("/payment/hystrix/test/{id}")
    public CommonResult<Payment> getTestFallbackMethod(@PathVariable("id") Long id){
        if (id == 2){
            int o = 10/0;
            return null;
        }
        return paymentFeignService.getPaymentById(id);
    }


    public CommonResult<Payment> fallbackMethod(Long id){
        return new CommonResult<Payment>(500,"服务调用出现异常,可能是系统能够出现了繁忙!");
    }

    // 测试会跳到默认的配置页面的服务降级测试
    @GetMapping("/payment/hystrix/test1/{id}")
    @HystrixCommand //将会使用默认的配置
    public JSONObject hystrixServerTest1(@PathVariable("id") long id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new JSONObject();

    }

    public JSONObject defaultFallbackMethod(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("code","系统繁忙!代码出现故障!");
        jsonObject.append("status","4578912354");
        log.info("封装的数据:" + jsonObject);
        return jsonObject;
    }

}
