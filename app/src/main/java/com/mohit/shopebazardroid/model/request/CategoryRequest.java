package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 25/5/17.
 */

public class CategoryRequest {

    private String catid;

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "catid='" + catid + '\'' +
                '}';
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }
}
