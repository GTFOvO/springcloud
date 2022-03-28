package com.matthias.springcloud.controller;

import com.matthias.springcloud.entities.CommonResult;
import com.matthias.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import com.matthias.springcloud.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.insert(payment);
        CommonResult commonResult = new CommonResult();

        log.info("插入结果：" + result);

        if (result > 0) {
            commonResult.setCode(200);
            commonResult.setMessage("插入数据库成功, serverPort:" + serverPort);
            commonResult.setData(result);
        } else {
            commonResult.setCode(444);
            commonResult.setMessage("插入数据库失败");
            commonResult.setData(null);
        }
        return commonResult;
    }

    @GetMapping(value = "/payment/queryById/{id}")
    public CommonResult queryById(@PathVariable("id") Long id) {
        Payment payment = paymentService.queryById(id);
        CommonResult commonResult = new CommonResult();

        log.info("查询结果：" + payment);

        if (payment != null) {
            commonResult.setCode(200);
            commonResult.setMessage("查询成功, serverPort:" + serverPort);
            commonResult.setData(payment);
        } else {
            commonResult.setCode(444);
            commonResult.setMessage("查询失败，id=" + id);
            commonResult.setData(null);
        }
        return commonResult;
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> list = discoveryClient.getServices();
        for (String str : list) {
            log.info("#############element: " + str);
        }
        List<ServiceInstance> instanceList = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instanceList) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t"
                    + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public Object queryPaymentLB() {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
