package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 17/10/16.
 */

public class Mobile_return {

    String custom_shipment_id;
    String shipment_status;
    String order_id;

    @Override
    public String toString() {
        return "Mobile_return{" +
                "custom_shipment_id='" + custom_shipment_id + '\'' +
                ", shipment_status='" + shipment_status + '\'' +
                ", order_id='" + order_id + '\'' +
                '}';
    }

    public String getCustom_shipment_id() {
        return custom_shipment_id;
    }

    public void setCustom_shipment_id(String custom_shipment_id) {
        this.custom_shipment_id = custom_shipment_id;
    }

    public String getShipment_status() {
        return shipment_status;
    }

    public void setShipment_status(String shipment_status) {
        this.shipment_status = shipment_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
