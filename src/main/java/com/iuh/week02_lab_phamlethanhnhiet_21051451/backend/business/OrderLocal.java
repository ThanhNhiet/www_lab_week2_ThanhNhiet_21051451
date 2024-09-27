package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Order;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.OrderDetail;

public interface OrderLocal {
    public void saveOrder(Order order);
    public Order findLastOrder();
    public void saveOrderDetail(OrderDetail orderDetail);
}
