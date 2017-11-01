package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 27/5/17.
 */

public class UserDetailsRequest {

    String email;
    String store_id;

    @Override
    public String toString() {
        return "UserDetailsRequest{" +
                "email='" + email + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
