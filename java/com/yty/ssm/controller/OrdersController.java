package com.yty.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.yty.ssm.domain.Orders;
import com.yty.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    //查询全部订单  未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        ModelAndView mv = new ModelAndView();
//        List<Orders> list = ordersService.findAll();
//        mv.addObject("ordersList",list);
//        mv.setViewName("orders-list");
//        return mv;
//    }
    @RequestMapping("findAll.do")
    public ModelAndView findAllByPage(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size) throws Exception{//注意参数类型尽量不要使用基本类型，而要使用包装类，因为前置通知中获取参数类型是获取对象
        ModelAndView mv = new ModelAndView();
        List<Orders> list = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) Integer id) throws Exception {
        ModelAndView mv = new ModelAndView();
        Orders order = ordersService.findById(id);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }
    @RequestMapping("/findByProductId")
    public ModelAndView findByProductId(@RequestParam(name = "id",required = true) Integer productId){
        ModelAndView mv = new ModelAndView();
        List<Orders> orders = ordersService.findByProductId(productId);
        mv.addObject("productId",productId);
        mv.addObject("ordersList",orders);
        mv.setViewName("product-orders-show");
        return mv;
    }

}
