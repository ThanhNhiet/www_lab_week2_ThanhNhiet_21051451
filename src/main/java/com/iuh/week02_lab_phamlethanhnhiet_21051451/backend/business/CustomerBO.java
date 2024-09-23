package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Customer;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories.CustomerRepo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

@Named
@RequestScoped
public class CustomerBO {
    @Inject
    private CustomerRepo customerRepo;

    @Transactional
    public Customer find(Long id) {
        return customerRepo.find(id);
    }
}
