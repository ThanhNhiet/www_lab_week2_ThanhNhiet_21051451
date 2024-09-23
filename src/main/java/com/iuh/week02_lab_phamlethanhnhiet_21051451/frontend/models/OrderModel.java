package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.models;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Order;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class OrderModel {
    private static final String BASED_URL = "http://localhost:8080/week02_lab_PhamLeThanhNhiet_21051451-1.0-SNAPSHOT/api/orders";

    public void createOrder(Order order) {
        try(Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URL+ "/save");
            Response response = target.request().post(Entity.json(order));
        }
    }
}
