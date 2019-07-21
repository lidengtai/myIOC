package com.taylor.ioc.demo.service;

import com.taylor.ioc.annotation.Autowired;
import com.taylor.ioc.annotation.Service;
import com.taylor.ioc.demo.dao.OrderDAO;
import com.taylor.ioc.demo.domain.OrderEntity;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author lidengtai
 * @Date 2019/7/7 14:34
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Override
    public OrderEntity getOrderEntity(Long orderID) {
        OrderEntity orderEntity = orderDAO.getOrderByID(orderID);
        return orderEntity;
    }
}
