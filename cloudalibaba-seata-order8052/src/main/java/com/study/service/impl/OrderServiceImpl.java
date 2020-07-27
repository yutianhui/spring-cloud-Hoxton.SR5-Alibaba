package com.study.service.impl;

import cn.hutool.json.JSONObject;
import com.study.mybatismbg.bean.TOrder;
import com.study.mybatismbg.bean.TOrderExample;
import com.study.mybatismbg.mappers.TOrderMapper;
import com.study.service.AccountService;
import com.study.service.OrderService;
import com.study.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OrderServiceImpl
 * @Author Yutian Hui
 * @date 2020.07.25 20:17
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TOrderMapper tOrderMapper;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional
    public JSONObject create(TOrder tOrder) {
        log.info("将会创建新的订单:" + tOrder);
        /* 向数据库中插入一个订单 */
        tOrderMapper.insert(tOrder);
        log.info("ProductID:" + tOrder.getProductId() + ",减少库存:" + tOrder.getCount());
        /* 调用库存微服务减少库存 */
        storageService.reduce(tOrder.getProductId(),tOrder.getCount());
        /* 调用账户微服务减少账户的余额 */
        log.info("用户:" + tOrder.getUserId() + ",账户减少:" + tOrder.getMoney());
        accountService.reduce(tOrder.getUserId(),tOrder.getMoney());
        /* 修改订单的状态 */
        log.info(">>>开始修改订单的状态>>>");
        // 添加条件
        TOrderExample tOrderExample = new TOrderExample();
        TOrderExample.Criteria criteria = tOrderExample.createCriteria();
        criteria.andIdEqualTo(tOrder.getId());
        // 添加修改信息
//        TOrder tOrder1 = new TOrder();
//        tOrder1.setId(tOrder.getId());
//        tOrder1.setStatus(0);
        tOrder.setStatus(1);
        tOrderMapper.updateByExample(tOrder,tOrderExample);
        log.info(">>>订单状态修改完成>>>");
        return new JSONObject()
                .putOnce("购买商品",tOrder.getProductId())
                .putOnce("购买数量",tOrder.getCount())
                .putOnce("总计",tOrder.getMoney());
    }
}
