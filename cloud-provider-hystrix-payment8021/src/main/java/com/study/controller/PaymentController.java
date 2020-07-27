package com.study.controller;

import cn.hutool.json.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.study.entities.CommonResult;
import com.study.entities.Payment;
import com.study.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PaymentController
 * @Author Yutian Hui
 * @date 2020.07.13 18:23
 */
@Slf4j
@RequestMapping("/payment")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/creat")
    public CommonResult creat(@RequestBody(required = false) Payment payment){
        // 插入数据
        int result = paymentService.create(payment);
        log.info("#### 数据插入的结果:" + result + " ####");
        if (result > 0 )
            return new CommonResult(201,"PaymentService:" + serverPort + ",成功请求并创建了新的资源",result);
        return new CommonResult(202,"PaymentService:" + serverPort + ",已经接受请求，但未处理完成",0);
    }

    @GetMapping("/result/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        log.info("#### 将会查询id为" + id + "的数据 ####");
        // 查询结果
        Payment paymentById = paymentService.getPaymentById(id);
        if (paymentById != null) {
            log.info("查询到的数据:" + paymentById.toString());
            return new CommonResult<Payment>(200, "PaymentService:" + serverPort + ",查询成功", paymentById);
        }
        log.info("#### 查询信息时发生了错误 ####");
        return new CommonResult<>(202,"PaymentService:" + serverPort + ",已经接受请求，但未处理完成");
    }

    // 为了测试feign的超时控制的controller
    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {
            // 指定方法的最大执行时间,超过就会报错
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @GetMapping("/tsleep/{id}")
    public CommonResult<Payment> getPaymentTsleepById(@PathVariable("id") Long id) {
        try {
            Thread.sleep(4000);
        }catch (Exception e){
            e.fillInStackTrace();
        }
        log.info("#### 将会查询id为" + id + "的数据 ####");
        // 查询结果
        Payment paymentById = paymentService.getPaymentById(id);
        if (paymentById != null) {
            log.info("查询到的数据:" + paymentById.toString());
            return new CommonResult<Payment>(200, "PaymentService:" + serverPort + ",查询成功", paymentById);
        }
        log.info("#### 查询信息时发生了错误 ####");
        return new CommonResult<>(202, "PaymentService:" + serverPort + ",已经接受请求，但未处理完成");
    }

    public CommonResult<Payment> fallbackMethod(@PathVariable("id") Long id){
        return new CommonResult<Payment>(500,"你好!你要获取的id ==> " + id + ",由于系统繁忙中,请稍候重试");
    }

    // 加入了熔断的方法
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
    @GetMapping("/circuit/get/{id}")
    public Object getinfo(@PathVariable("id") Long id){
        if(id == 2) {
            int o = 10/0;
        }
        return paymentService.getPaymentById(id);
    }

}
