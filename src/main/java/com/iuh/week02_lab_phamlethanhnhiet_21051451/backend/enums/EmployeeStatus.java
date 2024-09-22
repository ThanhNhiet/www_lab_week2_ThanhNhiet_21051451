package com.iuh.week02_lab_phamlethanhnhiet_21051451.backend.enums;

public enum EmployeeStatus {
    ACTIVE(1),
    INACTIVE(2),
    TERMINATED(3);

    private final int value;

    EmployeeStatus(int value) {
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

