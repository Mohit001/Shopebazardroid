package com.mohit.shopebazardroid.model.response;

import java.io.*;
import java.util.*;

/**
 * Created by root on 7/7/16.
 */
public class CategoryListItemResponse implements Serializable
{
    int category_id;
    String name;
    ArrayList<SubCategoryListItemResponse> children =new ArrayList<>();
    String CheckCustomerSubscriptionStatusResult;

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    @Override
    public String toString() {
        return "CategoryListItemResponse{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                ", children=" + children +
                ", CheckCustomerSubscriptionStatusResult='" + CheckCustomerSubscriptionStatusResult + '\'' +
                '}';
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubCategoryListItemResponse> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<SubCategoryListItemResponse> children) {
        this.children = children;
    }
}
