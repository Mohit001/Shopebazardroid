package com.mohit.shopebazardroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.CurrencyAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.CurrencyListner;
import com.mohit.shopebazardroid.model.request.NotificationRequest;
import com.mohit.shopebazardroid.model.request.SetCurrencyRequest;
import com.mohit.shopebazardroid.model.response.BasicResponse;
import com.mohit.shopebazardroid.model.response.CurrencyEntity;
import com.mohit.shopebazardroid.model.response.LoginResponse;
import com.mohit.shopebazardroid.model.response.SettingResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


/**
 * Created by msp on 21/7/16.
 */
public class SettingFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener,
        CurrencyListner, ApiResponse {
    public static String TAG = SettingFragment.class.getSimpleName();
    Context mContext;
    SwitchCompat switchCompat;
    ArrayList<CurrencyEntity> currencyEntityArrayList;
    CurrencyAdapter mAdapter;
    RecyclerView mRecyclerView;
    CurrencyEntity selectedCurrencyEntity;
    AppCompatTextView base_currency;
    boolean b;

    TextView txt_change_store;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        switchCompat = (SwitchCompat) view.findViewById(R.id.setting_notification_switch);
        switchCompat.setTypeface(SplashActivity.opensans_semi_bold);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        txt_change_store = (TextView) view.findViewById(R.id.txt_change_store);
        txt_change_store.setTypeface(SplashActivity.opensans_semi_bold);
//        txt_change_store.setOnClickListener(this);

        base_currency = (AppCompatTextView) view.findViewById(R.id.base_currency);
        base_currency.setTypeface(SplashActivity.opensans_semi_bold);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);

        int isChecked = Integer.parseInt(MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.IS_NOTIFICATION, "0"));
        switchCompat.setChecked(isChecked == 1 ? true : false);
        switchCompat.setOnCheckedChangeListener(this);

        String showNotification = MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.IS_NOTIFICATION, "1");
        /*if(showNotification.equalsIgnoreCase("0"))
        {
            switchCompat.setVisibility(View.GONE);
        }*/

//        HTTPWebRequest.Basic(mContext, AppConstants.APICode.BASIC, this, getFragmentManager());
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//        Utility.toastMessage(mContext, "Notification : "+b);
        String isChecked = b ? "1" : "0";
        NotificationRequest request = new NotificationRequest();
        request.setUserid(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .USER_ID, "0"));
        request.setStatus(isChecked);
        HTTPWebRequest.Notification(mContext, request, AppConstants.APICode.NOTIFICATION, this,
                getFragmentManager());


    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.NOTIFICATION:
                LoginResponse loginResponse = new Gson().fromJson(response, LoginResponse.class);
                if (loginResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (!loginResponse.getStatus().equalsIgnoreCase("success")) {
                    switchCompat.setChecked(!switchCompat.isChecked());
                }

//                Utility.toastMessage(mContext, loginResponse.getResult().getMessage());
                break;
            case AppConstants.APICode.BASIC:
                BasicResponse basicResponse = new Gson().fromJson(response, BasicResponse.class);

                if (basicResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }


                MyApplication myApplication = (MyApplication) getActivity().getApplicationContext();
                currencyEntityArrayList = basicResponse.getResult().getSetting().getCurrencies();
                mAdapter = new CurrencyAdapter(mContext, currencyEntityArrayList, this);
                mRecyclerView.setAdapter(mAdapter);
                break;
            case AppConstants.APICode.SET_CURRENCY:
//                Utility.toastMessage(mContext, "Notification : " + b);
                SettingResponse settingResponse = new Gson().fromJson(response, SettingResponse
                        .class);

                if (settingResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

//                if (settingResponse.getStatus().equalsIgnoreCase("success")) {
//                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys
//                                    .BASECURRENCY_CODE,
//                            selectedCurrencyEntity.getCode());
//
//                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys
//                                    .BASECURRENCY_SYMBOL,
//                            selectedCurrencyEntity.getSymbol());
//                    double currency = Double.parseDouble(selectedCurrencyEntity.getRate());
//                    MyApplication.preferencePutFloat(AppConstants.SharedPreferenceKeys
//                                    .BASECURRENCY_VALUE,
//                            Float.parseFloat(String.format("%.2f", currency)));
//
//                    /*MyApplication.preferencePutFloat(AppConstants.SharedPreferenceKeys
//                    .BASECURRENCY_VALUE,
//                            Float.parseFloat(String.format("%.2f", 1.00)));*/
//
//                    mAdapter.currencyCode = selectedCurrencyEntity.getCode();
//                    mAdapter = new CurrencyAdapter(mContext, currencyEntityArrayList, this);
//                    mRecyclerView.setAdapter(mAdapter);
//                }


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
    public void onCurrencyCheckedChange(int position) {

        selectedCurrencyEntity = currencyEntityArrayList.get(position);
        /*MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.BASECURRENCY_SYMBOL,
                entity.getSymbol());
        double currency = Double.parseDouble(entity.getRate());
        MyApplication.preferencePutFloat(AppConstants.SharedPreferenceKeys.BASECURRENCY_VALUE,
                Float.parseFloat(String.format("%.2f", currency)));
        mAdapter.currencyCode = entity.getCode();
        mAdapter = new CurrencyAdapter(mContext, currencyEntityArrayList, this);
        mRecyclerView.setAdapter(mAdapter);*/


        SetCurrencyRequest setCurrencyRequest = new SetCurrencyRequest();
        setCurrencyRequest.setCode(selectedCurrencyEntity.getCode());
        setCurrencyRequest.setShoppingcartid(MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.CART_ID, "0"));
        setCurrencyRequest.setUser_id(MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.USER_ID, "0"));

        HTTPWebRequest.SetCurrency(mContext, setCurrencyRequest, AppConstants.APICode
                .SET_CURRENCY, this, getFragmentManager());


    }

}
