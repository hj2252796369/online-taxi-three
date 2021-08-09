package com.hujie.cloudgateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @program: online-taxi-three
 * @ClassName AuthFilterOne
 * @description:
 * @author: huJie
 * @create: 2021-08-08 20:16
 **/
@Component
@Order
public class AuthFilterOne implements GatewayFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }

    @Override
    public ShortcutType shortcutType() {
        return GatewayFilter.super.shortcutType();
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return GatewayFilter.super.shortcutFieldOrder();
    }

    @Override
    public String shortcutFieldPrefix() {
        return GatewayFilter.super.shortcutFieldPrefix();
    }
}
