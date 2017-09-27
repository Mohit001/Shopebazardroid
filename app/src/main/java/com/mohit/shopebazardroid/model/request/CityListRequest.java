package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 27/6/17.
 */

public class CityListRequest {

    String countrycode;
    String regionId;

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
