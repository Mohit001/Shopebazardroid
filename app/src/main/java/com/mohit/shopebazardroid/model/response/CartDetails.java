package com.mohit.shopebazardroid.model.response;

import java.util.*;

/**
 * Created by root on 4/8/16.
 */
public class CartDetails {
    int store_id;
    int quote_id;
    int items_qty;
    float subtotal;
    int items_count;
    ArrayList<CartItems> items = new ArrayList<>();

    public ArrayList<CartItems> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItems> items) {
        this.items = items;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public int getItems_qty() {
        return items_qty;
    }

    public void setItems_qty(int items_qty) {
        this.items_qty = items_qty;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public int getItems_count() {
        return items_count;
    }

    public void setItems_count(int items_count) {
        this.items_count = items_count;
    }
}
