package com.taylor.ioc.demo.controller;

import com.taylor.ioc.annotation.Autowired;
import com.taylor.ioc.demo.domain.OrderEntity;
import com.taylor.ioc.demo.service.OrderService;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author lidengtai
 * @Date 2019/7/7 17:34
 * @Version 1.0
 */
public class OrderController {

    @Autowired
    private OrderService orderService;

    public OrderEntity getOrderInfo(){
        return orderService.getOrderEntity(1234567l);
    }
}
