package com.aman.cloud.feign;

import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFeignFallback.class)
public interface PaymentFeign {

    @GetMapping(value = "/paymentSQL/{id}")
    ResultData<Payment> paymentSQL(@PathVariable("id") Integer id);

}
