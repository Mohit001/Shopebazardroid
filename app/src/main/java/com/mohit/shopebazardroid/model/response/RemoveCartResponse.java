package com.mohit.shopebazardroid.model.response;

/**
 * Created by root on 5/8/16.
 */
public class RemoveCartResponse {

    RemoveCartResultResponse result;
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
        return "CartResponse{" +
                ", status='" + status + '\'' +
                ", CheckCustomerSubscriptionStatusResult='" + CheckCustomerSubscriptionStatusResult + '\'' +
                '}';
    }

    public RemoveCartResultResponse getResult() {
        return result;
    }

    public void setResult(RemoveCartResultResponse result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
