package com.aman.cloud.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    /**
     * 订单状态:0创建中，1已完结
     */
    private Integer status;
}
