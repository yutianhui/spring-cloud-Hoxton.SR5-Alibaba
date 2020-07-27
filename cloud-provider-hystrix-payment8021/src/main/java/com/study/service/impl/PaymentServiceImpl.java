package com.study.service.impl;

import cn.hutool.core.util.IdUtil;
import com.study.dao.PaymentDao;
import com.study.entities.Payment;
import com.study.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaymentServiceImpl
 * @Author Yutian Hui
 * @date 2020.07.13 18:18
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /* 注入dao */
    @Autowired
    PaymentDao paymentDao ;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    /* 测试服务的熔断来使用的方法 */

    @Override
    public String getPaymentUUID() {
        return IdUtil.randomUUID();
    }
}
