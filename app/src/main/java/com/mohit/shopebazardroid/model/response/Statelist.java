package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 30/7/16.
 */
public class Statelist {

    private String region_id;
    private String value;

    public Statelist(String region_id, String value) {
        this.region_id = region_id;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Statelist{" +
                "region_id='" + region_id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
