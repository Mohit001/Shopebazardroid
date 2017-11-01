package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by msp on 30/5/17.
 */


public class RelatedProductList implements Serializable {

    public static String KEY_OBJECT = "product";
    private String entity_id;
    private String sku;
    private String set;
    private String type;
    private String type_id;
    private String name;
    private String description;
    private String short_description;
    private String weight;
    private String news_from_date;
    private String old_id;
    private String news_to_date;
    private String status;
    private String url_key;
    private String visibility;
    private String url_path;
    private String country_of_manufacture;
    private String megnor_featured_product;
    private String manufacturer;
    private String required_options;
    private String has_options;
    private String image_label;
    private String small_image_label;
    private String thumbnail_label;
    private String created_at;
    private String updated_at;
    private String price;
    private String special_price;
    private String special_from_date;
    private String special_to_date;
    private String minimal_price;
    private String msrp_enabled;
    private String msrp_display_actual_price_type;
    private String msrp;
    private String guest_hide_price;
    private String tax_class_id;
    private String meta_title;
    private String meta_keyword;
    private String meta_description;
    private String is_recurring;
    private String recurring_profile;
    private String custom_design;
    private String custom_design_from;
    private String custom_design_to;
    private String custom_layout_update;
    private String page_layout;
    private String options_container;
    private String gift_message_available;
    private String shirt_size;
    private String arw_brand;
    private ArrayList<Custom_option> custom_option;
    private ArrayList<RelatedProductResponse.Media> media;
    private ArrayList<Configurable_option> configurable_option;
    private String image;
    private String small_image;
    private String thumbnail;

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

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
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

    public String getNews_from_date() {
        return news_from_date;
    }

    public void setNews_from_date(String news_from_date) {
        this.news_from_date = news_from_date;
    }

    public String getOld_id() {
        return old_id;
    }

    public void setOld_id(String old_id) {
        this.old_id = old_id;
    }

    public String getNews_to_date() {
        return news_to_date;
    }

