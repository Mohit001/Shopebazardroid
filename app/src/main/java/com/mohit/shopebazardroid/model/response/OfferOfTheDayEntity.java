package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 22/7/16.
 */
public class OfferOfTheDayEntity {
    int id;
    int index;
    String imageurl;

    @Override
    public String toString() {
        return "OfferOfTheDayEntity{" +
                "customer_address_id=" + id +
                ", index=" + index +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
