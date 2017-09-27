package com.mohit.shopebazardroid.model.response;


public class CartResult {

    private CartDetails cart;
    private String message;

    public CartDetails getCart() {
        return cart;
    }

    public void setCart(CartDetails cart) {
        this.cart = cart;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

