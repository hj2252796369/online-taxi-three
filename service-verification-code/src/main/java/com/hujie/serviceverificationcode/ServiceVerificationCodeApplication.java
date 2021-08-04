package com.hujie.serviceverificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Controller
public class ServiceVerificationCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationCodeApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "service-verfication-code";
    }
}
