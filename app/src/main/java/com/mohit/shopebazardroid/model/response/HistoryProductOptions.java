package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msp on 8/8/16.
 */
public class HistoryProductOptions {

    public class InfoBuyRequest
    {

        private String uenc;
        private String product;
        private String form_key;
        private String related_product;
        private String qty;
        private String isAjax;

        @Override
        public String toString() {
            return "InfoBuyRequest{" +
                    "uenc='" + uenc + '\'' +
                    ", product='" + product + '\'' +
                    ", form_key='" + form_key + '\'' +
                    ", related_product='" + related_product + '\'' +
                    ", qty='" + qty + '\'' +
                    ", isAjax='" + isAjax + '\'' +
                    '}';
        }

        public String getUenc() {
            return uenc;
        }

        public void setUenc(String uenc) {
            this.uenc = uenc;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getForm_key() {
            return form_key;
        }

        public void setForm_key(String form_key) {
            this.form_key = form_key;
        }

        public String getRelated_product() {
            return related_product;
        }

        public void setRelated_product(String related_product) {
            this.related_product = related_product;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getIsAjax() {
            return isAjax;
        }

        public void setIsAjax(String isAjax) {
            this.isAjax = isAjax;
        }
    }

    public class Options
    {

        private String label;
        private String value;
        private String print_value;
        private String option_id;
        private String option_type;
        private String option_value;
        private boolean custom_view;

        @Override
        public String toString() {
            return "Options{" +
                    "label='" + label + '\'' +
                    ", value='" + value + '\'' +
                    ", print_value='" + print_value + '\'' +
                    ", option_id='" + option_id + '\'' +
                    ", option_type='" + option_type + '\'' +
                    ", option_value='" + option_value + '\'' +
                    ", custom_view=" + custom_view +
                    '}';
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPrint_value() {
            return print_value;
        }

        public void setPrint_value(String print_value) {
            this.print_value = print_value;
        }

        public String getOption_id() {
            return option_id;
        }

        public void setOption_id(String option_id) {
            this.option_id = option_id;
        }

        public String getOption_type() {
            return option_type;
        }

        public void setOption_type(String option_type) {
            this.option_type = option_type;
        }

        public String getOption_value() {
            return option_value;
        }

        public void setOption_value(String option_value) {
            this.option_value = option_value;
        }

        public boolean getCustom_view() {
            return custom_view;
        }

        public void setCustom_view(boolean custom_view) {
            this.custom_view = custom_view;
        }
    }



    private  InfoBuyRequest info_buyRequest;
    private List<AttributesInfo> attributes_info;
    private ArrayList<Options> options;
    private String simple_name;
    private String simple_sku;
    private int product_calculations;
    private String shipment_type;

    @Override
    public String toString() {
        return "HistoryProductOptions{" +
                "info_buyRequest=" + info_buyRequest +
                ", attributes_info=" + attributes_info +
                ", options=" + options +
                ", simple_name='" + simple_name + '\'' +
                ", simple_sku='" + simple_sku + '\'' +
                ", product_calculations=" + product_calculations +
                ", shipment_type='" + shipment_type + '\'' +
                '}';
    }

    public InfoBuyRequest getInfo_buyRequest() {
        return info_buyRequest;
    }

    public void setInfo_buyRequest(InfoBuyRequest info_buyRequest) {
        this.info_buyRequest = info_buyRequest;
    }

    public List<AttributesInfo> getAttributes_info() {
        return attributes_info;
    }

    public void setAttributes_info(List<AttributesInfo> attributes_info) {
        this.attributes_info = attributes_info;
    }

    public ArrayList<Options> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Options> options) {
        this.options = options;
    }

    public String getSimple_name() {
        return simple_name;
    }

    public void setSimple_name(String simple_name) {
        this.simple_name = simple_name;
    }

    public String getSimple_sku() {
        return simple_sku;
    }

    public void setSimple_sku(String simple_sku) {
        this.simple_sku = simple_sku;
    }

    public int getProduct_calculations() {
        return product_calculations;
    }

    public void setProduct_calculations(int product_calculations) {
        this.product_calculations = product_calculations;
    }

    public String getShipment_type() {
        return shipment_type;
    }

    public void setShipment_type(String shipment_type) {
        this.shipment_type = shipment_type;
    }
}
