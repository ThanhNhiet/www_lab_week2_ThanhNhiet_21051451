package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Order;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.OrderDetail;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories.OrderRepo;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Stateless
public class OrderBean implements OrderLocal{
    @Inject
    private OrderRepo orderRepo;


    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderRepo.save(order);
    }

    @Override
    @Transactional
    public Order findLastOrder() {
        return orderRepo.findLastOrder();
    }

    @Override
    @Transactional
    public void saveOrderDetail(OrderDetail orderDetail) {
        orderRepo.saveOD(orderDetail);

    }
}
