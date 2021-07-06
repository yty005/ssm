package com.yty.ssm.service;

import com.yty.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {
    public List<Orders> findAll(int page, int size) throws Exception;
    public Orders findById(Integer id) throws Exception;

    List<Orders> findByProductId(Integer productId);
}
