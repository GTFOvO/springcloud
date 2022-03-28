package com.matthias.springcloud.controller;

import com.matthias.springcloud.entities.CommonResult;
import com.matthias.springcloud.entities.Payment;
import com.matthias.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("consumer")
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/payment/queryById/{id}")
    public CommonResult<Payment> queryById(@PathVariable("id") Long id) {
        return paymentFeignService.queryById(id);
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // OpenFeign客户端一般默认等待1秒钟
        return paymentFeignService.paymentFeignTimeout();
    }
}
