package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 30/7/16.
 */
public class CountryResult {

    String message;
    ArrayList<Countrylist> countrylist;

    @Override
    public String toString() {
        return "CountryResult{" +
                "message='" + message + '\'' +
                ", countrylist=" + countrylist +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Countrylist> getCountrylist() {
        return countrylist;
    }

    public void setCountrylist(ArrayList<Countrylist> countrylist) {
        this.countrylist = countrylist;
    }
}
