package com.study.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @ClassName MyGatewayFilter
 * @Author Yutian Hui
 * @date 2020.07.21 22:49
 */
@Component
@Slf4j
public class MyGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 验证请求参数中是否有合理的用户名
        log.info("将会对请求进行验证 --> exchange:" + exchange.toString());
        List<String> name = exchange.getRequest().getQueryParams().get("name");
        if (name.get(0) == null){
            log.warn("这是一个非法的用户!可能出现问题!");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // 放行
        return chain.filter(exchange);
    }

    // 加载顺序,数字越小,优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
