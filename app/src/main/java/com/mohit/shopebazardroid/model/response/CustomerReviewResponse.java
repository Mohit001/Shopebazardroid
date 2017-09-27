package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 31/5/17.
 */

public class CustomerReviewResponse {


    private ArrayList<Result> result;
    private String status;
    private String CheckCustomerSubscriptionStatusResult;

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String CheckCustomerSubscriptionStatusResult) {
        this.CheckCustomerSubscriptionStatusResult = CheckCustomerSubscriptionStatusResult;
    }

    public static class Options {
        private String option_id;
        private String rating_id;
        private String code;
        private String value;
        private String position;

        public String getOption_id() {
            return option_id;
        }

        public void setOption_id(String option_id) {
            this.option_id = option_id;
        }

        public String getRating_id() {
            return rating_id;
        }

        public void setRating_id(String rating_id) {
            this.rating_id = rating_id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }

    public static class Result {
        private String rating_id;
        private String entity_id;
        private String rating_code;
        private String position;
        private ArrayList<Options> options;

        public String getRating_id() {
            return rating_id;
        }

        public void setRating_id(String rating_id) {
            this.rating_id = rating_id;
        }

        public String getEntity_id() {
            return entity_id;
        }

        public void setEntity_id(String entity_id) {
            this.entity_id = entity_id;
        }

        public String getRating_code() {
            return rating_code;
        }

        public void setRating_code(String rating_code) {
            this.rating_code = rating_code;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public ArrayList<Options> getOptions() {
            return options;
        }

        public void setOptions(ArrayList<Options> options) {
            this.options = options;
        }
    }
}
