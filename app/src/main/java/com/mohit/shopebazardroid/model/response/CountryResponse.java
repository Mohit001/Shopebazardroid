package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 30/7/16.
 */
public class CountryResponse {

    CountryResult result;
    String status;
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "CountryResponse{" +
                "result=" + result +
                ", status='" + status + '\'' +
                ", CheckCustomerSubscriptionStatusResult='" + CheckCustomerSubscriptionStatusResult + '\'' +
                '}';
    }

    public CountryResult getResult() {
        return result;
    }

    public void setResult(CountryResult result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
