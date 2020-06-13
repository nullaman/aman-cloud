package com.aman.cloud.service;

import com.aman.cloud.entities.Payment;
import com.baomidou.mybatisplus.extension.service.IService;

public interface PaymentService extends IService<Payment> {
    /**
     * 多服务集群轮询调用
     *
     * @param id
     * @return
     */
    String myTestFeign(String id);
}
