package com.aman.cloud.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import com.aman.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;
    @Value("${spring.application.name}")
    private String springApplicationName;

    @GetMapping(value = "/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort + IdUtil.simpleUUID();
    }

    /**
     * 多服务集群轮询调用
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/test/feign/{id}")
    public ResultData<Object> myTestFeign(@PathVariable String id) {
        String s = paymentService.myTestFeign(id);
        log.info("*******payment:[controller]:serverPort==={}", serverPort);
        return ResultData.success(serverPort);
    }

    @GetMapping(value = "/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(server -> {
            log.info("**********server:{}", server);
            List<ServiceInstance> instances = discoveryClient.getInstances(server);
            instances.forEach(instance -> {
                log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
            });
        });
        return this.discoveryClient;
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
