package com.mohit.shopebazardroid.model.request;

/**
 * Created by root on 8/7/16.
 */
public class CustomOptionRequest
{
    int custom_title_id;
    String custom_title;
    int custom_title_value_id;
    String custom_title_value;
    float price;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCustom_title_id() {
        return custom_title_id;
    }

    public void setCustom_title_id(int custom_title_id) {
        this.custom_title_id = custom_title_id;
    }

    public String getCustom_title() {
        return custom_title;
    }

    public void setCustom_title(String custom_title) {
        this.custom_title = custom_title;
    }

    public int getCustom_title_value_id() {
        return custom_title_value_id;
    }

    public void setCustom_title_value_id(int custom_title_value_id) {
        this.custom_title_value_id = custom_title_value_id;
    }

    public String getCustom_title_value() {
        return custom_title_value;
    }

    public void setCustom_title_value(String custom_title_value) {
        this.custom_title_value = custom_title_value;
    }
}

