package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 17/8/16.
 */
public class AddCartResult {

    String message;
    AddCartData data;

    @Override
    public String toString() {
        return "AddCartResult{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddCartData getData() {
        return data;
    }

    public void setData(AddCartData data) {
        this.data = data;
    }
}