    public void setNews_to_date(String news_to_date) {
        this.news_to_date = news_to_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl_key() {
        return url_key;
    }

    public void setUrl_key(String url_key) {
        this.url_key = url_key;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getUrl_path() {
        return url_path;
    }

    public void setUrl_path(String url_path) {
        this.url_path = url_path;
    }

    public String getCountry_of_manufacture() {
        return country_of_manufacture;
    }

    public void setCountry_of_manufacture(String country_of_manufacture) {
        this.country_of_manufacture = country_of_manufacture;
    }

    public String getMegnor_featured_product() {
        return megnor_featured_product;
    }

    public void setMegnor_featured_product(String megnor_featured_product) {
        this.megnor_featured_product = megnor_featured_product;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRequired_options() {
        return required_options;
    }

    public void setRequired_options(String required_options) {
        this.required_options = required_options;
    }

    public String getHas_options() {
        return has_options;
    }

    public void setHas_options(String has_options) {
        this.has_options = has_options;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(String special_price) {
        this.special_price = special_price;
    }

    public String getSpecial_from_date() {
        return special_from_date;
    }

    public void setSpecial_from_date(String special_from_date) {
        this.special_from_date = special_from_date;
    }

    public String getSpecial_to_date() {
        return special_to_date;
    }

    public void setSpecial_to_date(String special_to_date) {
        this.special_to_date = special_to_date;
    }

    public String getMinimal_price() {
        return minimal_price;
    }

    public void setMinimal_price(String minimal_price) {
        this.minimal_price = minimal_price;
    }

    public String getMsrp_enabled() {
        return msrp_enabled;
    }

    public void setMsrp_enabled(String msrp_enabled) {
        this.msrp_enabled = msrp_enabled;
    }

    public String getMsrp_display_actual_price_type() {
        return msrp_display_actual_price_type;
    }

    public void setMsrp_display_actual_price_type(String msrp_display_actual_price_type) {
        this.msrp_display_actual_price_type = msrp_display_actual_price_type;
    }

    public String getMsrp() {
        return msrp;
    }

    public void setMsrp(String msrp) {
        this.msrp = msrp;
    }

    public String getGuest_hide_price() {
        return guest_hide_price;
    }

    public void setGuest_hide_price(String guest_hide_price) {
        this.guest_hide_price = guest_hide_price;
    }

    public String getTax_class_id() {
        return tax_class_id;
    }

    public void setTax_class_id(String tax_class_id) {
        this.tax_class_id = tax_class_id;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getMeta_keyword() {
        return meta_keyword;
    }

    public void setMeta_keyword(String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getIs_recurring() {
        return is_recurring;
    }

    public void setIs_recurring(String is_recurring) {
        this.is_recurring = is_recurring;
    }

    public String getRecurring_profile() {
        return recurring_profile;
    }

    public void setRecurring_profile(String recurring_profile) {
        this.recurring_profile = recurring_profile;
    }

    public String getCustom_design() {
        return custom_design;
    }

    public void setCustom_design(String custom_design) {
        this.custom_design = custom_design;
    }

    public String getCustom_design_from() {
        return custom_design_from;
    }

    public void setCustom_design_from(String custom_design_from) {
        this.custom_design_from = custom_design_from;
    }

    public String getCustom_design_to() {
        return custom_design_to;
    }

    public void setCustom_design_to(String custom_design_to) {
        this.custom_design_to = custom_design_to;
    }

    public String getCustom_layout_update() {
        return custom_layout_update;
    }

    public void setCustom_layout_update(String custom_layout_update) {
        this.custom_layout_update = custom_layout_update;
    }

    public String getPage_layout() {
        return page_layout;
    }

    public void setPage_layout(String page_layout) {
        this.page_layout = page_layout;
    }

    public String getOptions_container() {
        return options_container;
    }

    public void setOptions_container(String options_container) {
        this.options_container = options_container;
    }

    public String getGift_message_available() {
        return gift_message_available;
    }

    public void setGift_message_available(String gift_message_available) {
        this.gift_message_available = gift_message_available;
    }

    public String getShirt_size() {
        return shirt_size;
    }

    public void setShirt_size(String shirt_size) {
        this.shirt_size = shirt_size;
    }

    public String getArw_brand() {
        return arw_brand;
    }

    public void setArw_brand(String arw_brand) {
        this.arw_brand = arw_brand;
    }

    public ArrayList<Custom_option> getCustom_option() {
        return custom_option;
    }

    public void setCustom_option(ArrayList<Custom_option> custom_option) {
        this.custom_option = custom_option;
    }

    public ArrayList<RelatedProductResponse.Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<RelatedProductResponse.Media> media) {
        this.media = media;
    }

    public ArrayList<Configurable_option> getConfigurable_option() {
        return configurable_option;
    }

    public void setConfigurable_option(ArrayList<Configurable_option> configurable_option) {
        this.configurable_option = configurable_option;
    }

    public static class Custom_option {
        private String option_id;
        private String title;
        private String type;
        private String is_require;
        private String sort_order;
        private ArrayList<Value_option> value_option;

        public String getOption_id() {
            return option_id;
        }

        public void setOption_id(String option_id) {
            this.option_id = option_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIs_require() {
            return is_require;
        }

        public void setIs_require(String is_require) {
            this.is_require = is_require;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public ArrayList<Value_option> getValue_option() {
            return value_option;
        }

        public void setValue_option(ArrayList<Value_option> value_option) {
            this.value_option = value_option;
        }
    }

    public static class Value_option {
        private String value_id;
        private String title;
        private String price;
        private String price_type;
        private String sku;
        private String sort_order;

        public String getValue_id() {
            return value_id;
        }

        public void setValue_id(String value_id) {
            this.value_id = value_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_type() {
            return price_type;
        }

        public void setPrice_type(String price_type) {
            this.price_type = price_type;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }
    }

    public static class Configurable_option {

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
}


