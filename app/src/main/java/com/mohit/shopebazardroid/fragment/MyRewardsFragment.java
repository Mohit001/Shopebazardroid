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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.MyRewardsAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.response.RewardHistoryResponse;
import com.mohit.shopebazardroid.model.response.RewardsResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


public class MyRewardsFragment extends BaseFragment implements ApiResponse, View.OnClickListener {
    public static String TAG = MyRewardsFragment.class.getSimpleName();
    Context mContext;
    RecyclerView mRecyclerView;

    TextView txt_lbl_reward_point_info, txt_lbl_reward_points, txt_points;

    LinearLayout ll_labels;

    MyRewardsAdapter adapter;
    ArrayList<RewardHistoryResponse.Transactions> arrRewardHistory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_reward_points, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        ll_labels = (LinearLayout) view.findViewById(R.id.ll_labels);

        txt_lbl_reward_point_info = (TextView) view.findViewById(R.id.txt_lbl_reward_point_info);
        txt_lbl_reward_point_info.setTypeface(SplashActivity.opensans_regular);

        txt_lbl_reward_points = (TextView) view.findViewById(R.id.txt_lbl_reward_points);
        txt_lbl_reward_points.setTypeface(SplashActivity.opensans_regular);

        txt_points = (TextView) view.findViewById(R.id.txt_points);
        txt_points.setTypeface(SplashActivity.opensans_bold);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);


    }

    @Override
    public void onResume() {
        super.onResume();

        if(isUserLogin()){
            String email = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.EMAIL, "");
            HTTPWebRequest.TotalRewardPoints(mContext, email, AppConstants.APICode.TOTAL_REWARD_POINTS, this,
                    getFragmentManager());
        }
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.TOTAL_REWARD_POINTS:

                try {

                    RewardsResponse response1 = new Gson().fromJson(response,
                            RewardsResponse.class);


                    if (response1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(mContext, LoginActivity.class));
                        getActivity().finish();
                        return;
                    }
//                    if (response1.getStatus().equalsIgnoreCase("success")) {

                    if (response1.getResult().getRewardpoints().equalsIgnoreCase("0")) {
                        txt_points.setText(response1.getResult().getRewardpoints() + " points");
                        ll_labels.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.GONE);
                    } else {
                        txt_points.setText(response1.getResult().getRewardpoints() + " points");
                        ll_labels.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        HTTPWebRequest.RewardHistory(mContext, getUserid(), AppConstants.APICode.REWARD_HISTORY, this,
                                getFragmentManager());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case AppConstants.APICode.REWARD_HISTORY:

                try {

                    RewardHistoryResponse response1 = new Gson().fromJson(response,
                            RewardHistoryResponse.class);


                    if (response1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(mContext, LoginActivity.class));
                        getActivity().finish();
                        return;
                    }
                    if (response1.getStatus().equalsIgnoreCase("success")) {
                        arrRewardHistory = response1.getResult().getTransactions();

                        adapter = new MyRewardsAdapter(mContext, arrRewardHistory);
                        mRecyclerView.setAdapter(adapter);
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
