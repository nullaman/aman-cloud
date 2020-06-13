package com.aman.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import com.aman.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.application.name}")
    private String springApplicationName;

    /**
     * 多服务集群轮询调用
     * @param id
     * @return
     */
    @GetMapping(value = "/test/feign/{id}")
    public ResultData<Object> myTestFeign(@PathVariable String id) {
        String s = paymentService.myTestFeign(id);
        log.info("*******payment:[controller]:serverPort==={}", serverPort);
        return ResultData.success(serverPort);
    }

    @GetMapping("/get/port")
    public ResultData<Object> get() {
        log.info("*********{}:{}", springApplicationName, serverPort);
        return ResultData.success(springApplicationName + ":" + serverPort);
    }


    @GetMapping("/get/{id}")
    public ResultData<Object> get(@PathVariable Integer id) {
        log.info(">>>>>>>id:{}", id);
        log.info(">>>>>>>热部署生效");
        return ResultData.success(paymentService.getById(id));
    }

    @PostMapping("/post")
    public ResultData<Object> post(Payment payment) {
        log.info(">>>>>>>payment:{}", JSON.toJSONString(payment));
        return ResultData.success(paymentService.save(payment));
    }

}
