package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 2/9/16.
 */
public class SettingResponse {
    SettingResult result;
    String status;
    String CheckCustomerSubscriptionStatusResult;

    @Override
    public String toString() {
        return "SettingResponse{" +
                "result=" + result +
                ", status='" + status + '\'' +
                ", CheckCustomerSubscriptionStatusResult='" +
                CheckCustomerSubscriptionStatusResult + '\'' +
                '}';
    }

    public SettingResult getResult() {
        return result;
    }

    public void setResult(SettingResult result) {
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

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }
}
