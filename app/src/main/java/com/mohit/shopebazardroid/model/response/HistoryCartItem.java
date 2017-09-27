package com.mohit.shopebazardroid.model.response;

import java.util.ArrayList;

/**
 * Created by msp on 5/8/16.
 */
public class HistoryCartItem {

    private String item_id;
    private String order_id;
    private String parent_item_id;
    private String quote_item_id;
    private String store_id;
    private String created_at;
    private String updated_at;
    private String entity_id;
    private String product_type;
    private String weight;
    private String is_virtual;
    private String sku;
    private String name;
    private String description;
    private String applied_rule_ids;
    private String additional_data;
    private String free_shipping;
    private String is_qty_decimal;
    private String no_discount;
    private String qty_backordered;
    private String qty_canceled;
    private String qty_invoiced;
    private String qty_ordered;
    private String qty_refunded;
    private String qty_shipped;
    private String base_cost;
    private String price;
    private String base_price;
    private String original_price;
    private String base_original_price;
    private String tax_percent;
    private String tax_amount;
    private String base_tax_amount;
    private String tax_invoiced;
    private String base_tax_invoiced;
    private String discount_percent;
    private String discount_amount;
    private String base_discount_amount;
    private String discount_invoiced;
    private String base_discount_invoiced;
    private String amount_refunded;
    private String base_amount_refunded;
    private String row_total;
    private String base_row_total;
    private String row_invoiced;
    private String base_row_invoiced;
    private String row_weight;
    private String base_tax_before_discount;
    private String tax_before_discount;
    private String ext_order_item_id;
    private String locked_do_invoice;
    private String locked_do_ship;
    private String price_incl_tax;
    private String base_price_incl_tax;
    private String row_total_incl_tax;
    private String base_row_total_incl_tax;
    private String hidden_tax_amount;
    private String base_hidden_tax_amount;
    private String hidden_tax_invoiced;
    private String base_hidden_tax_invoiced;
    private String hidden_tax_refunded;
    private String base_hidden_tax_refunded;
    private String is_nominal;
    private String tax_canceled;
    private String hidden_tax_canceled;
    private String tax_refunded;
    private String base_tax_refunded;
    private String discount_refunded;
    private String base_discount_refunded;
    private String gift_message_id;
    private String gift_message_available;
    private String base_weee_tax_applied_amount;
    private String base_weee_tax_applied_row_amnt;
    private String base_weee_tax_applied_row_amount;
    private String weee_tax_applied_amount;
    private String weee_tax_applied_row_amount;
    private String weee_tax_disposition;
    private String weee_tax_row_disposition;
    private String base_weee_tax_disposition;
    private String base_weee_tax_row_disposition;
    private ArrayList<CartMediaItem> media;
    private HistoryProductOptions product_options;

