package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 10/8/16.
 */
public class BannerResponse {

    String status;
    BannerResult result;
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "BannerResponse{" +
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

    public BannerResult getResult() {
        return result;
    }

    public void setResult(BannerResult result) {
        this.result = result;
    }
}
