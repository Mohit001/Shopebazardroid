package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 6/6/17.
 */

public class NotificationListResponse {


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

    public static class List {
        private String id;
        private String customer_id;
        private String notification_type;
        private String message;
        private String img;
        private String isreply;
        private String incrementorder_id;
        private String created_date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getNotification_type() {
            return notification_type;
        }

        public void setNotification_type(String notification_type) {
            this.notification_type = notification_type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIsreply() {
            return isreply;
        }

        public void setIsreply(String isreply) {
            this.isreply = isreply;
        }

        public String getIncrementorder_id() {
            return incrementorder_id;
        }

        public void setIncrementorder_id(String incrementorder_id) {
            this.incrementorder_id = incrementorder_id;
        }

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }
    }

    public static class Result {
        private String message;
        private ArrayList<List> list;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ArrayList<List> getList() {
            return list;
        }

        public void setList(ArrayList<List> list) {
            this.list = list;
        }
    }
}
