package com.aman.cloud.feign;

import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignFallback implements PaymentFeign {

    @Override
    public ResultData<Payment> paymentSQL(Integer id) {
        return new ResultData<Payment>(444, "【Feign服务降级返回 - PaymentFeignFallback】异常paymentSQL ---  1");
    }

}
