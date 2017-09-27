package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by root on 4/8/16.
 */
public class CartItems
{

    private ArrayList<CartCustomOption> custom_option =new ArrayList<>();
    private ArrayList<CartCustomOptionId> custom_option_id =new ArrayList<>();
    private ArrayList<CartCustomOption> configurable_option =new ArrayList<>();
    private ArrayList<CartCustomOptionId> supper_attribute_id =new ArrayList<>();
    private ArrayList<CartMediaItem> media = new ArrayList<>();
    private String item_id;
    private String created_at;
    private String updated_at;
    private String entity_id;
    private String store_id;
    private String parent_item_id;
    private int is_virtual;
    private String sku;
    private String name;
    private String free_shipping;
    private String is_qty_decimal;
    private String no_discount;
    private double weight;
    private int qty;
    private double price;
    private double base_price;
    private double discount_percent;
    private double discount_amount;
    private double base_discount_amount;
    private double tax_percent;
    private double tax_amount;
    private double base_tax_amount;
    private double row_total;
    private double base_row_total;
    private double row_total_with_discount;
    private double row_weight;
    private String product_type;
    private double price_incl_tax;
    private double base_price_incl_tax;
    private double row_total_incl_tax;
    private double base_row_total_incl_tax;
    private double weee_tax_applied_amount;
    private double weee_tax_applied_row_amount;
    private double base_weee_tax_applied_amount;
    private double weee_tax_disposition;
    private double weee_tax_row_disposition;
    private double base_weee_tax_disposition;
    private double base_weee_tax_row_disposition;


    @Override
    public String toString() {
        return "CartItems{" +
                "custom_option=" + custom_option +
                ", custom_option_id=" + custom_option_id +
                ", media=" + media +
                ", item_id='" + item_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", entity_id='" + entity_id + '\'' +
                ", store_id='" + store_id + '\'' +
                ", parent_item_id='" + parent_item_id + '\'' +
                ", is_virtual=" + is_virtual +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", free_shipping='" + free_shipping + '\'' +
                ", is_qty_decimal='" + is_qty_decimal + '\'' +
                ", no_discount='" + no_discount + '\'' +
                ", weight=" + weight +
                ", qty=" + qty +
                ", price=" + price +
                ", base_price=" + base_price +
                ", discount_percent=" + discount_percent +
                ", discount_amount=" + discount_amount +
                ", base_discount_amount=" + base_discount_amount +
                ", tax_percent=" + tax_percent +
                ", tax_amount=" + tax_amount +
                ", base_tax_amount=" + base_tax_amount +
                ", row_total=" + row_total +
                ", base_row_total=" + base_row_total +
                ", row_total_with_discount=" + row_total_with_discount +
                ", row_weight=" + row_weight +
                ", product_type='" + product_type + '\'' +
                ", price_incl_tax=" + price_incl_tax +
                ", base_price_incl_tax=" + base_price_incl_tax +
                ", row_total_incl_tax=" + row_total_incl_tax +
                ", base_row_total_incl_tax=" + base_row_total_incl_tax +
                ", weee_tax_applied_amount=" + weee_tax_applied_amount +
                ", weee_tax_applied_row_amount=" + weee_tax_applied_row_amount +
                ", base_weee_tax_applied_amount=" + base_weee_tax_applied_amount +
                ", weee_tax_disposition=" + weee_tax_disposition +
                ", weee_tax_row_disposition=" + weee_tax_row_disposition +
                ", base_weee_tax_disposition=" + base_weee_tax_disposition +
                ", base_weee_tax_row_disposition=" + base_weee_tax_row_disposition +
                '}';
    }

    public String getParent_item_id() {
        return parent_item_id;
    }

    public void setParent_item_id(String parent_item_id) {
        this.parent_item_id = parent_item_id;
    }

    public ArrayList<CartCustomOption> getCustom_option() {
        return custom_option;
    }

    public void setCustom_option(ArrayList<CartCustomOption> custom_option) {
        this.custom_option = custom_option;
    }

    public ArrayList<CartCustomOptionId> getCustom_option_id() {
        return custom_option_id;
    }

    public void setCustom_option_id(ArrayList<CartCustomOptionId> custom_option_id) {
        this.custom_option_id = custom_option_id;
    }

    public ArrayList<CartCustomOption> getConfigurable_option() {
        return configurable_option;
    }

    public void setConfigurable_option(ArrayList<CartCustomOption> configurable_option) {
        this.configurable_option = configurable_option;
    }

    public ArrayList<CartCustomOptionId> getSupper_attribute_id() {
        return supper_attribute_id;
    }

    public void setSupper_attribute_id(ArrayList<CartCustomOptionId> supper_attribute_id) {
        this.supper_attribute_id = supper_attribute_id;
    }

