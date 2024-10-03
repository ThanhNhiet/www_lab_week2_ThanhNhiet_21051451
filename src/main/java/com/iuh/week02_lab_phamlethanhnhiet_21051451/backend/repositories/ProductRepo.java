package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.repositories;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities.Product;
import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.enums.ProductStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class ProductRepo {
    @PersistenceContext(unitName = "my_pu")
    private EntityManager em;

    public void save(Product p) {
        em.persist(p);
    }

    public void update(Product p) {
        em.merge(p);
    }

    public void delete(Long id) {
        Product p = em.find(Product.class, id);
        em.remove(p);
    }

    public Product find(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public List<Product> findAllActive() {
        return em.createQuery("SELECT p FROM Product p WHERE p.status = :status", Product.class)
                .setParameter("status", ProductStatus.ACTIVE)
                .getResultList();
    }

    public List<Product> findByName(String name) {
        return em.createQuery("SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }
}
