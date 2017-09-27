package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;

/**
 * Created by msp on 1/8/16.
 */
public class ProductBanner implements Serializable {

    private String label;
    private String position;
    private String url;
    private String value_id;
    private String file;
    private String product_id;
    private String disabled;
    private String label_default;
    private String position_default;
    private String disabled_default;

    @Override
    public String toString() {
        return "ProductBanner{" +
                "label='" + label + '\'' +
                ", position='" + position + '\'' +
                ", url='" + url + '\'' +
                ", value_id='" + value_id + '\'' +
                ", file='" + file + '\'' +
                ", product_id='" + product_id + '\'' +
                ", disabled='" + disabled + '\'' +
                ", label_default='" + label_default + '\'' +
                ", position_default='" + position_default + '\'' +
                ", disabled_default='" + disabled_default + '\'' +
                '}';
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getValue_id() {
        return value_id;
    }

    public void setValue_id(String value_id) {
        this.value_id = value_id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getLabel_default() {
        return label_default;
    }

    public void setLabel_default(String label_default) {
        this.label_default = label_default;
    }

    public String getPosition_default() {
        return position_default;
    }

    public void setPosition_default(String position_default) {
        this.position_default = position_default;
    }

    public String getDisabled_default() {
        return disabled_default;
    }

    public void setDisabled_default(String disabled_default) {
        this.disabled_default = disabled_default;
    }
}
