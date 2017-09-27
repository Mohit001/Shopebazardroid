package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by msp on 1/8/16.
 */
public class ProductResult implements Serializable {

    private String message;
    private ArrayList<ProductEntity> productlist;
    private int total;
    private ArrayList<ProductFilterAttributes> filter_attributes;
    private ArrayList<JsonFilter> json_filter;


    @Override
    public String toString() {
        return "ProductResult{" +
                "message='" + message + '\'' +
                ", productlist=" + productlist +
                ", total=" + total +
                ", filter_attributes=" + filter_attributes +
                ", json_filter=" + json_filter +
                '}';
    }

    public ArrayList<JsonFilter> getJson_filter() {
        return json_filter;
    }

    public void setJson_filter(ArrayList<JsonFilter> json_filter) {
        this.json_filter = json_filter;
    }

    public ArrayList<ProductFilterAttributes> getFilter_attributes() {
        return filter_attributes;
    }

    public void setFilter_attributes(ArrayList<ProductFilterAttributes> filter_attributes) {
        this.filter_attributes = filter_attributes;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ProductEntity> getProductlist() {
        return productlist;
    }

    public void setProductlist(ArrayList<ProductEntity> productlist) {
        this.productlist = productlist;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
