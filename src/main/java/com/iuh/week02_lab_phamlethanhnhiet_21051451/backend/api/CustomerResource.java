package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.api;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business.CustomerBO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {

    @Inject
    private CustomerBO customerBO;

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(customerBO.find(id));
        return builder.build();
    }
}
