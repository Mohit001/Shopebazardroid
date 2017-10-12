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
import com.mohit.shopebazardroid.activity.history.OrderHistoryDetailActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.adapter.NotificationListAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.model.request.NotificationListRequest;
import com.mohit.shopebazardroid.model.response.NotificationListResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;



public class NotificationFragment extends BaseFragment implements ApiResponse, View.OnClickListener {
    public static String TAG = NotificationFragment.class.getSimpleName();
    Context mContext;
    RecyclerView mRecyclerView;

    ArrayList<NotificationListResponse.List> arrList;
    NotificationListAdapter adapter;

    String increment_id;

    String userid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "0");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        mRecyclerView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new RecyclerItemclicklistner.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                increment_id = arrList.get(position).getIncrementorder_id();

                if (increment_id.equalsIgnoreCase("0")) {

                    Toast.makeText(mContext, "Push Notification from admin", Toast.LENGTH_SHORT).show();

                } else {

                    Intent intent = new Intent(mContext, OrderHistoryDetailActivity.class);
                    intent.putExtra(AppConstants.RequestDataKey.INVOICE_ID, increment_id);
                    intent.putExtra("isFrom", "order");
                    startActivity(intent);
                }


            }
        }));


        NotificationListRequest notif_request = new NotificationListRequest();
        notif_request.setCustomer_id(userid);

        HTTPWebRequest.NotificationList(mContext, notif_request, AppConstants.APICode.NOTIFICATION_LIST, this,
                getFragmentManager());

//        HTTPWebRequest.Basic(mContext, AppConstants.APICode.BASIC, this, getFragmentManager());
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.NOTIFICATION_LIST:

                try {

                    NotificationListResponse response1 = new Gson().fromJson(response,
                            NotificationListResponse.class);


                    if (response1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(mContext, LoginActivity.class));
                        getActivity().finish();
                        return;
                    }
                    if (response1.getStatus().equalsIgnoreCase("success")) {
                        if (response1.getResult().getList().size() == 0) {
                            Utility.toastMessage(mContext, "No notification found");
                            return;
                        }
                        arrList = response1.getResult().getList();

                        adapter = new NotificationListAdapter(mContext, arrList);
                        mRecyclerView.setAdapter(adapter);


                    } else {
                        Utility.toastMessage(mContext, "No notification found");
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
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
