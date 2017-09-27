package com.mohit.shopebazardroid.model.response;


/**
 * Created by msp on 6/7/16.
 */
public class Result
{
    private String message;

    private Userinfo userinfo;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Userinfo getUserinfo ()
    {
        return userinfo;
    }

    public void setUserinfo (Userinfo userinfo)
    {
        this.userinfo = userinfo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", userinfo = "+userinfo+"]";
    }
}
