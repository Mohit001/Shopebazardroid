package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by root on 3/8/16.
 */
public class ProductConfigurableValueOption implements Serializable {
    private String default_label;
    private String store_label;
    private String is_percent;
    private boolean use_default_value;
    public String image;
    int product_super_attribute_id;
    String label;
    int value_id;
    float pricing_value;
    int value_index;
    private ArrayList<ProductConfigurableValueProducts> products = new ArrayList<>();


    public ArrayList<ProductConfigurableValueProducts> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductConfigurableValueProducts> products) {
        this.products = products;
    }

    public String getDefault_label() {
        return default_label;
    }

    public void setDefault_label(String default_label) {
        this.default_label = default_label;
    }

    public String getStore_label() {
        return store_label;
    }

    public void setStore_label(String store_label) {
        this.store_label = store_label;
    }

    public String getIs_percent() {
        return is_percent;
    }

    public void setIs_percent(String is_percent) {
        this.is_percent = is_percent;
    }

    public boolean isUse_default_value() {
        return use_default_value;
    }

    public void setUse_default_value(boolean use_default_value) {
        this.use_default_value = use_default_value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProduct_super_attribute_id() {
        return product_super_attribute_id;
    }

    public void setProduct_super_attribute_id(int product_super_attribute_id) {
        this.product_super_attribute_id = product_super_attribute_id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue_id() {
        return value_id;
    }

    public void setValue_id(int value_id) {
        this.value_id = value_id;
    }

    public float getPricing_value() {
        return pricing_value;
    }

    public void setPricing_value(float pricing_value) {
        this.pricing_value = pricing_value;
    }

    public int getValue_index() {
        return value_index;
    }

    public void setValue_index(int value_index) {
        this.value_index = value_index;
    }
}
