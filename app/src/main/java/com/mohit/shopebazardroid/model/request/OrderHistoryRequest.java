package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 6/6/17.
 */

public class OrderHistoryRequest {

    String customer_id;
    String store_id;

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
