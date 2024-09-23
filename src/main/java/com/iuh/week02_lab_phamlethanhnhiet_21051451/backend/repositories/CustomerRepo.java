package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomerRepo {
    @PersistenceContext(unitName = "my_pu")
    private EntityManager em;

    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }
}
