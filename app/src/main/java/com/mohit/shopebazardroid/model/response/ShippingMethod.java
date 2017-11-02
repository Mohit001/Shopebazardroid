package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 4/8/16.
 */
public class ShippingMethod {


    private String carrier;
    private String carrier_title;
    private String code;
    private String method;
    private String method_description;
    private String price;
    private String error_message;
    private String method_title;
    private String carrierName;
    private boolean isSelected;

    @Override
    public String toString() {
        return "ShippingMethod{" +
                "carrier='" + carrier + '\'' +
                ", carrier_title='" + carrier_title + '\'' +
                ", code='" + code + '\'' +
                ", method='" + method + '\'' +
                ", method_description='" + method_description + '\'' +
                ", price='" + price + '\'' +
                ", error_message='" + error_message + '\'' +
                ", method_title='" + method_title + '\'' +
                ", carrierName='" + carrierName + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrier_title() {
        return carrier_title;
    }

    public void setCarrier_title(String carrier_title) {
        this.carrier_title = carrier_title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod_description() {
        return method_description;
    }

    public void setMethod_description(String method_description) {
        this.method_description = method_description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getMethod_title() {
        return method_title;
    }

    public void setMethod_title(String method_title) {
        this.method_title = method_title;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
}
