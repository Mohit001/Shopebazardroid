package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 17/8/16.
 */
public class StoreList {


    private String storeid;
    private String storename;
    private String root_category_id;
    private String is_default;

    @Override
    public String toString() {
        return "StoreList{" +
                "storeid='" + storeid + '\'' +
                ", storename='" + storename + '\'' +
                ", root_category_id='" + root_category_id + '\'' +
                ", is_default=" + is_default +
                '}';
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getRoot_category_id() {
        return root_category_id;
    }

    public void setRoot_category_id(String root_category_id) {
        this.root_category_id = root_category_id;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }
}
