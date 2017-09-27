package com.mohit.shopebazardroid.model.response;

import java.util.*;

/**
 * Created by root on 7/7/16.
 */
public class CategoryResponseResult
{
    ArrayList<CategoryListItemResponse> cats=new ArrayList<CategoryListItemResponse>();
    public ArrayList<CategoryListItemResponse> getCats() {
        return cats;
    }
    public void setCats(ArrayList<CategoryListItemResponse> cats) {
        this.cats = cats;
    }
}
