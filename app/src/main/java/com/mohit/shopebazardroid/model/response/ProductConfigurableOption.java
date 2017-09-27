package com.mohit.shopebazardroid.model.response;

import java.io.*;
import java.util.*;

/**
 * Created by root on 3/8/16.
 */
public class ProductConfigurableOption implements Serializable
{
    int id;
    String label;
    int attribute_id;
    ArrayList<ProductConfigurableValueOption> values=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<ProductConfigurableValueOption> getValues() {
        return values;
    }

    public void setValues(ArrayList<ProductConfigurableValueOption> values) {
        this.values = values;
    }

    public int getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(int attribute_id) {
        this.attribute_id = attribute_id;
    }
}
