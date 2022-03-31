package com.matthias.springcloud.controller;

import com.matthias.springcloud.domain.CommonResult;
import com.matthias.springcloud.domain.OrderDomain;
import com.matthias.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping(value = "/create")
    public CommonResult createOrder(OrderDomain orderDomain) {
        orderService.create(orderDomain);
        return new CommonResult(200, "订单创建成功");
    }




}
