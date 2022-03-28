package com.matthias.springcloud.service.impl;

import com.matthias.springcloud.dao.PaymentDao;
import com.matthias.springcloud.entities.Payment;
import com.matthias.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Payment queryById(Long id) {
        return paymentDao.queryById(id);
    }

    @Override
    public List<Payment> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<Payment> queryAll(Payment payment) {
        return null;
    }

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public int update(Payment payment) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
