package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.business;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.dtos.ProductDTO;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories.ProductPriceRepo;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories.ProductRepo;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class ProductBO {

    @Inject
    private ProductRepo productRepo;

    @Inject
    private ProductPriceRepo productPriceRepo;

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

    //Active products
//    @Transactional
//    public List<ProductDTO> getProductsActive() {
//        List<Product> products = productRepo.findAllActive();
//        List<ProductDTO> dtos= new ArrayList<>();
//        for (Product product : products) {
//            String status = product.getStatus().toString();
//            double price = productPriceRepo.findById(product.getProductId()).getPrice();
//            ProductDTO productDto = new ProductDTO(
//                    product.getProductId(),
//                    product.getName(),
//                    product.getDescription(),
//                    product.getManufacturer(),
//                    product.getStatus().toString(),
//                    product.getUnit(),
//                    price
//            );
//            dtos.add(productDto);
//        }
//        return dtos;
//    }

    //All products
    @Transactional
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepo.findAll();
        List<ProductPrice> productPrices = productPriceRepo.findAll();
        List<ProductDTO> dtos= new ArrayList<>();
        for (Product product : products) {
            String status = product.getStatus().toString();
            boolean priceFound = false;
            for(ProductPrice productPrice : productPrices) {
                if(productPrice.getProduct().getProductId().equals(product.getProductId())) {
                    priceFound = true;
                    ProductDTO productDto = new ProductDTO(
                            product.getProductId(),
                            product.getName(),
                            product.getDescription(),
                            product.getManufacturer(),
                            product.getStatus().toString(),
                            product.getUnit(),
                            productPrice.getPrice()
                    );
                    dtos.add(productDto);
                    break;
                }
            }
            if(!priceFound) {
                ProductDTO productDto = new ProductDTO(
                        product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getManufacturer(),
                        product.getStatus().toString(),
                        product.getUnit(),
                        0
                );
                dtos.add(productDto);
            }
        }
        return dtos;
    }
}
