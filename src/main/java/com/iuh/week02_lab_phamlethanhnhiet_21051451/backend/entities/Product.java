package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.entities;

import com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.enums.ProductStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @Column(name = "unit")
    private String unit;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrices;

    public Product() {
    }

    public Product(String name, String description, String manufacturer, ProductStatus status, String unit) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.status = status;
        this.unit = unit;
    }

//    public Product(Long productId, String name, String description, String manufacturer, ProductStatus status, String unit) {
//        this.productId = productId;
//        this.name = name;
//        this.description = description;
//        this.manufacturer = manufacturer;
//        this.status = status;
//        this.unit = unit;
//    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", status=" + status +
                ", unit='" + unit + '\'' +
                '}';
    }
}
