package com.matthias.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-provider-hystrix-payment")
public interface PaymentHystrixService {
    @GetMapping("/hystrix/ok/{id}")
    String paymentInfoOK(@PathVariable("id") Integer id);

    @GetMapping("/hystrix/timeout/{id}")
    String paymentInfoTimeOut(@PathVariable("id") Integer id);
}
