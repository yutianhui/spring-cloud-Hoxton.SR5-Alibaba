package com.study.service.impl;

import cn.hutool.json.JSONObject;
import com.study.entities.CommonResult;
import com.study.mybatismbg.bean.TStorage;
import com.study.mybatismbg.bean.TStorageExample;
import com.study.mybatismbg.mappers.TStorageMapper;
import com.study.service.AccountService;
import com.study.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName StorageServiceImpl
 * @Author Yutian Hui
 * @date 2020.07.26 16:58
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {


    @Autowired
    private AccountService accountService;
    @Autowired
    private TStorageMapper tStorageMapper;


    @Override
    public CommonResult reduce(Long productId, Integer count) {
        /* 将库存中的商品数量进行减少 */
        // 查出数量
        TStorageExample tStorageExample = new TStorageExample();
        TStorageExample.Criteria criteria = tStorageExample.createCriteria();
        criteria.andProductIdEqualTo(productId);
        List<TStorage> tStorages = tStorageMapper.selectByExample(tStorageExample);
        // 比较
        TStorage tStorage = tStorages.get(0);
        tStorage.setUsed(tStorage.getUsed() + count);
        tStorage.setResidue(tStorage.getResidue() - count);
        // 更新
        int i = tStorageMapper.updateByExample(tStorage, tStorageExample);
        return new CommonResult(200,"成功更新了库存!",new JSONObject().putOnce("当前的该商品库存情况",tStorage));
    }
}
