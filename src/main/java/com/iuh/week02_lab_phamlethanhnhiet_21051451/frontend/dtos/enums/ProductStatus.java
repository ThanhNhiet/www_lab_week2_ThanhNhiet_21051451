package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.dtos.enums;

public enum ProductStatus {
    ACTIVE(1),
    INACTIVE(2),
    TERMINATED(3);

    private final int value;

    ProductStatus(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
