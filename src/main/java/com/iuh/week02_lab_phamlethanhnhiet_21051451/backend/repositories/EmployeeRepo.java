package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EmployeeRepo {
    @PersistenceContext(unitName = "my_pu")
    private EntityManager em;

    public Employee find(Long id) {
        return em.find(Employee.class, id);
    }
}
