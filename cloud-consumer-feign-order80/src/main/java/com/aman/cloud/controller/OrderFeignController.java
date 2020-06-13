package com.aman.cloud.controller;

import com.aman.cloud.entities.ResultData;
import com.aman.cloud.feign.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/test/feign/{id}")
    public ResultData<Object> myTestFeign(@PathVariable String id) {
        String fid = paymentFeignService.myTestFeign(id);
        log.info("*******order:[controller]:port==={}", fid);
        return ResultData.success(fid);
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }

}
