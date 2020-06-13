package com.aman.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka7001Main {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Main.class, args);
    }
}
