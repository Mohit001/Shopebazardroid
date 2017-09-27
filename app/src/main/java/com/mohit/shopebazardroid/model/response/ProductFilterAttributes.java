package com.mohit.shopebazardroid.model.response;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msp on 13/8/16.
 */
public class ProductFilterAttributes  implements Serializable, ParentListItem{

    private String title;
    private String code;
    ArrayList<FilterAttributesOption>   options;

    @Override
    public String toString() {
        return "ProductFilterAttributes{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", options=" + options +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<FilterAttributesOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<FilterAttributesOption> options) {
        this.options = options;
    }

    @Override
    public List<FilterAttributesOption> getChildItemList() {
        return options;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return true;
    }
}
