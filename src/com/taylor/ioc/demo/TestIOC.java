package com.taylor.ioc.demo;

import com.taylor.ioc.core.AnnotationApplicationContext;
import com.taylor.ioc.core.ApplicationContext;
import com.taylor.ioc.demo.domain.OrderEntity;
import com.taylor.ioc.demo.service.OrderService;

/**
 * @ClassName TestIOC
 * @Description 启动入口类
 * @Author lidengtai
 * @Date 2019/7/7 16:39
 * @Version 1.0
 */
public class TestIOC {
    public static void main(String[] args) {

        ApplicationContext app = new AnnotationApplicationContext();
        OrderService orderService = (OrderService) app.getBean("orderServiceImpl");
        System.out.println(orderService);
        OrderEntity orderEntity = orderService.getOrderEntity(1234567L);
        System.out.println(orderEntity);
    }
}
