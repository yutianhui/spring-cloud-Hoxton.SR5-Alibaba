package com.study.controller;

import com.study.entities.CommonResult;
import com.study.mybatismbg.bean.TAccount;
import com.study.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AccountController
 * @Author Yutian Hui
 * @date 2020.07.26 17:54
 */
@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/reduce")
    public CommonResult reduce(@RequestParam("userId") Long userId, @RequestParam("money") Long money){
        log.info("将会扣减账户余额,用户id:" + userId + ",扣减金额:" + money);
        return accountService.reduce(userId,money);
    }

}
