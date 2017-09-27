package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 27/7/16.
 */
public class OrderHistory {

    int id;
    int index;
    String orderno;
    String orderdate;
    String amount;
    String status;

    @Override
    public String toString() {
        return "OrderHistory{" +
                "customer_address_id=" + id +
                ", index=" + index +
                ", orderno='" + orderno + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
