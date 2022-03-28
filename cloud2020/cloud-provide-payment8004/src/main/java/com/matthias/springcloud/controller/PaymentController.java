package com.matthias.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    //需要安装启动zookeeper服务器
    @GetMapping(value = "/payment/zk")
    public Object paymentZk() {
        return "SpringCloud with zookeeper: " + serverPort + "\t" + UUID.randomUUID();
    }
}
