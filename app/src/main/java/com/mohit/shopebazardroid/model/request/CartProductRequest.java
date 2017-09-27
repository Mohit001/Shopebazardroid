package com.mohit.shopebazardroid.model.request;

import java.util.*;

/**
 * Created by root on 10/8/16.
 */
public class CartProductRequest
{
    String product_id;
    String type;
    String qty;

    ArrayList<CartProductOptionRequest> custom_option_id =new ArrayList<>();

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public ArrayList<CartProductOptionRequest> getCustom_option_id() {
        return custom_option_id;
    }

    public void setCustom_option_id(ArrayList<CartProductOptionRequest> custom_option_id) {
        this.custom_option_id = custom_option_id;
    }
}
