package com.hujie.serviceverificationcode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class ServiceVerificationCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerificationCodeApplication.class, args);
    }

    @Value("${server.port}")
    int port;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "test" + "port=" + port;
    }

    @RequestMapping("/gateway/test")
    @ResponseBody
    public String gatewayTest(){
        return "gateway/test";
    }

    @GetMapping("/serverport")
    @ResponseBody
    public String list(){
        return "port=" + port;
    }
}
