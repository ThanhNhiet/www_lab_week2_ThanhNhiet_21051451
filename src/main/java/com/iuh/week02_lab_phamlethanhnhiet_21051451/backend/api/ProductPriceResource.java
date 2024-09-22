package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.api;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business.ProductPriceBO;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/product-prices")
public class ProductPriceResource {
    @Inject
    private ProductPriceBO productPriceBO;

    @GET
    public Response getAllProductPrices() {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(productPriceBO.findAllProductPrices());
        return builder.build();
    }

    @GET
    @Path("/{id}")
    public Response getProductPriceById(@PathParam("id") Long id) {
        Response.ResponseBuilder builder = Response.ok();
        builder.entity(productPriceBO.findProductPriceById(id));
        return builder.build();
    }
}
