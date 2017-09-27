package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 23/7/16.
 */
public class SubcategoryEntity {

    String imageurl;

    @Override
    public String toString() {
        return "SubcategoryEntity{" +
                "imageurl='" + imageurl + '\'' +
                '}';
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
