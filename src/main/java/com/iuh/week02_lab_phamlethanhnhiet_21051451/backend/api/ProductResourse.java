package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.api;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business.ProductBO;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product;
import jakarta.inject.Inject;
import jakarta.persistence.Id;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/products")
public class ProductResourse {
    @Inject
    private ProductBO productBO;

    @GET
    public Response getAllProducts() {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(productBO.findAllProducts());
        return builder.build();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Long id) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(productBO.findProductById(id));
        return builder.build();
    }

    @GET
    @Path("/find/{name}")
    public Response getProductByName(@PathParam("name") String name) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(productBO.findProductByName(name));
        return builder.build();
    }

    @POST
    @Path("/save")
    public Response saveProduct(Product product) {
        productBO.saveProduct(product);
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(product);
        return builder.build();
    }

    @POST
    @Path("/delete{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        productBO.deleteProduct(id);
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(id);
        return builder.build();
    }

    @POST
    @Path("/update")
    public Response updateProduct(Product product) {
        productBO.updateProduct(product);
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(product);
        return builder.build();
    }
}
