package com.aman.cloud.controller;

import com.aman.cloud.feign.PaymentHystrixFeign;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/consumer")
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentHystrixController {

    @Autowired
    private PaymentHystrixFeign paymentHystrixFeign;

    /**
     * Hystrix配置了全局和单独rollback的话，都生效，各自走各自。
     */

    @GetMapping("/payment/hystrix/ok/{id}")
    @HystrixCommand //异常处理也会进去
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
//        int a = 1 / 0; //异常
        String result = paymentHystrixFeign.paymentInfo_OK(id);
        log.info("*******result:" + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod"
            ,commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")  //3秒钟以内就是正常的业务逻辑
            }
    )
//    @HystrixCommand  // 全局
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
//        int a = 1/0; // 异常
        String result = paymentHystrixFeign.paymentInfo_TimeOut(id);
        log.info("*******result:" + result);
        return result;
    }

    public String paymentTimeOutFallbackMethod(Integer id) {
        return "feign服务异常，/(ㄒoㄒ)/~~";
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息，请稍后再试,(┬＿┬)";
    }


}
