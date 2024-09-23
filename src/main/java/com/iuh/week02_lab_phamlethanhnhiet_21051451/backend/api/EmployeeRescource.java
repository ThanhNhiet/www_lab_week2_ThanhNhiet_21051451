package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.api;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business.EmployeeBO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/employees")
public class EmployeeRescource {

    @Inject
    private EmployeeBO employeeBO;

    @GET
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") Long id) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(employeeBO.find(id));
        return builder.build();
    }
}
