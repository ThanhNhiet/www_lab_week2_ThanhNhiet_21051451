package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Employee;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories.EmployeeRepo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named
@RequestScoped
public class EmployeeBO {

    @Inject
    private EmployeeRepo employeeRepo;

    @Transactional
    public Employee find(Long id) {
        return employeeRepo.find(id);
    }
}
