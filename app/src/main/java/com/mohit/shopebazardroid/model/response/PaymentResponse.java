package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 4/8/16.
 */
public class PaymentResponse {

    private String status;
    private PaymentResult result;
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
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

    public PaymentResult getResult() {
        return result;
    }

    public void setResult(PaymentResult result) {
        this.result = result;
    }
}
