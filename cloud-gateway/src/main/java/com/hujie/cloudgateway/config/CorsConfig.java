package com.hujie.cloudgateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @program: online-taxi-three
 * @ClassName CorsConfig
 * @description:    跨域处理
 * @author: huJie
 * @create: 2021-08-13 20:13
 **/
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedMethod("*"); //支持所有方法
        configuration.addAllowedOrigin("*"); // 跨域处理 允许所有跨域
        configuration.addAllowedHeader("*"); // 支持所有请求头

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 匹配所有请求
        return new CorsWebFilter(source);
    }
}
