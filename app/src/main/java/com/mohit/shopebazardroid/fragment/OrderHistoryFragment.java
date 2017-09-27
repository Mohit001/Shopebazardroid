package com.mohit.shopebazardroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.activity.history.OrderHistoryDetailActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.adapter.OrderHistoryAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.ReorderListner;
import com.mohit.shopebazardroid.model.request.OrderHistoryRequest;
import com.mohit.shopebazardroid.model.request.ReorderRequest;
import com.mohit.shopebazardroid.model.response.AddCartResponse;
import com.mohit.shopebazardroid.model.response.CurrencyEntity;
import com.mohit.shopebazardroid.model.response.HistoryEntity;
import com.mohit.shopebazardroid.model.response.HistoryResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;

/**
 * Created by msp on 27/7/16.
 */
public class OrderHistoryFragment extends BaseFragment implements ApiResponse, ReorderListner {

    public static String TAG = OrderHistoryFragment.class.getSimpleName();
    Context mContext;

    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    OrderHistoryAdapter mAdapter;

    ArrayList<HistoryEntity> arrayList;
    ArrayList<CurrencyEntity> currencyArrayList;
    NavigationDrawerActivity activity;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycleview, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getContext();
        activity = (NavigationDrawerActivity) getActivity();
        currencyArrayList = activity.getCurrencyArrayList();

        layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);

        /*mRecyclerView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                Utility.toastMessage(mContext, "Item click at : " + position);
                        String incrementid = arrayList.get(position).getIncrement_id();
                        String status = arrayList.get(position).getStatus();
                        String coupanCode = arrayList.get(position).getCoupon_code();

                        Intent intent = new Intent(mContext, OrderHistoryDetailActivity.class);

                        intent.putExtra(AppConstants.RequestDataKey.INCREMENTID, incrementid);
                        intent.putExtra(AppConstants.RequestDataKey.STATUS, status);
                        intent.putExtra("isFrom", "order");
                        intent.putExtra(AppConstants.RequestDataKey.COUPON_CODE, TextUtils.isEmpty(coupanCode) ? "" : coupanCode);
                        intent.putExtra(AppConstants.RequestDataKey.CURRENCY_SYMBOL, arrayList.get(position).getCurrencySymbol());

                        startActivity(intent);
                    }
                }));*/



    }


    @Override
    public void onResume() {
        super.onResume();
//        setupOrderHistory();

        if(isUserLogin()){
            OrderHistoryRequest request = new OrderHistoryRequest();
            request.setCustomer_id(getUserid());
            request.setStore_id(getStoreID());

            HTTPWebRequest.OrderHistory(mContext, request, AppConstants.APICode.HISTORY, this,
                    getFragmentManager());
        }
    }


    /*private void setupOrderHistory() {

        arrayList = new ArrayList<>();

        OrderHistory history1 = new OrderHistory();
        history1.setOrderno("234234");
        history1.setOrderdate("12 Dec, 2015");
        history1.setAmount("$25000");
        history1.setStatus("delivered");
        arrayList.add(history1);

        OrderHistory history2 = new OrderHistory();
        history2.setOrderno("234534234");
        history2.setOrderdate("23 Feb, 2016");
        history2.setAmount("$450");
        history2.setStatus("cancel");
        arrayList.add(history2);

        OrderHistory history3 = new OrderHistory();
        history3.setOrderno("7928374");
        history3.setOrderdate("1 Jun, 2016");
        history3.setAmount("$1025");
        history3.setStatus("delivered");
        arrayList.add(history3);

        OrderHistory history4 = new OrderHistory();
        history4.setOrderno("949494");
        history4.setOrderdate("30 Jun, 2015");
        history4.setAmount("$6584");
        history4.setStatus("Pending");
        arrayList.add(history4);

        mAdapter = new OrderHistoryAdapter(mContext, arrayList);
        mRecyclerView.setAdapter(mAdapter);
    }*/

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.REORDER:
                AddCartResponse addCartResponse = new Gson().fromJson(response, AddCartResponse.class);
                Toast.makeText(mContext, addCartResponse.getResult().getMessage(), Toast.LENGTH_SHORT).show();
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID, addCartResponse.getResult().getData().getShoppingcartid());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, addCartResponse.getResult().getData().getItems_count());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, addCartResponse.getResult().getData().getItems_qty());
                ((NavigationDrawerActivity) getActivity()).updateCartCount();
                break;
            case AppConstants.APICode.HISTORY:
                try {
                    HistoryResponse historyResponse = new Gson().fromJson(response,
                            HistoryResponse.class);

                    if (historyResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(mContext, LoginActivity.class));
                        getActivity().finish();
                        return;
                    }

                    if (historyResponse.getStatus().equalsIgnoreCase("success")) {
                        if (historyResponse.getResult().getOrderlist().size() == 0) {
                            Utility.toastMessage(mContext, "No order history found");
                            return;
                        }
                        arrayList = historyResponse.getResult().getOrderlist();
                        HistoryEntity entity;
                        for (int i = 0; i < arrayList.size(); i++) {
                            entity = arrayList.get(i);
                            for (CurrencyEntity currencyEntity : currencyArrayList) {
                                if (currencyEntity.getCode().equalsIgnoreCase(entity
                                        .getOrder_currency_code())) {
                                    entity.setCurrencySymbol(currencyEntity.getSymbol());
                                    arrayList.set(i, entity);
                                    break;
                                }
                            }
                        }


                        mAdapter = new OrderHistoryAdapter(mContext, arrayList, this);
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        Utility.toastMessage(mContext, "No order history found");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
    public void orReorderClick(String orderid) {
        ReorderRequest request = new ReorderRequest();
        request.setCustomer_id(getUserid());
        request.setStore_id(getStoreID());
        request.setOrderid(orderid);

        HTTPWebRequest.Reorder(mContext, request, AppConstants.APICode.REORDER, this,getFragmentManager());
    }

    @Override
    public void onDetailsClick(int position) {

        String incrementid = arrayList.get(position).getIncrement_id();
        String status = arrayList.get(position).getStatus();
        String coupanCode = arrayList.get(position).getCoupon_code();

        Intent intent = new Intent(mContext, OrderHistoryDetailActivity.class);

        intent.putExtra(AppConstants.RequestDataKey.INCREMENTID, incrementid);
        intent.putExtra(AppConstants.RequestDataKey.STATUS, status);
        intent.putExtra("isFrom", "order");
        intent.putExtra(AppConstants.RequestDataKey.COUPON_CODE, TextUtils.isEmpty(coupanCode) ? "" : coupanCode);
        intent.putExtra(AppConstants.RequestDataKey.CURRENCY_SYMBOL, arrayList.get(position).getCurrencySymbol());

        startActivity(intent);
    }
}
