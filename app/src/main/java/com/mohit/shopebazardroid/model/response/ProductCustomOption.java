package com.mohit.shopebazardroid.model.response;

import java.io.*;
import java.util.*;

/**
 * Created by root on 3/8/16.
 */
public class ProductCustomOption implements Serializable
{
    int option_id;
    String title;
    ArrayList<ProductCustomOptionValue> value_option =new ArrayList<>();

    public int getOption_id() {
        return option_id;
    }

    public void setOption_id(int option_id) {
        this.option_id = option_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ProductCustomOptionValue> getValue_option() {
        return value_option;
    }

    public void setValue_option(ArrayList<ProductCustomOptionValue> value_option) {
        this.value_option = value_option;
    }
}
