package com.study.handler;

import cn.hutool.json.JSONObject;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @ClassName MyHandler
 * @Author Yutian Hui
 * @date 2020.07.24 16:14
 */
public class MyHandler {

    public static JSONObject customerHandler(BlockException blockException){
        return new JSONObject()
                .putOnce("status","Bad Request")
                .putOnce("Mes","这是一次糟糕的请求!")
                .putOnce("exception",blockException.getMessage());
    }

}
