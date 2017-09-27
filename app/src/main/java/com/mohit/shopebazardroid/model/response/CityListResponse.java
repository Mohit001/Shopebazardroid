package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 27/6/17.
 */

public class CityListResponse {


    private Result result;
    private String status;
    private int CheckCustomerSubscriptionStatusResult;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(int CheckCustomerSubscriptionStatusResult) {
        this.CheckCustomerSubscriptionStatusResult = CheckCustomerSubscriptionStatusResult;
    }

    public static class Cities {

        public Cities(String cityname) {
            this.cityname = cityname;
        }

        private String cityname;

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }
    }

    public static class Result {
        private String message;
        private ArrayList<Cities> cities;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ArrayList<Cities> getCities() {
            return cities;
        }

        public void setCities(ArrayList<Cities> cities) {
            this.cities = cities;
        }
    }
}
