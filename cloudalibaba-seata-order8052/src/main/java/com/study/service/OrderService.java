package com.study.service;

import cn.hutool.json.JSONObject;
import com.study.mybatismbg.bean.TOrder;


public interface OrderService {

    /*
    * 创建新的订单
    *   1. 在T_Order表创建新的订单
    *   2. 调用库存微服务减少库存
    *   3. 调用账户微服务减少账户余额
    * */
    public JSONObject create(TOrder tOrder);

}
