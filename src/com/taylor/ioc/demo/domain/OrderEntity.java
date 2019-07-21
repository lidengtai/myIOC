package com.taylor.ioc.demo.domain;

import java.util.Date;

/**
 * @ClassName OrderEntity
 * @Description TODO
 * @Author lidengtai
 * @Date 2019/7/7 14:10
 * @Version 1.0
 */
public class OrderEntity {

    private Long orderID;
    private String orderName;
    private Date orderTime;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderID=" + orderID +
                ", orderName='" + orderName + '\'' +
                ", orderTime=" + orderTime +
                '}';
    }
}
