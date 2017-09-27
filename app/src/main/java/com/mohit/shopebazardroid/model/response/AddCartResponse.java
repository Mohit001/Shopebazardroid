package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 17/8/16.
 */
public class AddCartResponse {
    String status;
    AddCartResult result;
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "AddCartResponse{" +
                "status='" + status + '\'' +
                ", result=" + result +
                ", CheckCustomerSubscriptionStatusResult='" + CheckCustomerSubscriptionStatusResult + '\'' +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddCartResult getResult() {
        return result;
    }

    public void setResult(AddCartResult result) {
        this.result = result;
    }
}
