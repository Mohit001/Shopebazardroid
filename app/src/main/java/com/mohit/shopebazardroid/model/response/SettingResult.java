package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 2/9/16.
 */
public class SettingResult {
    String message;

    @Override
    public String toString() {
        return "SettingResult{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
