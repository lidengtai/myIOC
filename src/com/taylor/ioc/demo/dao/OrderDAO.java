package com.taylor.ioc.demo.dao;

import com.taylor.ioc.demo.domain.OrderEntity;

/**
 * @ClassName OrderDAO
 * @Description TODO
 * @Author lidengtai
 * @Date 2019/7/7 14:14
 * @Version 1.0
 */
public interface OrderDAO {
    OrderEntity getOrderByID(Long orderID);
}