    public ArrayList<CartMediaItem> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<CartMediaItem> media) {
        this.media = media;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
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

    public String getProduct_id() {
        return entity_id;
    }

    public void setProduct_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public int getIs_virtual() {
        return is_virtual;
    }

    public void setIs_virtual(int is_virtual) {
        this.is_virtual = is_virtual;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFree_shipping() {
        return free_shipping;
    }

    public void setFree_shipping(String free_shipping) {
        this.free_shipping = free_shipping;
    }

    public String getIs_qty_decimal() {
        return is_qty_decimal;
    }

    public void setIs_qty_decimal(String is_qty_decimal) {
        this.is_qty_decimal = is_qty_decimal;
    }

    public String getNo_discount() {
        return no_discount;
    }

    public void setNo_discount(String no_discount) {
        this.no_discount = no_discount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public double getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(double discount_amount) {
        this.discount_amount = discount_amount;
    }

    public double getBase_discount_amount() {
        return base_discount_amount;
    }

    public void setBase_discount_amount(double base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }

    public double getTax_percent() {
        return tax_percent;
    }

    public void setTax_percent(double tax_percent) {
        this.tax_percent = tax_percent;
    }

    public double getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(double tax_amount) {
        this.tax_amount = tax_amount;
    }

    public double getBase_tax_amount() {
        return base_tax_amount;
    }

    public void setBase_tax_amount(double base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }

    public double getRow_total() {
        return row_total;
    }

    public void setRow_total(double row_total) {
        this.row_total = row_total;
    }

    public double getBase_row_total() {
        return base_row_total;
    }

    public void setBase_row_total(double base_row_total) {
        this.base_row_total = base_row_total;
    }

    public double getRow_total_with_discount() {
        return row_total_with_discount;
    }

    public void setRow_total_with_discount(double row_total_with_discount) {
        this.row_total_with_discount = row_total_with_discount;
    }

    public double getRow_weight() {
        return row_weight;
    }

    public void setRow_weight(double row_weight) {
        this.row_weight = row_weight;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public double getPrice_incl_tax() {
        return price_incl_tax;
    }

    public void setPrice_incl_tax(double price_incl_tax) {
        this.price_incl_tax = price_incl_tax;
    }

    public double getBase_price_incl_tax() {
        return base_price_incl_tax;
    }

    public void setBase_price_incl_tax(double base_price_incl_tax) {
        this.base_price_incl_tax = base_price_incl_tax;
    }

    public double getRow_total_incl_tax() {
        return row_total_incl_tax;
    }

    public void setRow_total_incl_tax(double row_total_incl_tax) {
        this.row_total_incl_tax = row_total_incl_tax;
    }

    public double getBase_row_total_incl_tax() {
        return base_row_total_incl_tax;
    }

    public void setBase_row_total_incl_tax(double base_row_total_incl_tax) {
        this.base_row_total_incl_tax = base_row_total_incl_tax;
    }



    public double getWeee_tax_applied_amount() {
        return weee_tax_applied_amount;
    }

    public void setWeee_tax_applied_amount(double weee_tax_applied_amount) {
        this.weee_tax_applied_amount = weee_tax_applied_amount;
    }

    public double getWeee_tax_applied_row_amount() {
        return weee_tax_applied_row_amount;
    }

    public void setWeee_tax_applied_row_amount(double weee_tax_applied_row_amount) {
        this.weee_tax_applied_row_amount = weee_tax_applied_row_amount;
    }

    public double getBase_weee_tax_applied_amount() {
        return base_weee_tax_applied_amount;
    }

    public void setBase_weee_tax_applied_amount(double base_weee_tax_applied_amount) {
        this.base_weee_tax_applied_amount = base_weee_tax_applied_amount;
    }

    public double getWeee_tax_disposition() {
        return weee_tax_disposition;
    }

    public void setWeee_tax_disposition(double weee_tax_disposition) {
        this.weee_tax_disposition = weee_tax_disposition;
    }

    public double getWeee_tax_row_disposition() {
        return weee_tax_row_disposition;
    }

    public void setWeee_tax_row_disposition(double weee_tax_row_disposition) {
        this.weee_tax_row_disposition = weee_tax_row_disposition;
    }

    public double getBase_weee_tax_disposition() {
        return base_weee_tax_disposition;
    }

    public void setBase_weee_tax_disposition(double base_weee_tax_disposition) {
        this.base_weee_tax_disposition = base_weee_tax_disposition;
    }

    public double getBase_weee_tax_row_disposition() {
        return base_weee_tax_row_disposition;
    }

    public void setBase_weee_tax_row_disposition(double base_weee_tax_row_disposition) {
        this.base_weee_tax_row_disposition = base_weee_tax_row_disposition;
    }
}
