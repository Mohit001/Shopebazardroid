package com.mohit.shopebazardroid.model.request;

import java.util.*;

/**
 * Created by root on 10/8/16.
 */
public class CartItemsRequest
{
    String totalitems;
    ArrayList<CartProductRequest> cartitems =new ArrayList<>();

    public String getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(String totalitems) {
        this.totalitems = totalitems;
    }

    public ArrayList<CartProductRequest> getCartitems() {
        return cartitems;
    }

    public void setCartitems(ArrayList<CartProductRequest> cartitems) {
        this.cartitems = cartitems;
    }
}
