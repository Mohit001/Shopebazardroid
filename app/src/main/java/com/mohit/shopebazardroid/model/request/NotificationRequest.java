package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 17/8/16.
 */
public class NotificationRequest {

    String userid;
    String status;

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "userid='" + userid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
