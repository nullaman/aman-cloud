package com.aman.cloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.aman.cloud.mapper"})
public class MyBatisConfig {

}