    @Override
    public String toString() {
        return "HistoryCartItem{" +
                "item_id='" + item_id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", parent_item_id='" + parent_item_id + '\'' +
                ", quote_item_id='" + quote_item_id + '\'' +
                ", store_id='" + store_id + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", entity_id='" + entity_id + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_options='" + product_options + '\'' +
                ", weight='" + weight + '\'' +
                ", is_virtual='" + is_virtual + '\'' +
                ", sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", applied_rule_ids='" + applied_rule_ids + '\'' +
                ", additional_data='" + additional_data + '\'' +
                ", free_shipping='" + free_shipping + '\'' +
                ", is_qty_decimal='" + is_qty_decimal + '\'' +
                ", no_discount='" + no_discount + '\'' +
                ", qty_backordered='" + qty_backordered + '\'' +
                ", qty_canceled='" + qty_canceled + '\'' +
                ", qty_invoiced='" + qty_invoiced + '\'' +
                ", qty_ordered='" + qty_ordered + '\'' +
                ", qty_refunded='" + qty_refunded + '\'' +
                ", qty_shipped='" + qty_shipped + '\'' +
                ", base_cost='" + base_cost + '\'' +
                ", price='" + price + '\'' +
                ", base_price='" + base_price + '\'' +
                ", original_price='" + original_price + '\'' +
                ", base_original_price='" + base_original_price + '\'' +
                ", tax_percent='" + tax_percent + '\'' +
                ", tax_amount='" + tax_amount + '\'' +
                ", base_tax_amount='" + base_tax_amount + '\'' +
                ", tax_invoiced='" + tax_invoiced + '\'' +
                ", base_tax_invoiced='" + base_tax_invoiced + '\'' +
                ", discount_percent='" + discount_percent + '\'' +
                ", discount_amount='" + discount_amount + '\'' +
                ", base_discount_amount='" + base_discount_amount + '\'' +
                ", discount_invoiced='" + discount_invoiced + '\'' +
                ", base_discount_invoiced='" + base_discount_invoiced + '\'' +
                ", amount_refunded='" + amount_refunded + '\'' +
                ", base_amount_refunded='" + base_amount_refunded + '\'' +
                ", row_total='" + row_total + '\'' +
                ", base_row_total='" + base_row_total + '\'' +
                ", row_invoiced='" + row_invoiced + '\'' +
                ", base_row_invoiced='" + base_row_invoiced + '\'' +
                ", row_weight='" + row_weight + '\'' +
                ", base_tax_before_discount='" + base_tax_before_discount + '\'' +
                ", tax_before_discount='" + tax_before_discount + '\'' +
                ", ext_order_item_id='" + ext_order_item_id + '\'' +
                ", locked_do_invoice='" + locked_do_invoice + '\'' +
                ", locked_do_ship='" + locked_do_ship + '\'' +
                ", price_incl_tax='" + price_incl_tax + '\'' +
                ", base_price_incl_tax='" + base_price_incl_tax + '\'' +
                ", row_total_incl_tax='" + row_total_incl_tax + '\'' +
                ", base_row_total_incl_tax='" + base_row_total_incl_tax + '\'' +
                ", hidden_tax_amount='" + hidden_tax_amount + '\'' +
                ", base_hidden_tax_amount='" + base_hidden_tax_amount + '\'' +
                ", hidden_tax_invoiced='" + hidden_tax_invoiced + '\'' +
                ", base_hidden_tax_invoiced='" + base_hidden_tax_invoiced + '\'' +
                ", hidden_tax_refunded='" + hidden_tax_refunded + '\'' +
                ", base_hidden_tax_refunded='" + base_hidden_tax_refunded + '\'' +
                ", is_nominal='" + is_nominal + '\'' +
                ", tax_canceled='" + tax_canceled + '\'' +
                ", hidden_tax_canceled='" + hidden_tax_canceled + '\'' +
                ", tax_refunded='" + tax_refunded + '\'' +
                ", base_tax_refunded='" + base_tax_refunded + '\'' +
                ", discount_refunded='" + discount_refunded + '\'' +
                ", base_discount_refunded='" + base_discount_refunded + '\'' +
                ", gift_message_id='" + gift_message_id + '\'' +
                ", gift_message_available='" + gift_message_available + '\'' +
                ", base_weee_tax_applied_amount='" + base_weee_tax_applied_amount + '\'' +
                ", base_weee_tax_applied_row_amnt='" + base_weee_tax_applied_row_amnt + '\'' +
                ", base_weee_tax_applied_row_amount='" + base_weee_tax_applied_row_amount + '\'' +
                ", weee_tax_applied_amount='" + weee_tax_applied_amount + '\'' +
                ", weee_tax_applied_row_amount='" + weee_tax_applied_row_amount + '\'' +
                ", weee_tax_disposition='" + weee_tax_disposition + '\'' +
                ", weee_tax_row_disposition='" + weee_tax_row_disposition + '\'' +
                ", base_weee_tax_disposition='" + base_weee_tax_disposition + '\'' +
                ", base_weee_tax_row_disposition='" + base_weee_tax_row_disposition + '\'' +
                ", media=" + media +
                ", product_options=" + product_options +
                '}';
    }

