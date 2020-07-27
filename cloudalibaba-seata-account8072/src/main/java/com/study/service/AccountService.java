package com.study.service;


import com.study.entities.CommonResult;
import com.study.mybatismbg.bean.TAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName AccountService
 * @Author Yutian Hui
 * @date 2020.07.25 20:16
 */
public interface AccountService {

    /*
     * 减少账户余额的方法
     *   1. 用户的id
     *   2. 减少的钱数
     * */
    public CommonResult reduce(Long userId,Long money);

}
