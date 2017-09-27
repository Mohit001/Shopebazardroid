package com.mohit.shopebazardroid.model.response;

import java.util.*;

/**
 * Created by root on 5/8/16.
 */
public class CartMediaItem {
    String url;
    ArrayList<String> types =new ArrayList<>();
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
}
