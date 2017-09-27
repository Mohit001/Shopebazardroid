package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 1/8/16.
 */
public class CategoryResult {

    String message;
    ArrayList<CategoryChildrens> cats;

    @Override
    public String toString() {
        return "CategoryResult{" +
                "message='" + message + '\'' +
                ", cats=" + cats +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<CategoryChildrens> getCats() {
        return cats;
    }

    public void setCats(ArrayList<CategoryChildrens> cats) {
        this.cats = cats;
    }
}
