package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 12/8/17.
 */

public class AttributesInfo {

    private String label;
    private String value;

    @Override
    public String toString() {
        return "AttributesInfo{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
