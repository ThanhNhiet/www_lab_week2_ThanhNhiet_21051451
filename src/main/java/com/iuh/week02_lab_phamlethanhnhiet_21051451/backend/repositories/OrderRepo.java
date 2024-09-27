package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Order;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class OrderRepo {
    @PersistenceContext(unitName = "my_pu")
    private EntityManager em;

    public void save(Order o) {
        em.persist(o);
        em.flush();
    }

    public Order findLastOrder() {
        return em.createQuery("SELECT o FROM Order o ORDER BY o.orderId DESC", Order.class).setMaxResults(1).getSingleResult();
    }

    public void saveOD(OrderDetail od) {
        em.persist(od);
    }

}
