package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 4/8/16.
 */
public class PaymentResult {

    private String message;
    private ArrayList<PaymentMethod> paymentlist;


    @Override
    public String toString() {
        return "PaymentResult{" +
                "message='" + message + '\'' +
                ", paymentlist=" + paymentlist +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<PaymentMethod> getPaymentlist() {
        return paymentlist;
    }

    public void setPaymentlist(ArrayList<PaymentMethod> paymentlist) {
        this.paymentlist = paymentlist;
    }
}
