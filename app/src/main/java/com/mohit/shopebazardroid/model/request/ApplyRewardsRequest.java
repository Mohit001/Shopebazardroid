package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 3/7/17.
 */

public class ApplyRewardsRequest {

    String shoppingcartid;
    String store_id;
    String rewardpoints;
    String customer_id;


    public String getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(String shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getRewardpoints() {
        return rewardpoints;
    }

    public void setRewardpoints(String rewardpoints) {
        this.rewardpoints = rewardpoints;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }
}
