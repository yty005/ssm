package com.yty.ssm.dao;

import com.yty.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductDao {
    @Select("select * from product where id = #{id}")
    public Product findById(Integer id)throws Exception;
    @Select("select * from product")
    public List<Product> findAll() throws Exception;
    @Insert("insert into product(productNum,productName,cityName,DepartureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{DepartureTime},#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product) throws Exception;
}
