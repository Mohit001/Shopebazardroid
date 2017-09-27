package com.mohit.shopebazardroid.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by msp on 5/8/16.
 */
public class OrderReviewEntity {


    @SerializedName("store_id")
    private String store_id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("quote_id")
    private int quote_id;
    @SerializedName("is_active")
    private int is_active;
    @SerializedName("is_virtual")
    private int is_virtual;
    @SerializedName("is_multi_shipping")
    private int is_multi_shipping;
    @SerializedName("items_count")
    private int items_count;
    @SerializedName("items_qty")
    private int items_qty;
    @SerializedName("orig_order_id")
    private String orig_order_id;
    @SerializedName("store_to_base_rate")
    private String store_to_base_rate;
    @SerializedName("store_to_quote_rate")
    private String store_to_quote_rate;
    @SerializedName("base_currency_code")
    private String base_currency_code;
    @SerializedName("store_currency_code")
    private String store_currency_code;
    @SerializedName("quote_currency_code")
    private String quote_currency_code;
    @SerializedName("grand_total")
    private String grand_total;
    @SerializedName("base_grand_total")
    private String base_grand_total;
    @SerializedName("customer_id")
    private String customer_id;
    @SerializedName("customer_tax_class_id")
    private String customer_tax_class_id;
    @SerializedName("customer_group_id")
    private int customer_group_id;
    @SerializedName("customer_email")
    private String customer_email;
    @SerializedName("customer_firstname")
    private String customer_firstname;
    @SerializedName("customer_middlename")
    private String customer_middlename;
    @SerializedName("customer_lastname")
    private String customer_lastname;
    @SerializedName("customer_note_notify")
    private String customer_note_notify;
    @SerializedName("customer_is_guest")
    private String customer_is_guest;
    @SerializedName("global_currency_code")
    private String global_currency_code;
    @SerializedName("base_to_global_rate")
    private int base_to_global_rate;
    @SerializedName("base_to_quote_rate")
    private float base_to_quote_rate;
    @SerializedName("subtotal")
    private double subtotal;
    @SerializedName("base_subtotal")
    private double base_subtotal;
    @SerializedName("subtotal_with_discount")
    private double subtotal_with_discount;
    @SerializedName("base_subtotal_with_discount")
    private double base_subtotal_with_discount;
    @SerializedName("shipping_address")
    private Address shipping_address;
    @SerializedName("billing_address")
    private Address billing_address;
    @SerializedName("items")
    private ArrayList<OrderReviewCartItem> items;
    @SerializedName("payment")
    private OrderReviewPayment payment;

    private String coupon_code;

    private double base_discount_amount_total;
    private double base_tax_amount_total;

    @Override
    public String toString() {
        return "OrderReviewEntity{" +
                "store_id='" + store_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", quote_id=" + quote_id +
                ", is_active=" + is_active +
                ", is_virtual=" + is_virtual +
                ", is_multi_shipping=" + is_multi_shipping +
                ", items_count=" + items_count +
                ", items_qty=" + items_qty +
                ", orig_order_id='" + orig_order_id + '\'' +
                ", store_to_base_rate='" + store_to_base_rate + '\'' +
                ", store_to_quote_rate='" + store_to_quote_rate + '\'' +
                ", base_currency_code='" + base_currency_code + '\'' +
                ", store_currency_code='" + store_currency_code + '\'' +
                ", quote_currency_code='" + quote_currency_code + '\'' +
                ", grand_total='" + grand_total + '\'' +
                ", base_grand_total='" + base_grand_total + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", customer_tax_class_id='" + customer_tax_class_id + '\'' +
                ", customer_group_id=" + customer_group_id +
                ", customer_email='" + customer_email + '\'' +
                ", customer_firstname='" + customer_firstname + '\'' +
                ", customer_middlename='" + customer_middlename + '\'' +
                ", customer_lastname='" + customer_lastname + '\'' +
                ", customer_note_notify='" + customer_note_notify + '\'' +
                ", customer_is_guest='" + customer_is_guest + '\'' +
                ", global_currency_code='" + global_currency_code + '\'' +
                ", base_to_global_rate=" + base_to_global_rate +
                ", base_to_quote_rate=" + base_to_quote_rate +
                ", subtotal=" + subtotal +
                ", base_subtotal=" + base_subtotal +
                ", subtotal_with_discount=" + subtotal_with_discount +
                ", base_subtotal_with_discount=" + base_subtotal_with_discount +
                ", shipping_address=" + shipping_address +
                ", billing_address=" + billing_address +
                ", items=" + items +
                ", payment=" + payment +
                ", coupon_code='" + coupon_code + '\'' +
                ", base_discount_amount_total=" + base_discount_amount_total +
                ", base_tax_amount_total=" + base_tax_amount_total +
                '}';
    }

    public double getBase_discount_amount_total() {
        return base_discount_amount_total;
    }

    public void setBase_discount_amount_total(double base_discount_amount_total) {
        this.base_discount_amount_total = base_discount_amount_total;
    }

    public double getBase_tax_amount_total() {
        return base_tax_amount_total;
    }

