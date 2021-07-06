package com.yty.ssm.dao;

import com.yty.ssm.domain.Member;
import com.yty.ssm.domain.Orders;
import com.yty.ssm.domain.Product;
import com.yty.ssm.domain.Traveller;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.yty.ssm.dao.ProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId",javaType = Product.class, one = @One(select = "com.yty.ssm.dao.ProductDao.findById")),
            @Result(property = "member", column = "memberId",javaType = Member.class, one = @One(select = "com.yty.ssm.dao.MemberDao.findByMemberId")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select = "com.yty.ssm.dao.TravellerDao.findByOrdersId"))
    })
    public Orders findById(Integer id) throws Exception;
    @Select("select * from orders where productid=#{productId}")
    List<Orders> findByProductId(Integer productId);
}
