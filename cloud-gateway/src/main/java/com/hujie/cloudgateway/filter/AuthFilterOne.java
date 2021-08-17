package com.hujie.cloudgateway.filter;

import com.hujie.internalcommon.constant.RedisKeyPrefixConstant;
import com.hujie.internalcommon.util.JwtInfo;
import com.hujie.internalcommon.util.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: online-taxi-three
 * @ClassName AuthFilterOne
 * @description:
 * @author: huJie
 * @create: 2021-08-08 20:16
 **/
@Component
public class AuthFilterOne implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static List releaseUrlList = new ArrayList();

    {
        releaseUrlList.add("/auth/login");
        releaseUrlList.add("verify-code/send");
    }

    // 从exchange可以获取response
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取请求的参数
        ServerHttpRequest request = exchange.getRequest();

        // 对接口、auth/login    verify-code/send  接口放行
        URI uri = request.getURI();
        if (releaseUrlList.contains(uri.getPath())) {
            return chain.filter(exchange);
        }

        List<String> authorization = request.getHeaders().get("Authorization");
        String token = "";
        if (authorization != null && authorization.size() > 0) {
            token = authorization.get(0);
            JwtInfo tokenJwtInfo = JwtUtil.parseToken(token);
            if (null != tokenJwtInfo) {
                String tokenUserId = tokenJwtInfo.getSubject();

                // 比较token
                BoundValueOperations<String, String> stringStringBoundValueOperations = redisTemplate.boundValueOps(RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + tokenUserId);
                String tokenRedis = stringStringBoundValueOperations.get();
                if (!StringUtils.equals(token, tokenRedis)) {
                    DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap("没有权限哦~".getBytes());
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().writeWith(Mono.just(dataBuffer));
                }

                // 认证通过 更新认证时间
                stringStringBoundValueOperations.increment(180);
            } else {
                DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap("Token不正确~".getBytes());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().writeWith(Mono.just(dataBuffer));
            }
        } else {
            DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap("请先登录~".getBytes());
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }
        return chain.filter(exchange);
    }

    // 执行filter的顺序 值越小权重越大
    @Override
    public int getOrder() {
        return 0;
    }
}
