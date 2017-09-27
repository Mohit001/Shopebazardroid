package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 31/5/17.
 */

public class CustomerReviewList {


    private Result result;
    private String status;
    private String CheckCustomerSubscriptionStatusResult;

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

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String CheckCustomerSubscriptionStatusResult) {
        this.CheckCustomerSubscriptionStatusResult = CheckCustomerSubscriptionStatusResult;
    }

    public static class Result {
        private String message;
        private ArrayList<ReviewList> reviewlist;
        private int total;
        private String filter_attributes;
        private String json_filter;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ArrayList<ReviewList> getReviewlist() {
            return reviewlist;
        }

        public void setReviewlist(ArrayList<ReviewList> reviewlist) {
            this.reviewlist = reviewlist;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getFilter_attributes() {
            return filter_attributes;
        }

        public void setFilter_attributes(String filter_attributes) {
            this.filter_attributes = filter_attributes;
        }

        public String getJson_filter() {
            return json_filter;
        }

        public void setJson_filter(String json_filter) {
            this.json_filter = json_filter;
        }
    }
}
