package com.mohit.shopebazardroid.model.request;

/**
 * Created by root on 10/8/16.
 */
public class CartProductOptionRequest
{
    String option_id;
    String value_id;

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public String getValue_id() {
        return value_id;
    }

    public void setValue_id(String value_id) {
        this.value_id = value_id;
    }
}
