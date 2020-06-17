package com.aman.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.aman.cloud.domain.Order;
import com.aman.cloud.entities.ResultData;
import com.aman.cloud.service.OrderService;
import com.aman.cloud.snowflake.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;


    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @GetMapping("/order/create")
    public ResultData<Object> create(Order order) {
        orderService.create(order);
        return ResultData.success(200, "订单创建成功");
    }

    /**
     * 雪花算法生成订单
     *
     * @return
     */
    @GetMapping("/order/snowflake")
    public ResultData<Object> snowflake() {
//        idGeneratorSnowflake.init();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Long> list = new ArrayList<>();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    long lid = idGeneratorSnowflake.snowflakeId();
                    list.add(lid);
                    System.out.println(">>>>>> id : " + lid);
                }
            }
        });
        return ResultData.success(JSON.toJSON(list), 200, "订单创建成功");
    }
}

