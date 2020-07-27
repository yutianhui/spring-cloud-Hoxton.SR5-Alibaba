package com.study.controller;

import cn.hutool.json.JSONObject;
import com.study.mybatismbg.bean.TOrder;
import com.study.mybatismbg.bean.TOrderExample;
import com.study.mybatismbg.mappers.TOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MyController
 * @Author Yutian Hui
 * @date 2020.07.25 21:36
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class MyController {

    @Autowired
    private TOrderMapper tOrderMapper;

    @PostMapping("/create")
    public JSONObject create(@RequestBody TOrder tOrder){
        log.info("创建订单:" + tOrder.toString());
        // 创建订单
        int insert = tOrderMapper.insert(tOrder);
        return new JSONObject()
                .putOnce("插入",insert);
    }

    @PostMapping("/update")
    public JSONObject update(@RequestBody TOrder tOrder){
        log.info(tOrder.toString());
        // 更新订单
        TOrderExample tOrderExample = new TOrderExample();
        TOrderExample.Criteria criteria = tOrderExample.createCriteria();
        criteria.andUserIdEqualTo(tOrder.getUserId());
        // 更新
        int i = tOrderMapper.updateByExample(tOrder, tOrderExample);
        return new JSONObject()
                .putOnce("更新",i);
    }


}
