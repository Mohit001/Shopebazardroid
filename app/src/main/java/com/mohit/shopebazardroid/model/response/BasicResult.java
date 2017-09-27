package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 17/8/16.
 */
public class BasicResult {

    String message;
    BasicSettings setting;
    ArrayList<BasicCMS> cms;

    @Override
    public String toString() {
        return "BasicResult{" +
                "message='" + message + '\'' +
                ", setting=" + setting +
                ", cms=" + cms +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BasicSettings getSetting() {
        return setting;
    }

    public void setSetting(BasicSettings setting) {
        this.setting = setting;
    }

    public ArrayList<BasicCMS> getCms() {
        return cms;
    }

    public void setCms(ArrayList<BasicCMS> cms) {
        this.cms = cms;
    }
}
