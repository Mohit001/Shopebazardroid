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
import com.mohit.shopebazardroid.adapter.PaymentMethodAdapter;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.PaymentRequest;
import com.mohit.shopebazardroid.model.response.PaymentCCTypes;
import com.mohit.shopebazardroid.model.response.PaymentMethod;
import com.mohit.shopebazardroid.model.response.PaymentResponse;
import com.mohit.shopebazardroid.model.response.ShippingResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;

/**
 * Created by msp on 26/7/16.
 */
public class ActivityCheckoutPaymentMethod extends BaseActivity implements ApiResponse,
        AddressListner, View.OnClickListener {

    public static String TAG = ActivityShippingMethod.class.getSimpleName();
    private Context mContext;

    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    ArrayList<PaymentMethod> arrayList = new ArrayList<>();
    PaymentMethodAdapter adapter;
    PaymentMethod selectedPaymentMethod = null;
    RelativeLayout relativeLayout;
    Button continueTextView;
    PaymentCCTypes paymentCCTypes;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Payment Method");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        relativeLayout = (RelativeLayout) findViewById(R.id.cart_gross_total_rl);
        continueTextView = (Button) findViewById(R.id.proceed_checkout_btn);

        layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        continueTextView.setText("Continue");
        continueTextView.setOnClickListener(this);
        relativeLayout.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        PaymentRequest request = new PaymentRequest();
        request.setCartid(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0"));
        request.setAction(String.valueOf(1));
        HTTPWebRequest.GetPaymentMethodList(mContext, request, AppConstants.APICode.PAYMENT_METHOD_LIST, this, getSupportFragmentManager());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
//                startActivity(new Intent(mContext, ActivityShippingMethod.class));
                finish();
                return true;
        }
    }

    @Override
    public void onEditAddressClick(int index) {

    }

    @Override
    public void onDeleteAddressClick(int index) {

    }

    @Override
    public void onSelectionChange(int index) {

        PaymentMethod shippingMethod;
        for (int i = 0; i < arrayList.size(); i++) {
            shippingMethod = arrayList.get(i);
            if (i == index) {
                shippingMethod.setSelected(true);
                selectedPaymentMethod = shippingMethod;
            } else {
                shippingMethod.setSelected(false);
            }

            arrayList.set(i, shippingMethod);
        }

        adapter = new PaymentMethodAdapter(mContext, arrayList, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.PAYMENT_METHOD_LIST:
                PaymentResponse shippingResponse = new Gson().fromJson(response, PaymentResponse
                        .class);

                if (shippingResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (shippingResponse.getStatus().equalsIgnoreCase("success")) {
                    arrayList = shippingResponse.getResult().getPaymentlist();
                    adapter = new PaymentMethodAdapter(mContext, arrayList, this);
                    mRecyclerView.setAdapter(adapter);
                }
                break;

            case AppConstants.APICode.PAYMENT_METHOD_SELECTION:
                ShippingResponse shippingResponse1 = new Gson().fromJson(response,
                        ShippingResponse.class);

                if (shippingResponse1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    this.finish();
                    return;
                }

                if (shippingResponse1.getStatus().equalsIgnoreCase("success")) {
                    Intent cartIntent  = new Intent(mContext, ActivityCheckoutOrderReview.class);
                    cartIntent.putExtra(AppConstants.INTENTDATA.PAYMENT_METHOD, selectedPaymentMethod);
                    startActivity(cartIntent);
//                    startActivity(new Intent(mContext, ActivityCheckoutOrderReview.class));
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
                if (selectedPaymentMethod == null) {
                    Utility.toastMessage(mContext, getString(R.string.error_select_payment_method));
                    return;
                }



                PaymentRequest request = new PaymentRequest();
                request.setCartid(MyApplication.preferenceGetString(AppConstants
                        .SharedPreferenceKeys.CART_ID, "0"));
                request.setAction(String.valueOf(2));
                request.setCode(selectedPaymentMethod.getCode());
                HTTPWebRequest.SetPaymentMethodToCart(mContext, request, AppConstants.APICode
                        .PAYMENT_METHOD_SELECTION, this, getSupportFragmentManager());
                break;
        }

    }

}