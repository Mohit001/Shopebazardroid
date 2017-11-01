package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 6/6/17.
 */

public class AddCommentInOrderRequest {

    String increment_id;
    String comment;
    String order_status;

    public String getIncrement_id() {
        return increment_id;
    }

    public void setIncrement_id(String increment_id) {
        this.increment_id = increment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
}
