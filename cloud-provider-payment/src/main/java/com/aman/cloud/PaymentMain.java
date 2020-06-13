package com.aman.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 *
 * @author aman
 * @version 1.0
 * @date 2020/06/08
 * @since 2020/06/08
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.aman.cloud.mapper")
public class PaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain.class, args);
    }
}
