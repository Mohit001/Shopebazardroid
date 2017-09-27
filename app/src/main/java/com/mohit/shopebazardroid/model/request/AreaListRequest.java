package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 27/6/17.
 */

public class AreaListRequest {

    String cityname;
    String countrycode;
    String regionId;

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

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
