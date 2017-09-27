package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 23/7/16.
 */
public class OfferOfTheDayProductEntity {

    String imageurl;

    @Override
    public String toString() {
        return "OfferOfTheDayProductEntity{" +
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
