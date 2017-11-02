package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 3/7/17.
 */

public class RewardsResponse {


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
        private String rewardpoints;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRewardpoints() {
            return rewardpoints;
        }

        public void setRewardpoints(String rewardpoints) {
            this.rewardpoints = rewardpoints;
        }
    }
}
