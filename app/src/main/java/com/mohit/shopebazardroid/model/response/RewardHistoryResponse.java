package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 3/7/17.
 */

public class RewardHistoryResponse {


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

    public static class Transactions {
        private String history_id;
        private String customer_id;
        private String type_of_transaction;
        private String amount;
        private String balance;
        private String transaction_detail;
        private String transaction_time;
        private String history_order_id;
        private String expired_day;
        private String expired_time;
        private String point_remaining;
        private String check_time;
        private String status;
        private String status_check;

        public String getHistory_id() {
            return history_id;
        }

        public void setHistory_id(String history_id) {
            this.history_id = history_id;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getType_of_transaction() {
            return type_of_transaction;
        }

        public void setType_of_transaction(String type_of_transaction) {
            this.type_of_transaction = type_of_transaction;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getTransaction_detail() {
            return transaction_detail;
        }

        public void setTransaction_detail(String transaction_detail) {
            this.transaction_detail = transaction_detail;
        }

        public String getTransaction_time() {
            return transaction_time;
        }

        public void setTransaction_time(String transaction_time) {
            this.transaction_time = transaction_time;
        }

        public String getHistory_order_id() {
            return history_order_id;
        }

        public void setHistory_order_id(String history_order_id) {
            this.history_order_id = history_order_id;
        }

        public String getExpired_day() {
            return expired_day;
        }

        public void setExpired_day(String expired_day) {
            this.expired_day = expired_day;
        }

        public String getExpired_time() {
            return expired_time;
        }

        public void setExpired_time(String expired_time) {
            this.expired_time = expired_time;
        }

        public String getPoint_remaining() {
            return point_remaining;
        }

        public void setPoint_remaining(String point_remaining) {
            this.point_remaining = point_remaining;
        }

        public String getCheck_time() {
            return check_time;
        }

        public void setCheck_time(String check_time) {
            this.check_time = check_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_check() {
            return status_check;
        }

        public void setStatus_check(String status_check) {
            this.status_check = status_check;
        }
    }

    public static class Result {
        private String message;
        private ArrayList<Transactions> transactions;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ArrayList<Transactions> getTransactions() {
            return transactions;
        }

        public void setTransactions(ArrayList<Transactions> transactions) {
            this.transactions = transactions;
        }
    }
}
