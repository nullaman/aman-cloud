package com.aman.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() throws InterruptedException {
//        TimeUnit.SECONDS.sleep(1);
        log.info(">>>>>>>> TimeUnit.SECONDS.sleep(1)>>>");
        return "----testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "----testB";
    }

    @GetMapping("/test/byUrl")
    @SentinelResource(value = "byUrl")
    public ResultData<Object> byUrl() {
        return ResultData.success(new Payment(2020, "serial002"), 200, "按照byUrl限流测试");
    }

}

