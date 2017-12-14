package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 26/5/17.
 */

public class SearchRequest {

    String search;
    String store_id;
    String customer_id;

    @Override
    public String toString() {
        return "SearchRequest{" +
                "search='" + search + '\'' +
                ", store_id='" + store_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                '}';
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