    public void setBase_tax_amount_total(double base_tax_amount_total) {
        this.base_tax_amount_total = base_tax_amount_total;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public Address getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(Address shipping_address) {
        this.shipping_address = shipping_address;
    }

    public Address getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(Address billing_address) {
        this.billing_address = billing_address;
    }

    public ArrayList<OrderReviewCartItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderReviewCartItem> items) {
        this.items = items;
    }

    public OrderReviewPayment getPayment() {
        return payment;
    }

    public void setPayment(OrderReviewPayment payment) {
        this.payment = payment;
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

    public int getQuote_id() {
        return quote_id;
    }

    public void setQuote_id(int quote_id) {
        this.quote_id = quote_id;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public int getIs_virtual() {
        return is_virtual;
    }

    public void setIs_virtual(int is_virtual) {
        this.is_virtual = is_virtual;
    }

    public int getIs_multi_shipping() {
        return is_multi_shipping;
    }

    public void setIs_multi_shipping(int is_multi_shipping) {
        this.is_multi_shipping = is_multi_shipping;
    }

    public int getItems_count() {
        return items_count;
    }

    public void setItems_count(int items_count) {
        this.items_count = items_count;
    }

    public int getItems_qty() {
        return items_qty;
    }

    public void setItems_qty(int items_qty) {
        this.items_qty = items_qty;
    }

    public String getOrig_order_id() {
        return orig_order_id;
    }

    public void setOrig_order_id(String orig_order_id) {
        this.orig_order_id = orig_order_id;
    }

    public String getStore_to_base_rate() {
        return store_to_base_rate;
    }

    public void setStore_to_base_rate(String store_to_base_rate) {
        this.store_to_base_rate = store_to_base_rate;
    }

    public String getStore_to_quote_rate() {
        return store_to_quote_rate;
    }

    public void setStore_to_quote_rate(String store_to_quote_rate) {
        this.store_to_quote_rate = store_to_quote_rate;
    }

    public String getBase_currency_code() {
        return base_currency_code;
    }

    public void setBase_currency_code(String base_currency_code) {
        this.base_currency_code = base_currency_code;
    }

    public String getStore_currency_code() {
        return store_currency_code;
    }

    public void setStore_currency_code(String store_currency_code) {
        this.store_currency_code = store_currency_code;
    }

    public String getQuote_currency_code() {
        return quote_currency_code;
    }

    public void setQuote_currency_code(String quote_currency_code) {
        this.quote_currency_code = quote_currency_code;
    }

    public String getGrand_total() {
        return grand_total;
    }

    public void setGrand_total(String grand_total) {
        this.grand_total = grand_total;
    }

    public String getBase_grand_total() {
        return base_grand_total;
    }

    public void setBase_grand_total(String base_grand_total) {
        this.base_grand_total = base_grand_total;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_tax_class_id() {
        return customer_tax_class_id;
    }

    public void setCustomer_tax_class_id(String customer_tax_class_id) {
        this.customer_tax_class_id = customer_tax_class_id;
    }

    public int getCustomer_group_id() {
        return customer_group_id;
    }

    public void setCustomer_group_id(int customer_group_id) {
        this.customer_group_id = customer_group_id;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_firstname() {
        return customer_firstname;
    }

    public void setCustomer_firstname(String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }

    public String getCustomer_middlename() {
        return customer_middlename;
    }

    public void setCustomer_middlename(String customer_middlename) {
        this.customer_middlename = customer_middlename;
    }

    public String getCustomer_lastname() {
        return customer_lastname;
    }

    public void setCustomer_lastname(String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }

    public String getCustomer_note_notify() {
        return customer_note_notify;
    }

    public void setCustomer_note_notify(String customer_note_notify) {
        this.customer_note_notify = customer_note_notify;
    }

    public String getCustomer_is_guest() {
        return customer_is_guest;
    }

    public void setCustomer_is_guest(String customer_is_guest) {
        this.customer_is_guest = customer_is_guest;
    }

    public String getGlobal_currency_code() {
        return global_currency_code;
    }

    public void setGlobal_currency_code(String global_currency_code) {
        this.global_currency_code = global_currency_code;
    }

    public int getBase_to_global_rate() {
        return base_to_global_rate;
    }

    public void setBase_to_global_rate(int base_to_global_rate) {
        this.base_to_global_rate = base_to_global_rate;
    }

    public float getBase_to_quote_rate() {
        return base_to_quote_rate;
    }

    public void setBase_to_quote_rate(float base_to_quote_rate) {
        this.base_to_quote_rate = base_to_quote_rate;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getBase_subtotal() {
        return base_subtotal;
    }

    public void setBase_subtotal(double base_subtotal) {
        this.base_subtotal = base_subtotal;
    }

    public double getSubtotal_with_discount() {
        return subtotal_with_discount;
    }

    public void setSubtotal_with_discount(double subtotal_with_discount) {
        this.subtotal_with_discount = subtotal_with_discount;
    }

    public double getBase_subtotal_with_discount() {
        return base_subtotal_with_discount;
    }

    public void setBase_subtotal_with_discount(double base_subtotal_with_discount) {
        this.base_subtotal_with_discount = base_subtotal_with_discount;
    }
}
