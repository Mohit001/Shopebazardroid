package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by msp on 23/7/16.
 */
public class ProductEntity implements Serializable {

    public static String KEY_OBJECT="product";

    private String imageurl;
    private String entity_id;
    private String sku;
    private String type;
    private String type_id;
    private String name;
    private String description;
    private String short_description;
    private String weight;
    private String manufacturer;
    private String image_label;
    private String small_image_label;
    private String thumbnail_label;
    private String price;
    private double priceDouble;
    private String special_price;
    private String minimal_price;
    private ArrayList<ProductBanner> media;
    private String product_url;
    private String url;
    private ArrayList<ProductCustomOption> custom_option=new ArrayList<>();
    private ArrayList<ProductConfigurableOption> configurable_option=new ArrayList<>();
    private String image;
    private String small_image;
    private String thumbnail;
    private String return_title;
    private String return_content;
    private String qty;

    @Override
    public String toString() {
        return "ProductEntity{" +
                "imageurl='" + imageurl + '\'' +
                ", entity_id='" + entity_id + '\'' +
                ", sku='" + sku + '\'' +
                ", type='" + type + '\'' +
                ", type_id='" + type_id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", short_description='" + short_description + '\'' +
                ", weight='" + weight + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", image_label='" + image_label + '\'' +
                ", small_image_label='" + small_image_label + '\'' +
                ", thumbnail_label='" + thumbnail_label + '\'' +
                ", price='" + price + '\'' +
                ", priceDouble=" + priceDouble +
                ", special_price='" + special_price + '\'' +
                ", minimal_price='" + minimal_price + '\'' +
                ", media=" + media +
                ", custom_option=" + custom_option +
                ", configurable_option=" + configurable_option +
                '}';
    }

    public double getPriceDouble() {
        return priceDouble;
    }

    public void setPriceDouble(double priceDouble) {
        this.priceDouble = priceDouble;
    }

    public ArrayList<ProductBanner> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<ProductBanner> media) {
        this.media = media;
    }

    public String getProduct_id() {
        return entity_id;
    }

    public void setProduct_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
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

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getImage_label() {
        return image_label;
    }

    public void setImage_label(String image_label) {
        this.image_label = image_label;
    }

    public String getSmall_image_label() {
        return small_image_label;
    }

    public void setSmall_image_label(String small_image_label) {
        this.small_image_label = small_image_label;
    }

    public String getThumbnail_label() {
        return thumbnail_label;
    }

    public void setThumbnail_label(String thumbnail_label) {
        this.thumbnail_label = thumbnail_label;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        this.priceDouble = Double.parseDouble(price);
    }

    public String getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(String special_price) {
        this.special_price = special_price;
    }

    public String getMinimal_price() {
        return minimal_price;
    }

    public void setMinimal_price(String minimal_price) {
        this.minimal_price = minimal_price;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public static String getKeyObject() {
        return KEY_OBJECT;
    }

    public static void setKeyObject(String keyObject) {
        KEY_OBJECT = keyObject;
    }

    public ArrayList<ProductCustomOption> getCustom_option() {
        return custom_option;
    }

    public void setCustom_option(ArrayList<ProductCustomOption> custom_option) {
        this.custom_option = custom_option;
    }

    public ArrayList<ProductConfigurableOption> getConfigurable_option() {
        return configurable_option;
    }

    public void setConfigurable_option(ArrayList<ProductConfigurableOption> configurable_option) {
        this.configurable_option = configurable_option;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getProduct_url() {
        return product_url;
    }

    public void setProduct_url(String product_url) {
        this.product_url = product_url;
    }

    public String getReturn_title() {
        return return_title;
    }

    public void setReturn_title(String return_title) {
        this.return_title = return_title;
    }

    public String getReturn_content() {
        return return_content;
    }

    public void setReturn_content(String return_content) {
        this.return_content = return_content;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
