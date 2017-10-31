package com.mohit.shopebazardroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Checkout.CartActivity;
import com.mohit.shopebazardroid.activity.products.ProductDetailActivity;
import com.mohit.shopebazardroid.utility.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msp on 29/9/16.
 */
public class BaseActivity extends AppCompatActivity {

    int theme_code;
    Menu menu;
    View cartBadget;
    protected static TextView cartItemBadge;
    private String fragmentTAG;
    private static String customerid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "");
    private static boolean is_login_compulsory = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme_code = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);
        setTheme(R.style.AppThemeActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String className = this.getClass().getSimpleName();
        String hideCartString = readJsonFileFromAsset("hide_cart.json");
        List<String> hideClassNameList =new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(hideCartString);
            for (int i = 0; i < jsonArray.length(); i++) {
                hideClassNameList.add(jsonArray.getString(i));
            }

            if(hideClassNameList.contains(className))
                return false;
            else{
                this.menu = menu;
                getMenuInflater().inflate(R.menu.main_menu, menu);

                if(className.equalsIgnoreCase(ProductDetailActivity.class.getSimpleName())){
                    MenuItem shareItem = menu.findItem(R.id.action_share);
                    shareItem.setVisible(false);
                }

                MenuItem item = menu.findItem(R.id.action_search);
                item.setVisible(false);
                //        searchView.setMenuItem(item);
                LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context
                        .LAYOUT_INFLATER_SERVICE);
                cartBadget = inflater.inflate(R.layout.cart_badge_actionbar, null);
                cartBadget.setLayoutParams(new RelativeLayout.LayoutParams(item.getIcon()
                        .getIntrinsicWidth() + 50, item.getIcon().getIntrinsicHeight() + 20));

                cartItemBadge = (TextView) cartBadget.findViewById(R.id.counter);
                //        textView.setText("0");
                cartItemBadge.setText("" + MyApplication.preferenceGetString(
                        AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
                MenuItem item2 = menu.findItem(R.id.action_cart);
                item2.setActionView(cartBadget);

                cartBadget.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /*if (MyApplication.preferenceGetBoolean(AppConstants.SharedPreferenceKeys
                                .IS_LOGGED_IN, false)) {
                            startActivity(new Intent(BaseActivity.this, CartActivity.class));
                        } else {
                            finish();
                            startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                        }*/

                        startActivity(new Intent(BaseActivity.this, CartActivity.class));
                    }
                });

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        customerid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "");

        if(cartBadget != null){
            cartItemBadge.setText("" + MyApplication.preferenceGetString(
                    AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case R.id.action_cart:
//                Utility.toastMessage(mContext, "Cart clicked");
                startActivity(new Intent(BaseActivity.this, CartActivity.class));
//                startActivity(new Intent(mContext, ActivityStripePaymentGateway.class));
                return true;
        }


    }

    public String readJsonFileFromAsset(String filename){
        String json = null;
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public boolean isUserLogin(){
        if(TextUtils.isEmpty(customerid))
            return  false;
        else
            return true;
    }

    public void  setUserLoggedIn(){
        customerid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "");
    }

    public void setUserLoggedOut(){
        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.USER_ID, "");
        customerid = "";
    }

    public String getFragmentTAG() {
        return fragmentTAG;
    }

    public void setFragmentTAG(String fragmentTAG) {
        this.fragmentTAG = fragmentTAG;
    }

    public String getUserid(){
        return customerid;
    }

    public String getStoreID(){
        return MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "1");
    }

    public boolean getIs_login_compulsory() {
        return is_login_compulsory;
    }

    public  void setIs_login_compulsory(boolean is_login_compulsory) {
        this.is_login_compulsory = is_login_compulsory;
    }

    public void updateCartCount(){
        if(cartItemBadge == null){
            cartItemBadge = (TextView) cartBadget.findViewById(R.id.counter);
        }
        cartItemBadge.setText(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
        cartItemBadge.refreshDrawableState();
    }

    public String getFirebaseId(){
        return MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.GCM_TOKEN, "");
    }

    public void setFirebaseId(String firebaseid){
        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.GCM_TOKEN, firebaseid);
    }

    public String getStringPreferencesValue(String property){
        return MyApplication.preferenceGetString(property, "");
    }

    public int getIntPreferencesValue(String property){
        return MyApplication.preferenceGetInteger(property, -1);
    }



}