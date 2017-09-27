package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 4/8/16.
 */
public class ShippingResult {

    String message;
    ArrayList<ShippingMethod> shppinglist;

    @Override
    public String toString() {
        return "ShippingResult{" +
                "message='" + message + '\'' +
                ", shppinglist=" + shppinglist +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ShippingMethod> getShppinglist() {
        return shppinglist;
    }

    public void setShppinglist(ArrayList<ShippingMethod> shppinglist) {
        this.shppinglist = shppinglist;
    }
}
