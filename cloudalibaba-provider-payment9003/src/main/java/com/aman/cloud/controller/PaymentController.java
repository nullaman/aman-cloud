package com.aman.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.aman.cloud.annotation.HAStatusCheck;
import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import com.aman.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        paymentService.myTestFeign("id");
        return "Hello Nacos Discovery: " + serverPort + "\t id: " + id;
    }


    @GetMapping("/get/{id}")
    public ResultData<Object> get(@PathVariable Integer id) {
        log.info(">>>>>>>id:{}", id);
        log.info(">>>>>>>热部署生效");
        return ResultData.success(paymentService.getById(id));
    }

    @PostMapping("/post")
//    @HAStatusCheck
    public ResultData<Object> post(Payment payment) {
        log.info(">>>>>>>payment:{}", JSON.toJSONString(payment));
        return ResultData.success(paymentService.save(payment));
    }

}

