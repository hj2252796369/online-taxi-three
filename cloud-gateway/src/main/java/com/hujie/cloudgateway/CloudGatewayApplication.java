package com.hujie.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

//    @Bean
//    // 如果喝yml配置冲突，代码的配置优先级是高于配置的
//    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder){
//        // 又先后顺序
//        return locatorBuilder.routes()
//                .route(p->p.path("/xxoo")
//                        .filters(f -> f.stripPrefix(1))
//                        .uri("http://hujie.com"))
//                // 同yml配置
//                .route(p->p.path("/go")
//                        .filters(f -> f.stripPrefix(1))
//                        .uri("lb://SERVICE-VERFICATION-CODE"))
//
//                .build();
//    }
}
