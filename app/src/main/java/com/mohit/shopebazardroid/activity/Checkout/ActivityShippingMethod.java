package com.mohit.shopebazardroid.activity.Checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.adapter.ShippingMethodAdapter;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.ShippingRequest;
import com.mohit.shopebazardroid.model.response.ShippingMethod;
import com.mohit.shopebazardroid.model.response.ShippingResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;

/**
 * Created by msp on 4/8/16.
 */
public class ActivityShippingMethod extends BaseActivity implements ApiResponse, AddressListner, View.OnClickListener {

    public static String TAG = ActivityShippingMethod.class.getSimpleName();
    private Context mContext;


    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<ShippingMethod> arrayList;
    ShippingMethodAdapter adapter;
    ShippingMethod selectedShippingMethod = null;
    RelativeLayout relativeLayout;
    Button continueTextView;

    int themeCode;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "store_id");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_method);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Shipping Method");


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        relativeLayout = (RelativeLayout) findViewById(R.id.cart_gross_total_rl);
        continueTextView = (Button) findViewById(R.id.proceed_checkout_btn);

        layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        continueTextView.setText("Continue");
        continueTextView.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        ShippingRequest request = new ShippingRequest();
        request.setCartid(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0"));
        request.setAction(String.valueOf(1));
        HTTPWebRequest.GetShippingMethodList(mContext, request, AppConstants.APICode.SHIPPING_METHOD_LIST, this, getSupportFragmentManager());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                onBackPressed();
                return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, CartActivity.class));
        this.finish();
    }
    @Override
    public void onEditAddressClick(int index) {

    }

    @Override
    public void onDeleteAddressClick(int index) {

    }

    @Override
    public void onSelectionChange(int index) {

        ShippingMethod shippingMethod;
        for (int i = 0; i < arrayList.size(); i++) {
            shippingMethod = arrayList.get(i);
            if (i == index) {
                shippingMethod.setSelected(true);
                selectedShippingMethod = shippingMethod;
            } else {
                shippingMethod.setSelected(false);
            }

            arrayList.set(i, shippingMethod);
        }

        adapter = new ShippingMethodAdapter(mContext, arrayList, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.SHIPPING_METHOD_LIST:
                ShippingResponse shippingResponse = new Gson().fromJson(response, ShippingResponse.class);

                if (shippingResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (shippingResponse.getStatus().equalsIgnoreCase("success")) {
                    arrayList = shippingResponse.getResult().getShppinglist();
                    adapter = new ShippingMethodAdapter(mContext, arrayList, this);
                    mRecyclerView.setAdapter(adapter);
                }


                break;

            case AppConstants.APICode.SHIPPING_METHOD_SELECTION:
                ShippingResponse shippingResponse1 = new Gson().fromJson(response, ShippingResponse.class);

                if (shippingResponse1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (shippingResponse1.getStatus().equalsIgnoreCase("success")) {
                    ActivityCheckoutOrderReview.shippingCharge = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 1) * Float.parseFloat(selectedShippingMethod.getPrice());
                    startActivity(new Intent(mContext, ActivityCheckoutPaymentMethod.class));
                    finish();
                }
                break;
        }
    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(int apiCode) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.proceed_checkout_btn:


                if (selectedShippingMethod == null) {
                    Utility.toastMessage(mContext, "Please select shipping method");
                    return;
                }
                ShippingRequest request = new ShippingRequest();
                request.setCartid(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0"));
                request.setAction(String.valueOf(2));
                request.setCode(selectedShippingMethod.getCode());
                HTTPWebRequest.SetShippingMethodToCart(mContext, request, AppConstants.APICode.SHIPPING_METHOD_SELECTION,
                        this, getSupportFragmentManager());
                break;

        }

    }

}
