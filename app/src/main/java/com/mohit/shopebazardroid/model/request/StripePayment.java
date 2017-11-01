package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 8/9/16.
 */
public class StripePayment {

    String shoppingcartid;
    String action;
    String code;
    String cc_owner;
    String cc_type;
    String cc_number;
    String cc_exp_month;
    String cc_exp_year;
    String cc_cid;

    @Override
    public String toString() {
        return "StripePayment{" +
                "shoppingcartid='" + shoppingcartid + '\'' +
                ", action='" + action + '\'' +
                ", code='" + code + '\'' +
                ", cc_owner='" + cc_owner + '\'' +
                ", cc_type='" + cc_type + '\'' +
                ", cc_number='" + cc_number + '\'' +
                ", cc_exp_month='" + cc_exp_month + '\'' +
                ", cc_exp_year='" + cc_exp_year + '\'' +
                ", cc_cid='" + cc_cid + '\'' +
                '}';
    }

    public String getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(String shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCc_owner() {
        return cc_owner;
    }

    public void setCc_owner(String cc_owner) {
        this.cc_owner = cc_owner;
    }

    public String getCc_type() {
        return cc_type;
    }

    public void setCc_type(String cc_type) {
        this.cc_type = cc_type;
    }

    public String getCc_number() {
        return cc_number;
    }

    public void setCc_number(String cc_number) {
        this.cc_number = cc_number;
    }

    public String getCc_exp_month() {
        return cc_exp_month;
    }

    public void setCc_exp_month(String cc_exp_month) {
        this.cc_exp_month = cc_exp_month;
    }

    public String getCc_exp_year() {
        return cc_exp_year;
    }

    public void setCc_exp_year(String cc_exp_year) {
        this.cc_exp_year = cc_exp_year;
    }

    public String getCc_cid() {
        return cc_cid;
    }

    public void setCc_cid(String cc_cid) {
        this.cc_cid = cc_cid;
    }
}
