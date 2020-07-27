package com.study.service.fallback;

import cn.hutool.json.JSONObject;
import com.study.service.NacosPaymentService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName NacosPaymentImpl
 * @Author Yutian Hui
 * @date 2020.07.24 17:55
 */
@Component
public class NacosPaymentServiceImpl implements FallbackFactory<NacosPaymentService> {
    @Override
    public NacosPaymentService create(Throwable throwable) {
        return new NacosPaymentService() {
            @Override
            public JSONObject getServerPort() {
                return new JSONObject()
                        .putOnce("status","failed")
                        .putOnce("Msg","请求出现异常,已经降级!");
            }
        };
    }
}
