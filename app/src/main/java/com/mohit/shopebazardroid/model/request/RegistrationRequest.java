package com.mohit.shopebazardroid.model.request;

/**
 * Created by msp on 6/7/16.
 */
public class RegistrationRequest
{
    String userid;
    String firstname;
    String middlename;
    String lastname;
    String email;
    String password;
    String action; // 1=registration, 2 = update profile
    String device_type; // 1=android, 2=iphone
    String device_token;


    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "userid='" + userid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", action='" + action + '\'' +
                ", device_type='" + device_type + '\'' +
                ", device_token='" + device_token + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getMiddlename()
    {
        return middlename;
    }

    public void setMiddlename(String middlename)
    {
        this.middlename = middlename;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public String getDevice_type()
    {
        return device_type;
    }

    public void setDevice_type(String device_type)
    {
        this.device_type = device_type;
    }

    public String getDevice_token()
    {
        return device_token;
    }

    public void setDevice_token(String device_token)
    {
        this.device_token = device_token;
    }
}
