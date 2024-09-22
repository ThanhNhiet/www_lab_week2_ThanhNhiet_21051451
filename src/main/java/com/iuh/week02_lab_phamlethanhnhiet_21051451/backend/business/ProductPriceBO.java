package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories.ProductPriceRepo;
import jakarta.inject.Inject;

import java.util.List;

public class ProductPriceBO {
    @Inject
    private ProductPriceRepo productPriceRepo;

    public void saveProductPrice(ProductPrice productPrice) {
        productPriceRepo.save(productPrice);
    }

    public void updateProductPrice(ProductPrice productPrice) {
        productPriceRepo.update(productPrice);
    }

    public void deleteProductPrice(ProductPrice productPrice) {
        productPriceRepo.delete(productPrice);
    }

    public ProductPrice findProductPriceById(Long id) {
        return productPriceRepo.findById(id);
    }

    public List<ProductPrice> findAllProductPrices() {
        return productPriceRepo.findAll();
    }
}
