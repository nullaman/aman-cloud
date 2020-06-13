package com.aman.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {
    @GetMapping(value = "/payment/test/feign/{id}")
    String myTestFeign(@PathVariable("id") String id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
