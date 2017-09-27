package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by msp on 1/8/16.
 */
public class CategoryChildrens implements Serializable{

    public static String KEY_OBJECT = "categorychildren";
    public static String KEY_ID = "child_id";
    public static String KEY_NAME = "child_name";
    public static String KEY_TYPE = "type"; // 0=category/subcategory product
                                            // 1 = banner product


    private String name;
    private String id;
    private String thumb;
    ArrayList<CategoryChildrens> children;
    private String image;

    @Override
    public String toString() {
        return "CategoryChildrens{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", thumb='" + thumb + '\'' +
                ", children=" + children +
                ", image='" + image + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public ArrayList<CategoryChildrens> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<CategoryChildrens> children) {
        this.children = children;
    }
}
