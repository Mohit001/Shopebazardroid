package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 1/8/16.
 */
public class AddressResult {

    String message;
    ArrayList<Address> address;

    @Override
    public String toString() {
        return "AddressResult{" +
                "message='" + message + '\'' +
                ", address=" + address +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Address> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<Address> address) {
        this.address = address;
    }
}
