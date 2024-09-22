package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.ProductPrice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ProductPriceRepo {
    @PersistenceContext(unitName = "my_pu")
    private EntityManager em;
    public void save(ProductPrice productPrice) {
        em.persist(productPrice);
    }
    public ProductPrice findById(Long id) {
        return em.find(ProductPrice.class, id);
    }
    public List<ProductPrice> findAll() {
        return em.createQuery("select pp from ProductPrice pp", ProductPrice.class).getResultList();
    }
    public void delete(ProductPrice productPrice) {
        em.remove(productPrice);
    }
    public void update(ProductPrice productPrice) {
        em.merge(productPrice);
    }
}
