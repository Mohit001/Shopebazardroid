package com.mohit.shopebazardroid.model.response;

/**
 * Created by msp on 6/7/16.
 */
public class Userinfo
{


    private String entity_id;
    private String entity_type_id;
    private String attribute_set_id;
    private String website_id;
    private String email;
    private String group_id;
    private String increment_id;
    private String store_id;
    private String created_at;
    private String updated_at;
    private String is_active;
    private String disable_auto_group_change;
    private String created_in;
    private String firstname;
    private String middlename;
    private String lastname;
    private String password_hash;
    private String rp_token;
    private String device_type;
    private String device_token;
    private String default_billing;
    private String default_shipping;
    private String is_notification;
    private String rp_token_created_at;
    private String SessionId;
    private int shoppingcartid;
    String items_qty;
    String items_count;

    @Override
    public String toString() {
        return "Userinfo{" +
                "entity_id='" + entity_id + '\'' +
                ", entity_type_id='" + entity_type_id + '\'' +
                ", attribute_set_id='" + attribute_set_id + '\'' +
                ", website_id='" + website_id + '\'' +
                ", email='" + email + '\'' +
                ", group_id='" + group_id + '\'' +
                ", increment_id='" + increment_id + '\'' +
                ", store_id='" + store_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", is_active='" + is_active + '\'' +
                ", disable_auto_group_change='" + disable_auto_group_change + '\'' +
                ", created_in='" + created_in + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", rp_token='" + rp_token + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_token='" + device_token + '\'' +
                ", default_billing='" + default_billing + '\'' +
                ", default_shipping='" + default_shipping + '\'' +
                ", is_notification='" + is_notification + '\'' +
                ", rp_token_created_at='" + rp_token_created_at + '\'' +
                ", SessionId='" + SessionId + '\'' +
                ", shoppingcartid=" + shoppingcartid +
                '}';
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getEntity_type_id() {
        return entity_type_id;
    }

    public void setEntity_type_id(String entity_type_id) {
        this.entity_type_id = entity_type_id;
    }

    public String getAttribute_set_id() {
        return attribute_set_id;
    }

    public void setAttribute_set_id(String attribute_set_id) {
        this.attribute_set_id = attribute_set_id;
    }

    public String getWebsite_id() {
        return website_id;
    }

    public void setWebsite_id(String website_id) {
        this.website_id = website_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getIncrement_id() {
        return increment_id;
    }

    public void setIncrement_id(String increment_id) {
        this.increment_id = increment_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getDisable_auto_group_change() {
        return disable_auto_group_change;
    }

    public void setDisable_auto_group_change(String disable_auto_group_change) {
        this.disable_auto_group_change = disable_auto_group_change;
    }

    public String getCreated_in() {
        return created_in;
    }

    public void setCreated_in(String created_in) {
        this.created_in = created_in;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getRp_token() {
        return rp_token;
    }

    public void setRp_token(String rp_token) {
        this.rp_token = rp_token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getDefault_billing() {
        return default_billing;
    }

    public void setDefault_billing(String default_billing) {
        this.default_billing = default_billing;
    }

    public String getDefault_shipping() {
        return default_shipping;
    }

    public void setDefault_shipping(String default_shipping) {
        this.default_shipping = default_shipping;
    }

    public String getIs_notification() {
        return is_notification;
    }

    public void setIs_notification(String is_notification) {
        this.is_notification = is_notification;
    }

    public String getRp_token_created_at() {
        return rp_token_created_at;
    }

    public void setRp_token_created_at(String rp_token_created_at) {
        this.rp_token_created_at = rp_token_created_at;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String SessionId) {
        this.SessionId = SessionId;
    }

    public int getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(int shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public String getItems_qty() {
        return items_qty;
    }

    public void setItems_qty(String items_qty) {
        this.items_qty = items_qty;
    }

    public String getItems_count() {
        return items_count;
    }

    public void setItems_count(String items_count) {
        this.items_count = items_count;
    }
}
