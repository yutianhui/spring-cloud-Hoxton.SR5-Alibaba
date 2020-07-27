package com.study.fallback;

import com.study.entities.CommonResult;
import com.study.entities.Payment;
import com.study.service.PaymentFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName PaymentFeignServiceFallback
 * @Author Yutian Hui
 * @date 2020.07.20 16:51
 */
@Component // 添加到spring容器中
public class PaymentFeignServiceFallback implements FallbackFactory<PaymentFeignService> {

    @Override
    public PaymentFeignService create(Throwable throwable) {
        return new PaymentFeignService() {
            @Override
            public CommonResult<Payment> getPaymentById(Long id) {
                return new CommonResult<>(200,"系统繁忙中,请稍后重试!");
            }

            @Override
            public CommonResult<Payment> getPaymentTsleepById(Long id) {
                return new CommonResult<>(200,"系统繁忙中,请稍后重试!");
            }

            @Override
            public Object getinfo(long id) {
                return new CommonResult<>(200,"系统繁忙中,请稍后重试!");
            }
        };
    }
}
