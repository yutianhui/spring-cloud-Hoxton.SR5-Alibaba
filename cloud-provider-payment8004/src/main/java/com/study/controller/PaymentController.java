package com.study.controller;

import cn.hutool.json.JSONObject;
import com.study.entities.CommonResult;
import com.study.entities.Payment;
import com.study.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    // 查询zookeeper注册的实例
    @RequestMapping(value = "/zk")
    public String paymentzk(){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.append("ServerPort",serverPort);
        jsonObject1.append("UUID_STRING", UUID.randomUUID().toString());
        // 将数据存放到jsonobject中
        jsonObject.append("SpringCloud",jsonObject1);
        return jsonObject.toString();
    }


}
