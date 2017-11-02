package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;

/**
 * Created by msp on 13/8/16.
 */
public class FilterAttributesOption implements Serializable {

    private String label;
    private String value;
    private String count;
    private boolean isChecked;
    private int parentIndex;
    private int childIndex;

    @Override
    public String toString() {
        return "FilterAttributesOption{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", count='" + count + '\'' +
                ", isChecked=" + isChecked +
                ", parentIndex=" + parentIndex +
                ", childIndex=" + childIndex +
                '}';
    }

    public int getParentIndex() {
        return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public int getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(int childIndex) {
        this.childIndex = childIndex;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
