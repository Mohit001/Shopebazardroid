package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 1/8/16.
 */
public class AddressResponse {

    String status;
    AddressResult result;
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
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

    public AddressResult getResult() {
        return result;
    }

    public void setResult(AddressResult result) {
        this.result = result;
    }
}
