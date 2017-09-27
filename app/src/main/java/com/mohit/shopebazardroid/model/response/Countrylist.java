package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 30/7/16.
 */
public class Countrylist {


    private String countrycode;
    private String value;

    @Override
    public String toString() {
        return "Countrylist{" +
                "countrycode='" + countrycode + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Countrylist(String countrycode, String value) {
        this.countrycode = countrycode;
        this.value = value;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
