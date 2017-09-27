package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 17/8/16.
 */
public class BasicSettings {


    private String themename;
    private String basecurrency;
    private String basecurrencycode;
    private String logo_src;
    private ArrayList<CurrencyEntity> currencies;
    private String add_to_cart;
    private String CheckCustomerSubscriptionStatusResult;
    private String Product_media_URL;
    private ArrayList<StoreList> storelist;
    private PaymentInfo payment;


    @Override
    public String toString() {
        return "BasicSettings{" +
                "themename='" + themename + '\'' +
                ", basecurrency='" + basecurrency + '\'' +
                ", basecurrencycode='" + basecurrencycode + '\'' +
                ", logo_src='" + logo_src + '\'' +
                ", currencies=" + currencies +
                ", add_to_cart='" + add_to_cart + '\'' +
                ", CheckCustomerSubscriptionStatusResult='" + CheckCustomerSubscriptionStatusResult + '\'' +
                ", Product_media_URL='" + Product_media_URL + '\'' +
                ", storelist=" + storelist +
                '}';
    }

    public ArrayList<StoreList> getStorelist() {
        return storelist;
    }

    public void setStorelist(ArrayList<StoreList> storelist) {
        this.storelist = storelist;
    }

    public String getAdd_to_cart() {
        return add_to_cart;
    }

    public void setAdd_to_cart(String add_to_cart) {
        this.add_to_cart = add_to_cart;
    }

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String checkCustomerSubscriptionStatusResult) {
        CheckCustomerSubscriptionStatusResult = checkCustomerSubscriptionStatusResult;
    }

    public ArrayList<CurrencyEntity> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<CurrencyEntity> currencies) {
        this.currencies = currencies;
    }

    public String getThemename() {
        return themename;
    }

    public void setThemename(String themename) {
        this.themename = themename;
    }

    public String getBasecurrency() {
        return basecurrency;
    }

    public void setBasecurrency(String basecurrency) {
        this.basecurrency = basecurrency;
    }

    public String getBasecurrencycode() {
        return basecurrencycode;
    }

    public void setBasecurrencycode(String basecurrencycode) {
        this.basecurrencycode = basecurrencycode;
    }

    public String getLogo_src() {
        return logo_src;
    }

    public void setLogo_src(String logo_src) {
        this.logo_src = logo_src;
    }

    public String getProduct_media_URL() {
        return Product_media_URL;
    }

    public void setProduct_media_URL(String product_media_URL) {
        Product_media_URL = product_media_URL;
    }

    public PaymentInfo getPayment() {
        return payment;
    }

    public void setPayment(PaymentInfo payment) {
        this.payment = payment;
    }
}
