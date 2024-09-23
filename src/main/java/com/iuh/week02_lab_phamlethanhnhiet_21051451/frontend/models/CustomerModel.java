package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.models;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.dtos.entities.Customer;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

public class CustomerModel {
    private static final String BASED_URL = "http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/customers";

    public Customer findCustomerById(Long id) {
        Customer customer = new Customer();
        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URL+ "/"+ id);
            Response response = target.request().get();
            customer = response.readEntity(Customer.class);
        }
        return customer;
    }
}
