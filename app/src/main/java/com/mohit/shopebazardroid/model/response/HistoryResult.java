package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 8/8/16.
 */
public class HistoryResult {

    private String message;
    private int total;
    ArrayList<HistoryEntity> orderlist;

    @Override
    public String toString() {
        return "HistoryResult{" +
                "message='" + message + '\'' +
                ", total=" + total +
                ", orderlist=" + orderlist +
                '}';
    }

    public ArrayList<HistoryEntity> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(ArrayList<HistoryEntity> orderlist) {
        this.orderlist = orderlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
