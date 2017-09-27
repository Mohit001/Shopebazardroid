package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 8/9/17.
 */

public class ReorderRequest {
    String orderid;
    String customer_id;
    String store_id;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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
