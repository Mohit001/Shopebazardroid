package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 25/5/17.
 */

public class StoreListResponse {


    private Result result;
    private String status;
    private String CheckCustomerSubscriptionStatusResult;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckCustomerSubscriptionStatusResult() {
        return CheckCustomerSubscriptionStatusResult;
    }

    public void setCheckCustomerSubscriptionStatusResult(String CheckCustomerSubscriptionStatusResult) {
        this.CheckCustomerSubscriptionStatusResult = CheckCustomerSubscriptionStatusResult;
    }

    public static class Setting {

        private String displaycurrency;
        private String displaycurrencycode;
        private String shoppingcartid;
        private String rate;
        private String ishideprice;
        private String configurable_swatches;
        private String product_list_attribute;
        private ArrayList<String> attributes;

        public ArrayList<String> getAttributes() {
            return attributes;
        }

        public void setAttributes(ArrayList<String> attributes) {
            this.attributes = attributes;
        }

        public String getConfigurable_swatches() {
            return configurable_swatches;
        }

        public void setConfigurable_swatches(String configurable_swatches) {
            this.configurable_swatches = configurable_swatches;
        }

        public String getProduct_list_attribute() {
            return product_list_attribute;
        }

        public void setProduct_list_attribute(String product_list_attribute) {
            this.product_list_attribute = product_list_attribute;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getIshideprice() {
            return ishideprice;
        }

        public void setIshideprice(String ishideprice) {
            this.ishideprice = ishideprice;
        }

        public String getDisplaycurrency() {
            return displaycurrency;
        }

        public void setDisplaycurrency(String displaycurrency) {
            this.displaycurrency = displaycurrency;
        }

        public String getDisplaycurrencycode() {
            return displaycurrencycode;
        }

        public void setDisplaycurrencycode(String displaycurrencycode) {
            this.displaycurrencycode = displaycurrencycode;
        }

        public String getShoppingcartid() {
            return shoppingcartid;
        }

        public void setShoppingcartid(String shoppingcartid) {
            this.shoppingcartid = shoppingcartid;
        }
    }

    public static class Result {
        private String message;
        private Setting setting;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Setting getSetting() {
            return setting;
        }

        public void setSetting(Setting setting) {
            this.setting = setting;
        }
    }
}
