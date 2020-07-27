package com.study.service;


import com.study.entities.CommonResult;
import com.study.mybatismbg.bean.TStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName StorageService
 * @Author Yutian Hui
 * @date 2020.07.25 20:16
 */
@FeignClient("alibaba-seata-storage8062")
@RequestMapping("/storage")
public interface StorageService {

    /*
    * 减少库存的方法
    * 参数:
    *   1. 商品id
    *   2. 减少的数量
    */
    @PostMapping("/reduce")
    public CommonResult reduce(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
