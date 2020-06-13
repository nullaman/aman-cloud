package com.aman.cloud.service.impl;

import com.aman.cloud.entities.Payment;
import com.aman.cloud.mapper.PaymentMapper;
import com.aman.cloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
    @Override
    public String myTestFeign(String id) {
        log.info("*******payment:[ServiceImpl]:id==={}", id);
        return id;
    }
}
