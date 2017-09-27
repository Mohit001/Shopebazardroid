package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 1/8/16.
 */
public class AddressRequest {
    String user_id;
    String action;
    String firstname;
    String lastname;
    String street;
    String street2;
    String city;
    String country_id;
    String region;
    String postcode;
    String telephone;
    String addressId;
    String region_id;
    String area;
    int mode;
    String shoppingCartID;
    int defaultBillingAddress;
    int defaultShippingAddress;


    @Override
    public String toString() {
        return "AddressRequest{" +
                "user_id='" + user_id + '\'' +
                ", action='" + action + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", street='" + street + '\'' +
                ", street2='" + street2 + '\'' +
                ", city='" + city + '\'' +
                ", country_id='" + country_id + '\'' +
                ", region='" + region + '\'' +
                ", postcode='" + postcode + '\'' +
                ", telephone='" + telephone + '\'' +
                ", addressId='" + addressId + '\'' +
                ", region_id='" + region_id + '\'' +
                ", area='" + area + '\'' +
                ", mode=" + mode +
                ", shoppingCartID='" + shoppingCartID + '\'' +
                ", defaultBillingAddress=" + defaultBillingAddress +
                ", defaultShippingAddress=" + defaultShippingAddress +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getDefaultBillingAddress() {
        return defaultBillingAddress;
    }

    public void setDefaultBillingAddress(int defaultBillingAddress) {
        this.defaultBillingAddress = defaultBillingAddress;
    }

    public int getDefaultShippingAddress() {
        return defaultShippingAddress;
    }

    public void setDefaultShippingAddress(int defaultShippingAddress) {
        this.defaultShippingAddress = defaultShippingAddress;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getShoppingCartID() {
        return shoppingCartID;
    }

    public void setShoppingCartID(String shoppingCartID) {
        this.shoppingCartID = shoppingCartID;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }
}
