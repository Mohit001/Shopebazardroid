package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 23/8/16.
 */
public class VerifyCouponRequest {

    String shoppingcartid;
    String couponcode;
    String action;

    @Override
    public String toString() {
        return "VerifyCouponRequest{" +
                "shoppingcartid='" + shoppingcartid + '\'' +
                ", couponcode='" + couponcode + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    public String getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(String shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public String getCouponcode() {
        return couponcode;
    }

    public void setCouponcode(String couponcode) {
        this.couponcode = couponcode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
