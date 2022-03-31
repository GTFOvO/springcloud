package com.matthias.springcloud.dao;

import com.matthias.springcloud.domain.OrderDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    //1 新建订单
    void create(OrderDomain order);

    //2 修改订单状态，从零改为1
    void update(@Param("userId") Long userId, @Param("status") Integer status);


}
