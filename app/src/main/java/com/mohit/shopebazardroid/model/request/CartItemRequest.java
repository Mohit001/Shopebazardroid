package com.mohit.shopebazardroid.model.request;

import java.util.*;

/**
 * Created by root on 8/7/16.
 */
public class CartItemRequest
{
    int cartId;
    String productId;
    String productName;
    String productSku;
    int productQty;
    int has_custom_option;
    boolean isConfigurable;
    String code;
    ArrayList<CustomOptionRequest> customOptions=new ArrayList<>();

    private String customer_id;
    private String store_id;

    String final_price;

    @Override
    public String toString() {
        return "CartItemRequest{" +
                "cartId=" + cartId +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productSku='" + productSku + '\'' +
                ", productQty=" + productQty +
                ", has_custom_option=" + has_custom_option +
                ", isConfigurable=" + isConfigurable +
                ", code='" + code + '\'' +
                ", customOptions=" + customOptions +
                ", customer_id='" + customer_id + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }
    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public int getHas_custom_option() {
        return has_custom_option;
    }

    public void setHas_custom_option(int has_custom_option) {
        this.has_custom_option = has_custom_option;
    }

    public ArrayList<CustomOptionRequest> getCustomOptions() {
        return customOptions;
    }

    public void setCustomOptions(ArrayList<CustomOptionRequest> customOptions) {
        this.customOptions = customOptions;
    }

    public boolean isConfigurable() {
        return isConfigurable;
    }

    public void setConfigurable(boolean configurable) {
        isConfigurable = configurable;
    }
}
