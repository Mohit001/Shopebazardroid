package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by msp on 30/5/17.
 */

public class RelatedProductResponse implements Serializable {


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

    public static class Value_option {
        private String value_id;
        private String title;
        private String price;
        private String price_type;
        private String sku;
        private String sort_order;

        public String getValue_id() {
            return value_id;
        }

        public void setValue_id(String value_id) {
            this.value_id = value_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_type() {
            return price_type;
        }

        public void setPrice_type(String price_type) {
            this.price_type = price_type;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }
    }

    public static class Media {
        private String file;
        private String label;
        private String position;
        private String exclude;
        private String url;

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getExclude() {
            return exclude;
        }

        public void setExclude(String exclude) {
            this.exclude = exclude;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Result {
        private String message;
        private ArrayList<RelatedProductList> productlist;
        private String filter_attributes;
        private String json_filter;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ArrayList<RelatedProductList> getProductlist() {
            return productlist;
        }

        public void setProductlist(ArrayList<RelatedProductList> productlist) {
            this.productlist = productlist;
        }

        public String getFilter_attributes() {
            return filter_attributes;
        }

        public void setFilter_attributes(String filter_attributes) {
            this.filter_attributes = filter_attributes;
        }

        public String getJson_filter() {
            return json_filter;
        }

        public void setJson_filter(String json_filter) {
            this.json_filter = json_filter;
        }
    }
}
