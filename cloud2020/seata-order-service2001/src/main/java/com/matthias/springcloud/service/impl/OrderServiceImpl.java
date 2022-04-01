package com.matthias.springcloud.service.impl;

import com.matthias.springcloud.dao.OrderDao;
import com.matthias.springcloud.domain.OrderDomain;
import com.matthias.springcloud.service.AccountService;
import com.matthias.springcloud.service.OrderService;
import com.matthias.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AccountService accountService;
    @Resource
    StorageService storageService;


    @Override
    @GlobalTransactional(name = "orderTransactional", rollbackFor = Exception.class)
    public void create(OrderDomain orderDomain) {
        log.info("================>>>开始创建订单");
        orderDao.create(orderDomain);

        log.info("================>>>订单微服务开始调用库存，做减法");
        storageService.decrease(orderDomain.getProductId(), orderDomain.getCount());
        log.info("================>>>订单微服务开始调用库存，做减法End");

        log.info("================>>>订单微服务开始调用账户，扣减余额");
        accountService.decrease(orderDomain.getUserId(), orderDomain.getMoney());
        log.info("================>>>订单微服务开始调用账户，扣减余额End");

        log.info("================>>>开始修改订单状态");
        orderDao.update(orderDomain.getUserId(), 0);
        log.info("================>>>修改订单状态结束");

        log.info("================>>>下订单结束了，O(∩_∩)O哈哈~");

    }
}
