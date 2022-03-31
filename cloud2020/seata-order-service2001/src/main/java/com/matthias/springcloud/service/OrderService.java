package com.matthias.springcloud.service;

import com.matthias.springcloud.domain.OrderDomain;

public interface OrderService {
    void create(OrderDomain orderDomain);
}
