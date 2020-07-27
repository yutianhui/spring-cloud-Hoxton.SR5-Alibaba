package com.study.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FlowLimitController
 * @Author Yutian Hui
 * @date 2020.07.23 21:19
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public JSONObject testA(){
        return new JSONObject()
                .putOnce("name","testA");
    }

    @GetMapping("/testB")
    public JSONObject testB(){
        try {
            Thread.sleep(210);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new JSONObject()
                .putOnce("name","testB");
    }

    @GetMapping("/testC")
    public JSONObject testC(){
        JSONObject jsonObject = testB();
        jsonObject.set("name","通过testC调用的" + jsonObject.get("name"));
        return jsonObject;
    }

    /* 测试热点限流 */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "handlerHotKey") //唯一的名称,handler指定兜底的方法
    public JSONObject hotKeyControl(
            @RequestParam(value = "p1",required = false) String p1,
            @RequestParam(value = "p2",required = false) String p2){

        return JSONUtil.createObj()
                .putOnce("Method_Type","sucess")
                .putOnce("message","请求已经成功处理!")
                .putOnce("args",new JSONObject().putOnce("p1",p1).putOnce("p2",p2));
    }

    /* 处理异常的方法 */
    public JSONObject handlerHotKey(String p1, String p2, BlockException blockException){
        return JSONUtil.createObj()
                .putOnce("Method_Type","Bad Request")
                .putOnce("message","请求处理失败!")
                .putOnce("args",new JSONObject().putOnce("p1",p1).putOnce("p2",p2).putOnce("exception",blockException.getMessage()));
    }

}
