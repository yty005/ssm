package com.yty.ssm.service;

import com.yty.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll() throws Exception;
    public void save(Product product) throws Exception;
}
