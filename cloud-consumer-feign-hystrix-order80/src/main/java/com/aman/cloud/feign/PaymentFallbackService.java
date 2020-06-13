package com.aman.cloud.feign;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixFeign {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务熔断paymentInfo_OK is rall back : id===" + id;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "服务熔断paymentInfo_TimeOut is rall back : id===" + id;
    }

}
