package com.mohit.shopebazardroid.model.response;

import java.io.Serializable;

/**
 * Created by msp on 26/7/16.
 */
public class Address implements Serializable {


    String customer_address_id;
    int index;
    String houseOfficeNo;
    String firstname;
    String street;
    String landmark;
    String city;
    String region;
    String region_id;
    String country;
    String country_id;
    String area;
    String postcode;
    String telephone;
    String email;
    boolean selected;
    boolean is_default_billing;
    boolean is_default_shipping;
    String created_at;
    String updated_at;
    String customer_id;
    String save_in_address_book;
    String address_type;
    int same_as_billing;
    int free_shipping;
    String shipping_method;
    String shipping_description;
    double weight;


    @Override
    public String toString() {
        return "Address{" +
                "customer_address_id='" + customer_address_id + '\'' +
                ", index=" + index +
                ", houseOfficeNo='" + houseOfficeNo + '\'' +
                ", firstname='" + firstname + '\'' +
                ", street='" + street + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", region_id='" + region_id + '\'' +
                ", country='" + country + '\'' +
                ", country_id='" + country_id + '\'' +
                ", area='" + area + '\'' +
                ", postcode='" + postcode + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", selected=" + selected +
                ", is_default_billing=" + is_default_billing +
                ", is_default_shipping=" + is_default_shipping +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", save_in_address_book='" + save_in_address_book + '\'' +
                ", address_type='" + address_type + '\'' +
                ", same_as_billing=" + same_as_billing +
                ", free_shipping=" + free_shipping +
                ", shipping_method='" + shipping_method + '\'' +
                ", shipping_description='" + shipping_description + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSave_in_address_book() {
        return save_in_address_book;
    }

    public void setSave_in_address_book(String save_in_address_book) {
        this.save_in_address_book = save_in_address_book;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public int getSame_as_billing() {
        return same_as_billing;
    }

    public void setSame_as_billing(int same_as_billing) {
        this.same_as_billing = same_as_billing;
    }

    public int getFree_shipping() {
        return free_shipping;
    }

    public void setFree_shipping(int free_shipping) {
        this.free_shipping = free_shipping;
    }

    public String getShipping_method() {
        return shipping_method;
    }

    public void setShipping_method(String shipping_method) {
        this.shipping_method = shipping_method;
    }

    public String getShipping_description() {
        return shipping_description;
    }

    public void setShipping_description(String shipping_description) {
        this.shipping_description = shipping_description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean is_default_billing() {
        return is_default_billing;
    }

    public void setIs_default_billing(boolean is_default_billing) {
        this.is_default_billing = is_default_billing;
    }

    public boolean is_default_shipping() {
        return is_default_shipping;
    }

    public void setIs_default_shipping(boolean is_default_shipping) {
        this.is_default_shipping = is_default_shipping;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCustomer_address_id() {
        return customer_address_id;
    }

    public void setCustomer_address_id(String customer_address_id) {
        this.customer_address_id = customer_address_id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getHouseOfficeNo() {
        return houseOfficeNo;
    }

    public void setHouseOfficeNo(String houseOfficeNo) {
        this.houseOfficeNo = houseOfficeNo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
