package com.matthias.springcloud.controller;

import com.matthias.springcloud.entities.CommonResult;
import com.matthias.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(INVOKE_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/queryById/{id}")
    public CommonResult queryById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(INVOKE_URL + "/payment/queryById/" + id, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk/", String.class);
        return result;
    }

}
