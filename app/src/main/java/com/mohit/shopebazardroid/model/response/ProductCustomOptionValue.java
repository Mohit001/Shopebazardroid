package com.mohit.shopebazardroid.model.response;

import java.io.*;

/**
 * Created by root on 3/8/16.
 */
public class ProductCustomOptionValue implements Serializable
{
    int option_type_id;
    int value_id;
    String title;
    float price;
    String sku;
    int sort_order;

    public int getOption_type_id() {
        return option_type_id;
    }

    public void setOption_type_id(int option_type_id) {
        this.option_type_id = option_type_id;
    }

    public int getValue_id() {
        return value_id;
    }

    public void setValue_id(int value_id) {
        this.value_id = value_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getSort_order() {
        return sort_order;
    }

    public void setSort_order(int sort_order) {
        this.sort_order = sort_order;
    }
}
