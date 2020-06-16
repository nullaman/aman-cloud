package com.aman.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import com.aman.cloud.feign.PaymentFeign;
import com.aman.cloud.feign.PaymentFeignFallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentFeign paymentFeign;

    /**
     * 当 @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
     * 同时配置，【fallback管运行异常，blockHandler管配置违规】
     * exceptionsToIgnore = {Exception.class}  // 异常忽略
     */
    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback")
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback"
            //  , exceptionsToIgnore = {Exception.class}  // 异常忽略
    )
    public ResultData<Payment> fallback(@PathVariable Integer id) {
        ResultData<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, ResultData.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgument ,非法参数异常...");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }

    /**
     * fallback管运行异常
     */
    public ResultData<Payment> handlerFallback(@PathVariable Integer id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new ResultData<Payment>(444, "【fallback管运行异常】异常handlerFallback，exception内容： " + e.getMessage(), payment);
    }

    /**
     * blockHandler管配置违规
     */
    public ResultData<Payment> blockHandler(@PathVariable Integer id, BlockException e) {
        Payment payment = new Payment(id, "null");
        return new ResultData<Payment>(444, "【blockHandler管配置违规】blockHandler-sentinel 限流，BlockException： " + e.getMessage(), payment);
    }


    @RequestMapping("/consumer/feign/{id}")
//    @SentinelResource(value = "fallback")
    public ResultData<Payment> feignFallback(@PathVariable Integer id) {
        return paymentFeign.paymentSQL(id);
    }

}

