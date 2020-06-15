package com.aman.cloud.controller;

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
}