    public ArrayList<CartMediaItem> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<CartMediaItem> media) {
        this.media = media;
    }

    public HistoryProductOptions getProduct_options() {
        return product_options;
    }

    public void setProduct_options(HistoryProductOptions product_options) {
        this.product_options = product_options;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getParent_item_id() {
        return parent_item_id;
    }

    public void setParent_item_id(String parent_item_id) {
        this.parent_item_id = parent_item_id;
    }

    public String getQuote_item_id() {
        return quote_item_id;
    }

    public void setQuote_item_id(String quote_item_id) {
        this.quote_item_id = quote_item_id;
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

    public String getProduct_id() {
        return entity_id;
    }

    public void setProduct_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }


    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getIs_virtual() {
        return is_virtual;
    }

    public void setIs_virtual(String is_virtual) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplied_rule_ids() {
        return applied_rule_ids;
    }

    public void setApplied_rule_ids(String applied_rule_ids) {
        this.applied_rule_ids = applied_rule_ids;
    }

    public String getAdditional_data() {
        return additional_data;
    }

    public void setAdditional_data(String additional_data) {
        this.additional_data = additional_data;
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

    public String getQty_backordered() {
        return qty_backordered;
    }

    public void setQty_backordered(String qty_backordered) {
        this.qty_backordered = qty_backordered;
    }

    public String getQty_canceled() {
        return qty_canceled;
    }

    public void setQty_canceled(String qty_canceled) {
        this.qty_canceled = qty_canceled;
    }

    public String getQty_invoiced() {
        return qty_invoiced;
    }

    public void setQty_invoiced(String qty_invoiced) {
        this.qty_invoiced = qty_invoiced;
    }

    public String getQty_ordered() {
        return qty_ordered;
    }

    public void setQty_ordered(String qty_ordered) {
        this.qty_ordered = qty_ordered;
    }

    public String getQty_refunded() {
        return qty_refunded;
    }

    public void setQty_refunded(String qty_refunded) {
        this.qty_refunded = qty_refunded;
    }

    public String getQty_shipped() {
        return qty_shipped;
    }

    public void setQty_shipped(String qty_shipped) {
        this.qty_shipped = qty_shipped;
    }

    public String getBase_cost() {
        return base_cost;
    }

    public void setBase_cost(String base_cost) {
        this.base_cost = base_cost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBase_price() {
        return base_price;
    }

    public void setBase_price(String base_price) {
        this.base_price = base_price;
    }

    public String getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(String original_price) {
        this.original_price = original_price;
    }

    public String getBase_original_price() {
        return base_original_price;
    }

    public void setBase_original_price(String base_original_price) {
        this.base_original_price = base_original_price;
    }

    public String getTax_percent() {
        return tax_percent;
    }

    public void setTax_percent(String tax_percent) {
        this.tax_percent = tax_percent;
    }

    public String getTax_amount() {
        return tax_amount;
    }

    public void setTax_amount(String tax_amount) {
        this.tax_amount = tax_amount;
    }

    public String getBase_tax_amount() {
        return base_tax_amount;
    }

    public void setBase_tax_amount(String base_tax_amount) {
        this.base_tax_amount = base_tax_amount;
    }

    public String getTax_invoiced() {
        return tax_invoiced;
    }

    public void setTax_invoiced(String tax_invoiced) {
        this.tax_invoiced = tax_invoiced;
    }

    public String getBase_tax_invoiced() {
        return base_tax_invoiced;
    }

    public void setBase_tax_invoiced(String base_tax_invoiced) {
        this.base_tax_invoiced = base_tax_invoiced;
    }

    public String getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(String discount_percent) {
        this.discount_percent = discount_percent;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getBase_discount_amount() {
        return base_discount_amount;
    }

    public void setBase_discount_amount(String base_discount_amount) {
        this.base_discount_amount = base_discount_amount;
    }

    public String getDiscount_invoiced() {
        return discount_invoiced;
    }

    public void setDiscount_invoiced(String discount_invoiced) {
        this.discount_invoiced = discount_invoiced;
    }

    public String getBase_discount_invoiced() {
        return base_discount_invoiced;
    }

    public void setBase_discount_invoiced(String base_discount_invoiced) {
        this.base_discount_invoiced = base_discount_invoiced;
    }

    public String getAmount_refunded() {
        return amount_refunded;
    }

    public void setAmount_refunded(String amount_refunded) {
        this.amount_refunded = amount_refunded;
    }

    public String getBase_amount_refunded() {
        return base_amount_refunded;
    }

    public void setBase_amount_refunded(String base_amount_refunded) {
        this.base_amount_refunded = base_amount_refunded;
    }

    public String getRow_total() {
        return row_total;
    }

    public void setRow_total(String row_total) {
        this.row_total = row_total;
    }

    public String getBase_row_total() {
        return base_row_total;
    }

    public void setBase_row_total(String base_row_total) {
        this.base_row_total = base_row_total;
    }

    public String getRow_invoiced() {
        return row_invoiced;
    }

    public void setRow_invoiced(String row_invoiced) {
        this.row_invoiced = row_invoiced;
    }

    public String getBase_row_invoiced() {
        return base_row_invoiced;
    }

    public void setBase_row_invoiced(String base_row_invoiced) {
        this.base_row_invoiced = base_row_invoiced;
    }

    public String getRow_weight() {
        return row_weight;
    }

    public void setRow_weight(String row_weight) {
        this.row_weight = row_weight;
    }

    public String getBase_tax_before_discount() {
        return base_tax_before_discount;
    }

    public void setBase_tax_before_discount(String base_tax_before_discount) {
        this.base_tax_before_discount = base_tax_before_discount;
    }

    public String getTax_before_discount() {
        return tax_before_discount;
    }

    public void setTax_before_discount(String tax_before_discount) {
        this.tax_before_discount = tax_before_discount;
    }

    public String getExt_order_item_id() {
        return ext_order_item_id;
    }

    public void setExt_order_item_id(String ext_order_item_id) {
        this.ext_order_item_id = ext_order_item_id;
    }

    public String getLocked_do_invoice() {
        return locked_do_invoice;
    }

    public void setLocked_do_invoice(String locked_do_invoice) {
        this.locked_do_invoice = locked_do_invoice;
    }

    public String getLocked_do_ship() {
        return locked_do_ship;
    }

    public void setLocked_do_ship(String locked_do_ship) {
        this.locked_do_ship = locked_do_ship;
    }

    public String getPrice_incl_tax() {
        return price_incl_tax;
    }

    public void setPrice_incl_tax(String price_incl_tax) {
        this.price_incl_tax = price_incl_tax;
    }

    public String getBase_price_incl_tax() {
        return base_price_incl_tax;
    }

    public void setBase_price_incl_tax(String base_price_incl_tax) {
        this.base_price_incl_tax = base_price_incl_tax;
    }

    public String getRow_total_incl_tax() {
        return row_total_incl_tax;
    }

    public void setRow_total_incl_tax(String row_total_incl_tax) {
        this.row_total_incl_tax = row_total_incl_tax;
    }

    public String getBase_row_total_incl_tax() {
        return base_row_total_incl_tax;
    }

    public void setBase_row_total_incl_tax(String base_row_total_incl_tax) {
        this.base_row_total_incl_tax = base_row_total_incl_tax;
    }

    public String getHidden_tax_amount() {
        return hidden_tax_amount;
    }

    public void setHidden_tax_amount(String hidden_tax_amount) {
        this.hidden_tax_amount = hidden_tax_amount;
    }

    public String getBase_hidden_tax_amount() {
        return base_hidden_tax_amount;
    }

    public void setBase_hidden_tax_amount(String base_hidden_tax_amount) {
        this.base_hidden_tax_amount = base_hidden_tax_amount;
    }

    public String getHidden_tax_invoiced() {
        return hidden_tax_invoiced;
    }

    public void setHidden_tax_invoiced(String hidden_tax_invoiced) {
        this.hidden_tax_invoiced = hidden_tax_invoiced;
    }

    public String getBase_hidden_tax_invoiced() {
        return base_hidden_tax_invoiced;
    }

    public void setBase_hidden_tax_invoiced(String base_hidden_tax_invoiced) {
        this.base_hidden_tax_invoiced = base_hidden_tax_invoiced;
    }

    public String getHidden_tax_refunded() {
        return hidden_tax_refunded;
    }

    public void setHidden_tax_refunded(String hidden_tax_refunded) {
        this.hidden_tax_refunded = hidden_tax_refunded;
    }

    public String getBase_hidden_tax_refunded() {
        return base_hidden_tax_refunded;
    }

    public void setBase_hidden_tax_refunded(String base_hidden_tax_refunded) {
        this.base_hidden_tax_refunded = base_hidden_tax_refunded;
    }

    public String getIs_nominal() {
        return is_nominal;
    }

    public void setIs_nominal(String is_nominal) {
        this.is_nominal = is_nominal;
    }

    public String getTax_canceled() {
        return tax_canceled;
    }

    public void setTax_canceled(String tax_canceled) {
        this.tax_canceled = tax_canceled;
    }

    public String getHidden_tax_canceled() {
        return hidden_tax_canceled;
    }

    public void setHidden_tax_canceled(String hidden_tax_canceled) {
        this.hidden_tax_canceled = hidden_tax_canceled;
    }

    public String getTax_refunded() {
        return tax_refunded;
    }

    public void setTax_refunded(String tax_refunded) {
        this.tax_refunded = tax_refunded;
    }

    public String getBase_tax_refunded() {
        return base_tax_refunded;
    }

    public void setBase_tax_refunded(String base_tax_refunded) {
        this.base_tax_refunded = base_tax_refunded;
    }

    public String getDiscount_refunded() {
        return discount_refunded;
    }

    public void setDiscount_refunded(String discount_refunded) {
        this.discount_refunded = discount_refunded;
    }

    public String getBase_discount_refunded() {
        return base_discount_refunded;
    }

    public void setBase_discount_refunded(String base_discount_refunded) {
        this.base_discount_refunded = base_discount_refunded;
    }

    public String getGift_message_id() {
        return gift_message_id;
    }

    public void setGift_message_id(String gift_message_id) {
        this.gift_message_id = gift_message_id;
    }

    public String getGift_message_available() {
        return gift_message_available;
    }

    public void setGift_message_available(String gift_message_available) {
        this.gift_message_available = gift_message_available;
    }

    public String getBase_weee_tax_applied_amount() {
        return base_weee_tax_applied_amount;
    }

    public void setBase_weee_tax_applied_amount(String base_weee_tax_applied_amount) {
        this.base_weee_tax_applied_amount = base_weee_tax_applied_amount;
    }

    public String getBase_weee_tax_applied_row_amnt() {
        return base_weee_tax_applied_row_amnt;
    }

    public void setBase_weee_tax_applied_row_amnt(String base_weee_tax_applied_row_amnt) {
        this.base_weee_tax_applied_row_amnt = base_weee_tax_applied_row_amnt;
    }

    public String getBase_weee_tax_applied_row_amount() {
        return base_weee_tax_applied_row_amount;
    }

    public void setBase_weee_tax_applied_row_amount(String base_weee_tax_applied_row_amount) {
        this.base_weee_tax_applied_row_amount = base_weee_tax_applied_row_amount;
    }

    public String getWeee_tax_applied_amount() {
        return weee_tax_applied_amount;
    }

    public void setWeee_tax_applied_amount(String weee_tax_applied_amount) {
        this.weee_tax_applied_amount = weee_tax_applied_amount;
    }

    public String getWeee_tax_applied_row_amount() {
        return weee_tax_applied_row_amount;
    }

    public void setWeee_tax_applied_row_amount(String weee_tax_applied_row_amount) {
        this.weee_tax_applied_row_amount = weee_tax_applied_row_amount;
    }


    public String getWeee_tax_disposition() {
        return weee_tax_disposition;
    }

    public void setWeee_tax_disposition(String weee_tax_disposition) {
        this.weee_tax_disposition = weee_tax_disposition;
    }

    public String getWeee_tax_row_disposition() {
        return weee_tax_row_disposition;
    }

    public void setWeee_tax_row_disposition(String weee_tax_row_disposition) {
        this.weee_tax_row_disposition = weee_tax_row_disposition;
    }

    public String getBase_weee_tax_disposition() {
        return base_weee_tax_disposition;
    }

    public void setBase_weee_tax_disposition(String base_weee_tax_disposition) {
        this.base_weee_tax_disposition = base_weee_tax_disposition;
    }

    public String getBase_weee_tax_row_disposition() {
        return base_weee_tax_row_disposition;
    }

    public void setBase_weee_tax_row_disposition(String base_weee_tax_row_disposition) {
        this.base_weee_tax_row_disposition = base_weee_tax_row_disposition;
    }
}
