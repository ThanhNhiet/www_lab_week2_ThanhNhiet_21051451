package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.dtos;

public class ProductDTO {
    private Long id;

    private String name;

    private String description;

    private String manufacturer;

    private String status;

    private String unit;

    private double price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String description, String manufacturer, String status, String unit, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.status = status;
        this.unit = unit;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", status=" + status +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                '}';
    }
}
