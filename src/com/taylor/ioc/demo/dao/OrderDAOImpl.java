package com.taylor.ioc.demo.dao;

import com.taylor.ioc.annotation.Repository;
import com.taylor.ioc.demo.domain.OrderEntity;

import java.util.Date;

/**
 * @ClassName OrderDAOImpl
 * @Description TODO
 * @Author lidengtai
 * @Date 2019/7/7 14:17
 * @Version 1.0
 */
@Repository("orderDao")
public class OrderDAOImpl implements OrderDAO {


    @Override
    public OrderEntity getOrderByID(Long orderID) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderID(1234567L);
        orderEntity.setOrderName("phoneSE");
        orderEntity.setOrderTime(new Date());
        return orderEntity;
    }
}
