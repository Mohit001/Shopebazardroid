package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 5/8/16.
 */
public class OrderReviewResult {

    String message;
    OrderReviewEntity cart;
    HistoryEntity orderinfo;
    ArrayList<OrderTotals> totals;
    String orderid;


    @Override
    public String toString() {
        return "OrderReviewResult{" +
                "message='" + message + '\'' +
                ", cart=" + cart +
                ", orderinfo=" + orderinfo +
                ", totals=" + totals +
                ", orderid='" + orderid + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderReviewEntity getCart() {
        return cart;
    }

    public void setCart(OrderReviewEntity cart) {
        this.cart = cart;
    }

    public HistoryEntity getOrderinfo() {
        return orderinfo;
    }

    public void setOrderinfo(HistoryEntity orderinfo) {
        this.orderinfo = orderinfo;
    }

    public ArrayList<OrderTotals> getTotals() {
        return totals;
    }

    public void setTotals(ArrayList<OrderTotals> totals) {
        this.totals = totals;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
