package com.study.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.study.service.NacosPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OrderController
 * @Author Yutian Hui
 * @date 2020.07.23 14:50
 */
@RestController
@Slf4j
@RequestMapping("/onacos")
public class OrderController {

    @Autowired
    private NacosPaymentService nacosPaymentService;

    @GetMapping("/hello")
    public JSONObject hello(){
        // 调用提供者的服务
        JSONObject serverPort = nacosPaymentService.getServerPort();
        if (serverPort.get("message") != null)
            serverPort.set("message","这条信息来自:" + serverPort.get("message"));
        return serverPort;
    }

    /* 测试使用注解的方法 */
    @GetMapping("/test/{id}")
    @SentinelResource(value = "testID",
            fallback = "testIdFallback"
    )
    public JSONObject testId(@PathVariable("id") long id){

        //人为的制作错误
        if (id == 5){
            int i = 10/0;
        }
        return new JSONObject()
                .putOnce("value",id)
                .putOnce("Method_Type","GET_SUCCESS")
                .putOnce("Name","testId");
    }

    /* java运行异常的兜底方法 */
    public JSONObject testIdFallback(long id,Throwable e){
        return new JSONObject()
                .putOnce("code","500")
                .putOnce("excep_msg",e.getMessage())
                .putOnce("status","程序运行时发生了一个错误!!");
    }

    /* sentinel监控的异常处理 */
    public JSONObject testFallBack(long id,BlockException blockException){
        return new JSONObject()
                .putOnce("error_msg","sentinel监控到这里出现了异常!")
                .putOnce("excep_msg",blockException.getMessage())
                .putOnce("status","exception not in java");
    }



}
