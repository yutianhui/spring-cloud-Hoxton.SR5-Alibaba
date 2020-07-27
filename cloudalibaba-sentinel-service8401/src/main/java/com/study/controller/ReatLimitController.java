package com.study.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.study.handler.MyHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ReatLimitController
 * @Author Yutian Hui
 * @date 2020.07.24 15:46
 */
@RestController
public class ReatLimitController {

    /* 按照资源名称进行限流测试 */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handlerByResource")
    public JSONObject byResource(){
        return JSONUtil.createObj()
                .putOnce("ResourceName","byResource")
                .putOnce("status","success")
                .putOnce("Method_Type","GET");
    }

    // 服务降级的方法
    public JSONObject handlerByResource(){
        return JSONUtil.createObj()
                .putOnce("ResourceName","handlerByResource")
                .putOnce("status","Bad Request")
                .putOnce("Method_Type","GET");
    }

    /* 测试使用兜底的类来为方法做服务降级 */
    @GetMapping("/byCustomSource")
    @SentinelResource(
            value = "byCustomSource",
            blockHandlerClass = MyHandler.class,
            blockHandler = "customerHandler")
    public JSONObject customerHandlerMethod(){
        return JSONUtil.createObj()
                .putOnce("Method_Name","customerHandlerMethod")
                .putOnce("Response_Code",200)
                .putOnce("Resource_Name","/byCustomSource");
    }



}
