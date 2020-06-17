package com.aman.cloud.service;

import com.aman.cloud.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
