package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 4/8/16.
 */
public class PaymentRequest {

    private String cartid;
    private String action;
    private String code;

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "cartid='" + cartid + '\'' +
                ", action='" + action + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
        this.cartid = cartid;
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
}
