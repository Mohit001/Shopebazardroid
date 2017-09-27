package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 12/6/17.
 */

public class CreateOrderRequest {

    private String shoppingcartid;
    private String ishideprice;
    private String store_id;
    private String user_id;

    private String tinterval_id;
    private String date;
    private String comment;

    public String getTinterval_id() {
        return tinterval_id;
    }

    public void setTinterval_id(String tinterval_id) {
        this.tinterval_id = tinterval_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(String shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public String getIshideprice() {
        return ishideprice;
    }

    public void setIshideprice(String ishideprice) {
        this.ishideprice = ishideprice;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
