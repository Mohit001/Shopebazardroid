package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 2/8/17.
 */

public class AddRemoveWishListRequest {

    private String product_id;
    private String customer_id;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
