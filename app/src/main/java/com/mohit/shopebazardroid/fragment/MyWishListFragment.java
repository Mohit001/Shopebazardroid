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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.products.ProductDetailActivity;
import com.mohit.shopebazardroid.adapter.MyWishListAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.listener.DeleteWishlistProduct;
import com.mohit.shopebazardroid.model.request.AddRemoveWishListRequest;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.model.response.ProductResponse;
import com.mohit.shopebazardroid.model.response.RemoveCartResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


public class MyWishListFragment extends BaseFragment implements ApiResponse, DeleteWishlistProduct, ConfirmDialogListner {
    public static String TAG = MyWishListFragment.class.getSimpleName();
    Context mContext;
    RecyclerView mRecyclerView;

    String email = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.EMAIL, "");
    String userid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "0");

    MyWishListAdapter adapter;
    ArrayList<ProductEntity> arrayList;
    int position;
    String productId = "";

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
        mContext = getActivity();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);

    }

    @Override
    public void onResume() {
        super.onResume();

        if(isUserLogin()){
            HTTPWebRequest.MyWishList(mContext, getUserid(), AppConstants.APICode.MY_WISHLIST, this,
                    getFragmentManager());
        }

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }
        if (response != null) {
            Gson gson = new GsonBuilder().serializeNulls().create();
            switch (apiCode) {

                case AppConstants.APICode.MY_WISHLIST:

                    try {

                        ProductResponse response1 = new Gson().fromJson(response,
                                ProductResponse.class);


                        if (response1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                            Utility.toastMessage(mContext, R.string.subscription_over);
                            MyApplication.clearPreference();
                            startActivity(new Intent(mContext, LoginActivity.class));
                            getActivity().finish();
                            return;
                        }
                        if (response1.getStatus().equalsIgnoreCase("success")) {
                            arrayList = response1.getResult().getProductlist();

                            adapter = new MyWishListAdapter(mContext, arrayList, this);
                            mRecyclerView.setAdapter(adapter);

                            if (arrayList.isEmpty()) {
                                Utility.toastMessage(mContext, "No product found in your Wish List please add product to your wish List!");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case AppConstants.APICode.REMOVE_FROM_WISHLIST:
                    RemoveCartResponse removeCartResponse = gson.fromJson(response, RemoveCartResponse.class);

                    if (removeCartResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(mContext, LoginActivity.class));
                        getActivity().finish();
                        return;
                    }

                    if (removeCartResponse.getStatus().equalsIgnoreCase("success")) {
                        arrayList.remove(position);
                        adapter.notifyDataSetChanged();
                    } else {
                        Utility.toastMessage(mContext, removeCartResponse.getResult().getMessage());
                    }
                    break;
            }
        }
    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(int apiCode) {

    }

    @Override
    public void DeleteProduct(int pos, String product_id) {
        position = pos;
        productId = product_id;
        Utility.showConfirmDialog(mContext, this, "Delete Product", "Are you sure you want to " +
                "delete product from cart?", "Yes", "Cancel", getFragmentManager());
    }

    @Override
    public void ItemClick(int pos, String product_id) {

        int qty = Integer.parseInt(arrayList.get(pos).getQty().split("\\.", 2)[0]);

        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra(AppConstants.RequestDataKey.PRODUCT, arrayList.get(position));
        startActivity(intent);
    }

    @Override
    public void onPositiveButtonClick() {
        Utility.dismissConfirmDialog();

        AddRemoveWishListRequest request = new AddRemoveWishListRequest();
        request.setProduct_id(productId);
        request.setCustomer_id(userid);

        HTTPWebRequest.RemoveFromWishList(mContext, request, AppConstants
                .APICode.REMOVE_FROM_WISHLIST, this, getFragmentManager());
    }

    @Override
    public void onNegativeButtonClick() {
        Utility.dismissConfirmDialog();
    }


}
