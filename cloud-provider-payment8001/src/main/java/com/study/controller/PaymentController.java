package com.study.controller;

import cn.hutool.json.JSONObject;
import com.study.entities.CommonResult;
import com.study.entities.Payment;
import com.study.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName PaymentController
 * @Author Yutian Hui
 * @date 2020.07.13 18:23
 */
@Slf4j
@RequestMapping(value = "/payment")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/creat")
    public CommonResult creat(@RequestBody(required = false) Payment payment) {
        // 插入数据
        int result = paymentService.create(payment);
        log.info("#### 数据插入的结果:" + result + " ####");
        if (result > 0)
            return new CommonResult(201, "PaymentService:" + serverPort + ",成功请求并创建了新的资源", result);
        return new CommonResult(202, "PaymentService:" + serverPort + ",已经接受请求，但未处理完成", 0);
    }

    @GetMapping("/result/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
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

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/discovery")
    public Object discovery() {
        /* 服务注册发现的配置,得到注册的服务列表 */
        List<String> services = discoveryClient.getServices();
        // 输出服务的名字
        for (String service : services) {
            log.info("service ==> " + service);
        }
        // 通过微服务的名称来获取下面的全部实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("URI = " + instance.getUri() + ",InstanceId = " + instance.getInstanceId());
        }
        // 返回信息
        return discoveryClient;
    }


    // 为了测试feign的超时控制的controller
    @GetMapping("/tsleep/{id}")
    public CommonResult<Payment> getPaymentTsleepById(@PathVariable("id") Long id) {
        try {
            Thread.sleep(3000);
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

    //添加方法进行测试
    @GetMapping("/zipkin")
    public String zipkin(){
        return new JSONObject()
                .putOnce("code",200)
                .putOnce("status","very nice!!")
                .putOnce("isCut",false).toString();
    }


}
