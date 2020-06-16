package com.aman.cloud.controller;

import com.aman.cloud.entities.Payment;
import com.aman.cloud.entities.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Integer, Payment> map = new HashMap<>();

    static {
        map.put(1, new Payment(1, "1111"));
        map.put(2, new Payment(2, "2222"));
        map.put(3, new Payment(3, "3333"));
    }


    @GetMapping(value = "/paymentSQL/{id}")
    public ResultData<Payment> paymentSQL(@PathVariable("id") Integer id) {
        Payment payment = map.get(id);
        ResultData<Payment> result = new ResultData<>(200, "from mysql,serverPort: " + serverPort, payment);
        return result;
    }
}


