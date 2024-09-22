package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories.ProductRepo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@RequestScoped
public class ProductBO {

    @Inject
    private ProductRepo productRepo;

    @Transactional
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Transactional
    public void updateProduct(Product product) {
        if (product.getProductId() != null) {
            productRepo.update(product);
        } else {
            throw new IllegalArgumentException("Product ID không tồn tại");
        }
    }

    @Transactional
    public Product findProductById(Long id) {
        return productRepo.find(id);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepo.delete(id);
    }

    @Transactional
    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    @Transactional
    public List<Product> findProductByName(String name) {
        return productRepo.findByName(name);
    }
}
