package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 23/7/16.
 */
public class CartEntity {

    int id;
    int index;
    String imageurl;
    int quentity;
    String name;
    String size;
    double latestprice;
    double oldprice;
    double subtotal;

    @Override
    public String toString() {
        return "CartEntity{" +
                "customer_address_id=" + id +
                ", index=" + index +
                ", imageurl='" + imageurl + '\'' +
                ", quentity=" + quentity +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", latestprice=" + latestprice +
                ", oldprice=" + oldprice +
                ", subtotal=" + subtotal +
                '}';
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getLatestprice() {
        return latestprice;
    }

    public void setLatestprice(double latestprice) {
        this.latestprice = latestprice;
    }

    public double getOldprice() {
        return oldprice;
    }

    public void setOldprice(double oldprice) {
        this.oldprice = oldprice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getQuentity() {
        return quentity;
    }

    public void setQuentity(int quentity) {
        this.quentity = quentity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
