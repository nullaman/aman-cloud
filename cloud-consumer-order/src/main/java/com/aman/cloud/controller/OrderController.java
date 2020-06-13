package com.aman.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import com.aman.cloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

//        public static final String PAYMENT_URL = "http://localhost:8001";

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
//    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 自定义和默认的配置不能同时使用
     * 同时开启：自定义不能用报错【@throws】
     *
     * @return
     * @throws java.lang.IllegalStateException: No instances available for windows10.microdone.cn
     */
    @GetMapping("/payment/get/myport")
    public ResultData<Object> MyGetPort() {
        // 自定义
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        log.info("uri:{}", uri);
        ResponseEntity<ResultData> entity = restTemplate.getForEntity(uri + "/payment/get/port", ResultData.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
//            log.info("*****:{}", JSON.toJSONString(entity));
            return entity.getBody();
        }
        return ResultData.error();
    }

    @GetMapping("/payment/get/port")
    public ResultData<Object> getPort() {
        ResponseEntity<ResultData> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/port", ResultData.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            log.info("*****:{}", JSON.toJSONString(entity));
            return entity.getBody();
        }
        return ResultData.error();
    }


    @GetMapping("/payment/get/{id}")
    public ResultData<Payment> getPayment(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, ResultData.class);
    }

    @GetMapping("/payment/post")
    public ResultData<Object> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/post", payment, ResultData.class);
    }

}

