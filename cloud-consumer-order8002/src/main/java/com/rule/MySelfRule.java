package com.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MySelfRule
 * @Author Yutian Hui
 * @date 2020.07.19 16:57
 */
@Configuration
public class MySelfRule {
    /*
    * 自定义的规则类
    * */
    @Bean
    public IRule randomRule(){
        // 随机的ribbon负载均衡机制
        return new RandomRule();
    }
}
