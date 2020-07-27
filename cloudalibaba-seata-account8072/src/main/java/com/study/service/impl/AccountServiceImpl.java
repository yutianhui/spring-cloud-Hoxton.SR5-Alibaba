package com.study.service.impl;

import cn.hutool.json.JSONObject;
import com.study.entities.CommonResult;
import com.study.mybatismbg.bean.TAccount;
import com.study.mybatismbg.bean.TAccountExample;
import com.study.mybatismbg.mappers.TAccountMapper;
import com.study.mybatismbg.mappers.TOrderMapper;
import com.study.service.AccountService;
import com.study.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AccountServiceImpl
 * @Author Yutian Hui
 * @date 2020.07.26 17:12
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    @Autowired
    private TAccountMapper tAccountMapper;


    @Override
    public CommonResult reduce(Long userId, Long money) {
        /* 根据传进来的userid 和 金额减少账户的余额 */
        TAccountExample tAccountExample = new TAccountExample();
        TAccountExample.Criteria criteria = tAccountExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        // 查询用户
        List<TAccount> tAccounts = tAccountMapper.selectByExample(tAccountExample);
        TAccount tAccount = tAccounts.get(0);
        // 设置金额
        tAccount.setResidue(tAccount.getResidue() - money);
        tAccount.setUsed(tAccount.getUsed() + money);
        // 更新数据
        int i = tAccountMapper.updateByExample(tAccount, tAccountExample);
        return new CommonResult(200,"成功进行账户余额的更新",new JSONObject().putOnce("当前账户信息",tAccount));

    }
}
