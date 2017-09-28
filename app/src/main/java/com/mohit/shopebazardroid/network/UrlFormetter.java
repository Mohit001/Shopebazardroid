package com.mohit.shopebazardroid.network;

import android.content.Context;

import com.mohit.shopebazardroid.R;

/**
 * Created by msp on 9/28/17.
 */

public class UrlFormetter {

    public static String getURL(Context mContext, int api, String... param){
        StringBuilder builder =  new StringBuilder();
        builder.append(mContext.getString(R.string.api_base_url));

        if(param.length != 0){
            builder.append(String.format(mContext.getString(api), param));
        } else {
            builder.append(mContext.getString(api));
        }

        return builder.toString();
    }
}
