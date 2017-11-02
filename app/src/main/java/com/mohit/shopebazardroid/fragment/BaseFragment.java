package com.mohit.shopebazardroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.activity.BaseActivity;


/**
 * Created by msp on 24/8/17.
 */

public class BaseFragment extends Fragment {
    BaseActivity baseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = new BaseActivity();
    }

    public boolean isUserLogin(){
        if(baseActivity != null)
            return  baseActivity.isUserLogin();
        else
            return false;
    }

    public String getUserid(){
        if(baseActivity != null)
            return baseActivity.getUserid();
        else
            return "";
    }

    public String getStoreID(){
        if(baseActivity != null)
            return baseActivity.getStoreID();
        else
            return "";
    }

    public void updateCartCount(){
        if(baseActivity != null){
            baseActivity.updateCartCount();
        }
    }

    public String getFirebaseId(){
        if(baseActivity != null){
            return baseActivity.getFirebaseId();
        } else{
            return "";
        }
    }

    public String getStringPreferencesValue(String property){
        if(baseActivity != null){
            return MyApplication.preferenceGetString(property, "");
        } else{
            return "";
        }

    }

    public int getIntPreferencesValue(String property){
        if(baseActivity != null){
            return MyApplication.preferenceGetInteger(property, -1);
        } else{
            return -1;
        }
    }
}
