package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.api;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business.OrderLocal;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Order;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.OrderDetail;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/orders")
public class OrderResource {

    @Inject
    private OrderLocal orderLocal;

    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") Long id) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(orderLocal.findOrder(id));
        return builder.build();
    }

    @POST
    @Path("/save")
    public Response saveOrder(Order order) {
        orderLocal.saveOrder(order);
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(order);
        return builder.build();
    }

    @POST
    @Path("/saveOD")
    public Response saveOrderDetail(OrderDetail od) {
        orderLocal.saveOrderDetail(od);
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(od);
        return builder.build();
    }

}
