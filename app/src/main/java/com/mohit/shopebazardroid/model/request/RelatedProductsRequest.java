package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 30/5/17.
 */

public class RelatedProductsRequest {

    String productid;
    String customer_id;
    String store_id;

    @Override
    public String toString() {
        return "RelatedProductsRequest{" +
                "productid='" + productid + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
