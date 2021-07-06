package com.yty.ssm.dao;

import com.yty.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {
    @Select("SELECT * from traveller where id in (SELECT travellerId FROM order_traveller WHERE orderId = #{ordersId})")
    public List<Traveller> findByOrdersId(int ordersId) throws Exception;
}
