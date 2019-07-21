package com.taylor.ioc.demo.service;

import com.taylor.ioc.demo.domain.OrderEntity;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author lidengtai
 * @Date 2019/7/7 14:32
 * @Version 1.0
 */
public interface OrderService {
    OrderEntity getOrderEntity(Long orderID);
}
