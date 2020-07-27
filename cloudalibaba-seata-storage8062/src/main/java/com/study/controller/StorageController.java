package com.study.controller;

import com.study.entities.CommonResult;
import com.study.mybatismbg.bean.TStorage;
import com.study.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName StorageController
 * @Author Yutian Hui
 * @date 2020.07.26 17:34
 */
@Slf4j
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/reduce")
    public CommonResult reduce(@RequestParam("productId") Long productId, @RequestParam("count") Integer count){
        log.info("商品id为:" + productId + ",即将减少:" + count);
        /* 调用库存做减法 */
        CommonResult reduce = storageService.reduce(productId, count);
        return reduce;
    }

}
