package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 27/5/17.
 */

public class BestSellingRequest {

    private String customer_id;
    private String store_id;
    private String pagesize;
    private String page;

    @Override
    public String toString() {
        return "BestSellingRequest{" +
                "customer_id='" + customer_id + '\'' +
                ", store_id='" + store_id + '\'' +
                ", pagesize='" + pagesize + '\'' +
                ", page='" + page + '\'' +
                '}';
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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
