package com.mohit.shopebazardroid.model.response;

/**
 * Created by root on 6/7/16.
 */
public class LoginResponse
{
    private Result result;
    private String status;
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "result=" + result +
                ", status='" + status + '\'' +
                ", CheckCustomerSubscriptionStatusResult='" + CheckCustomerSubscriptionStatusResult + '\'' +
                '}';
    }

    public Result getResult ()
    {
        return result;
    }

    public void setResult (Result result)
    {
        this.result = result;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

}
