package com.mohit.shopebazardroid.model.request;

/**
 * Created by root on 6/7/16.
 */
public class LoginRequest
{
    private String email;
    private String password;
    private String device_id;
    private String device_type;
    private String ShoppingCartID;

    @Override
    public String toString()
    {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", device_id='" + device_id + '\'' +
                ", device_type='" + device_type + '\'' +
                '}';
    }

    public String getDevice_id()
    {
        return device_id;
    }

    public void setDevice_id(String device_id)
    {
        this.device_id = device_id;
    }

    public String getDevice_type()
    {
        return device_type;
    }

    public void setDevice_type(String device_type)
    {
        this.device_type = device_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShoppingCartID() {
        return ShoppingCartID;
    }

    public void setShoppingCartID(String shoppingCartID) {
        ShoppingCartID = shoppingCartID;
    }
}
