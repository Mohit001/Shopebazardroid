package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 8/8/16.
 */
public class HistoryStatus {

    private String parent_id;
    private String is_customer_notified;
    private String is_visible_on_front;
    private String comment="";
    private String status;
    private String created_at;
    private String entity_name;
    private String store_id;

    @Override
    public String toString() {
        return "HistoryStatus{" +
                "parent_id='" + parent_id + '\'' +
                ", is_customer_notified='" + is_customer_notified + '\'' +
                ", is_visible_on_front='" + is_visible_on_front + '\'' +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", entity_name='" + entity_name + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getIs_customer_notified() {
        return is_customer_notified;
    }

    public void setIs_customer_notified(String is_customer_notified) {
        this.is_customer_notified = is_customer_notified;
    }

    public String getIs_visible_on_front() {
        return is_visible_on_front;
    }

    public void setIs_visible_on_front(String is_visible_on_front) {
        this.is_visible_on_front = is_visible_on_front;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEntity_name() {
        return entity_name;
    }

    public void setEntity_name(String entity_name) {
        this.entity_name = entity_name;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
