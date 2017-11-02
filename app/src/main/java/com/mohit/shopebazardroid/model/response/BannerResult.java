package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 10/8/16.
 */
public class BannerResult {

    ArrayList<BannerEntity> Banner;
    String message;

    @Override
    public String toString() {
        return "BannerResult{" +
                "Banner=" + Banner +
                ", message='" + message + '\'' +
                '}';
    }

    public ArrayList<BannerEntity> getBanner() {
        return Banner;
    }

    public void setBanner(ArrayList<BannerEntity> banner) {
        Banner = banner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
