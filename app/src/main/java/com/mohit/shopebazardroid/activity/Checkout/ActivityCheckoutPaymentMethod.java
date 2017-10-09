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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.adapter.PaymentMethodAdapter;
import com.mohit.shopebazardroid.enums.ApiResponseStatus;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.response.PaymentCCTypes;
import com.mohit.shopebazardroid.models.PaymentMethod;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msp on 26/7/16.
 */
public class ActivityCheckoutPaymentMethod extends BaseActivity implements ApiResponse,
        AddressListner, View.OnClickListener {

    public static String TAG = ActivityShippingMethod.class.getSimpleName();
    private Context mContext;

    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    List<PaymentMethod> arrayList = new ArrayList<>();
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

        HTTPWebRequest.GetPaymentMethodList(mContext, AppConstants.APICode.PAYMENT_METHOD_LIST, this, getSupportFragmentManager());

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

                Type getPaymentMethodType = new TypeToken<BaseResponse<List<PaymentMethod>>>(){}.getType();
                Gson gson = new GsonBuilder().serializeNulls().create();
                BaseResponse<List<PaymentMethod>> baseResponse = gson.fromJson(response, getPaymentMethodType);

                if (baseResponse.getStatus() == ApiResponseStatus.CART_PAYMENT_TYPE_LIST_SUCCESS.getStatus_code()
                        && baseResponse.getInfo().size() > 0) {

                    if(arrayList == null)
                        arrayList = new ArrayList<>();
                    else{
                        arrayList.clear();
                    }

                    arrayList.addAll(baseResponse.getInfo());
                    adapter = new PaymentMethodAdapter(mContext, arrayList, this);
                    mRecyclerView.setAdapter(adapter);
                    selectedPaymentMethod = arrayList.get(0);
                }
                break;

            case AppConstants.APICode.PAYMENT_METHOD_SELECTION:

                Type setPaymentMethodToCartType = new TypeToken<BaseResponse<List<PaymentMethod>>>(){}.getType();
                Gson gson1 = new GsonBuilder().serializeNulls().create();
                BaseResponse<List<PaymentMethod>> baseResponse1 = gson1.fromJson(response, setPaymentMethodToCartType);



                if (baseResponse1.getStatus() == ApiResponseStatus.CART_PAYMENT_TYPE_UPDATE_SUCCESS.getStatus_code()) {
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

                String cart_id = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0");
                String payment_type_id = String.valueOf(selectedPaymentMethod.getId());

                HTTPWebRequest.SetPaymentMethodToCart(mContext, cart_id, payment_type_id, AppConstants.APICode.PAYMENT_METHOD_SELECTION, this, getSupportFragmentManager());
                break;
        }

    }

}