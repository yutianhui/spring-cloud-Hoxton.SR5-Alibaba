package com.study.service;

import cn.hutool.json.JSONObject;
import com.study.service.fallback.NacosPaymentServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName NacosPaymentService
 * @Author Yutian Hui
 * @date 2020.07.23 14:53
 */
@RequestMapping("/nacos")
// 指定调用的服务名,指定处理异常的方法
@FeignClient(value = "alibaba-nacos-provider-payment",fallbackFactory = NacosPaymentServiceImpl.class)
public interface NacosPaymentService {

    @GetMapping(value = "/payment/get/serverport")
    public JSONObject getServerPort();

}
