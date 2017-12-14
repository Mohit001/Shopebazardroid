package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 26/5/17.
 */

public class JsonFilter {

    private String code;
    private String value;

    @Override
    public String toString() {
        return "JsonFilter{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
