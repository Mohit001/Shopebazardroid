package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 25/5/17.
 */

public class StoreListRequest {

    private String store_id;
    private String customer_id;

    @Override
    public String toString() {
        return "StoreListRequest{" +
                "store_id='" + store_id + '\'' +
                ", customer_Id='" + customer_id + '\'' +
                '}';
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCustomer_Id() {
        return customer_id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_id = customer_Id;
    }
}
