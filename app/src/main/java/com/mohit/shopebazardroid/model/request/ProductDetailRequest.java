package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 30/5/17.
 */

public class ProductDetailRequest {

    String product_id;
    String customer_Id;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }
}
