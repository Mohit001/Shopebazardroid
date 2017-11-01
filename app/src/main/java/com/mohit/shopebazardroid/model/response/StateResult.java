package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 30/7/16.
 */
public class StateResult {
    String message;
    ArrayList<Statelist> statelist;


    @Override
    public String toString() {
        return "StateResult{" +
                "message='" + message + '\'' +
                ", statelist=" + statelist +
                '}';
    }

    public ArrayList<Statelist> getStatelist() {
        return statelist;
    }

    public void setStatelist(ArrayList<Statelist> statelist) {
        this.statelist = statelist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
