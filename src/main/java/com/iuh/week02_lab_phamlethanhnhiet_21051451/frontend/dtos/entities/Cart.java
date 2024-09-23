package com.iuh.week02_lab_phamlethanhnhiet_21051451.frontend.dtos.entities;

import java.util.ArrayList;

public class Cart {
    private ArrayList<CartItem> cart;

    public Cart() {
        cart = new ArrayList<>();
    }

    public void addProduct(CartItem p) {
        if (cart.contains(p)) {
            CartItem pp = cart.get(cart.indexOf(p));
            pp.setQuantity(pp.getQuantity() + 1);
        } else
            cart.add(p);
    }

    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public void setCart(ArrayList<CartItem> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        String s = "";
        for (CartItem p : cart)
            s += p + "\n";
        return s;
    }
}
