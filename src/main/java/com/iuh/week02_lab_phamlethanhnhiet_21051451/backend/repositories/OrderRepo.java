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
    }

    public Order find(Long id) {
        return em.find(Order.class, id);
    }

    public void saveOD(OrderDetail od) {
        em.persist(od);
    }

}
