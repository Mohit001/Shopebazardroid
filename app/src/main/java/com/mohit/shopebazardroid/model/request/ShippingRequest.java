package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 4/8/16.
 */
public class ShippingRequest {

    String cartid;
    String action;
    String code;


    @Override
    public String toString() {
        return "ShippingRequest{" +
                "cartid='" + cartid + '\'' +
                ", action='" + action + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
    }
}
