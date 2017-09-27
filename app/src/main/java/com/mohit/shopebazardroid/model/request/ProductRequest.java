package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 1/8/16.
 */
public class ProductRequest {

    String store_id;
    String customer_id;
    String catid;
    String pagesize;
    String page;
    String json_filter;

    @Override
    public String toString() {
        return "ProductRequest{" +
                "store_id='" + store_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", catid='" + catid + '\'' +
                ", pagesize='" + pagesize + '\'' +
                ", page='" + page + '\'' +
                ", json_filter='" + json_filter + '\'' +
                '}';
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

    public String getJson_filter() {
        return json_filter;
    }

    public void setJson_filter(String json_filter) {
        this.json_filter = json_filter;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
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
}
