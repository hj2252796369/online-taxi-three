package com.hujie.apipassenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AoiPassengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AoiPassengerApplication.class, args);
    }

}
