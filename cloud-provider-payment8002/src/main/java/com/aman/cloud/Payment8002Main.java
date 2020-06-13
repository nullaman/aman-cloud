package com.aman.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.aman.cloud.mapper")
public class Payment8002Main {
    public static void main(String[] args) {
        SpringApplication.run(Payment8002Main.class, args);
    }
}