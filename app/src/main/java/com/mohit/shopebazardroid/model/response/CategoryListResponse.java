package com.mohit.shopebazardroid.model.response;

/**
 * Created by root on 7/7/16.
 */
public class CategoryListResponse
{
    String status;
    CategoryResponseResult result;
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "CategoryListResponse{" +
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

    public CategoryResponseResult getResult() {
        return result;
    }

    public void setResult(CategoryResponseResult result) {
        this.result = result;
    }
}
