package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 2/9/16.
 */
public class SetCurrencyRequest {

    String shoppingcartid;
    String code;
    String user_id;

    @Override
    public String toString() {
        return "SetCurrencyRequest{" +
                "shoppingcartid='" + shoppingcartid + '\'' +
                ", code='" + code + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public String getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(String shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
