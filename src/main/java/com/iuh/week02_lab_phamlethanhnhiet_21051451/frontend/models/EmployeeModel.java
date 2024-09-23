package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.models;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Employee;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class EmployeeModel {
    private static final String BASED_URL = "http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/employees";

    public Employee findEmployeeById(Long id) {
        Employee employee = new Employee();
        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URL+ "/"+ id);
            Response response = target.request().get();
            employee = response.readEntity(Employee.class);
        }
        return employee;
    }
}
