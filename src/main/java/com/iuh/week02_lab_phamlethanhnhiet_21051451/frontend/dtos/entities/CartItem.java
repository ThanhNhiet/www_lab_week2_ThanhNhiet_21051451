package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.dtos.entities;

public class CartItem {
    private long pid;
    private long quantity;

    public CartItem() {
    }

    public CartItem(long pid, long quantity) {
        this.pid = pid;
        this.quantity = quantity;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem cartItem)) return false;

        return pid == cartItem.pid;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(pid);
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "pid=" + pid +
                ", quantity=" + quantity +
                '}';
    }
}
