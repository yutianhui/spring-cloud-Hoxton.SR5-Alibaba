package com.study.controller;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.entities.CommonResult;
import com.study.mybatismbg.bean.TOrder;
import com.study.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName OrderController
 * @Author Yutian Hui
 * @date 2020.07.25 22:56
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody TOrder tOrder){
        JSONObject jsonObject = orderService.create(tOrder);
        return new CommonResult(200,"成功创建了订单!",jsonObject);
    }

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/datainfo")
    public String info(){
//        String str = "{}";
//        try {
//            str = new ObjectMapper().writeValueAsString(dataSource);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return dataSource.toString();
    }

}
