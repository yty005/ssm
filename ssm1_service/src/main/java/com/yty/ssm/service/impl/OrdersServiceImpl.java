package com.yty.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.yty.ssm.dao.OrdersDao;
import com.yty.ssm.domain.Orders;
import com.yty.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("orderService")
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(Integer id) throws Exception {
        return ordersDao.findById(id);
    }

    @Override
    public List<Orders> findByProductId(Integer productId) {
        return ordersDao.findByProductId(productId);
    }
}
