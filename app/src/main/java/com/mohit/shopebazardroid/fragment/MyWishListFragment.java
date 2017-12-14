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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.products.ProductDetailActivity;
import com.mohit.shopebazardroid.adapter.MyWishListAdapter;
import com.mohit.shopebazardroid.enums.ApiResponseStatus;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.listener.DeleteWishlistProduct;
import com.mohit.shopebazardroid.model.request.AddRemoveWishListRequest;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.model.response.ProductResponse;
import com.mohit.shopebazardroid.model.response.RemoveCartResponse;
import com.mohit.shopebazardroid.models.Product;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MyWishListFragment extends BaseFragment implements ApiResponse, DeleteWishlistProduct, ConfirmDialogListner {
    public static String TAG = MyWishListFragment.class.getSimpleName();
    private Context mContext;
    private RecyclerView mRecyclerView;
    private String userid = getUserid();
    private MyWishListAdapter adapter;
    private List<Product> arrayList;
    private int position;
    private String productId = "";
    private String wishlist_id="";
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
        getUserWishlist();
    }

    private void getUserWishlist(){
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

                        Type wishlistType = new TypeToken<BaseResponse<List<Product>>>(){}.getType();
                        BaseResponse<List<Product>> baseResponse = gson.fromJson(response, wishlistType);

                        Toast.makeText(mContext, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        if (baseResponse.getStatus() == ApiResponseStatus.WISHLIST_GET_SUCCESS.getStatus_code()) {
                            arrayList = baseResponse.getInfo();

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

                    Type removeWishlistType = new TypeToken<BaseResponse<Integer>>(){}.getType();
                    BaseResponse<Integer> removeWishlistResponse = gson.fromJson(response, removeWishlistType);
                    Toast.makeText(mContext, removeWishlistResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    if(removeWishlistResponse.getStatus() == ApiResponseStatus.WISHLIST_REMOVE_SUCCESS.getStatus_code()){
                        getUserWishlist();
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
    public void DeleteProduct(String wishlist_id) {
        this.wishlist_id = wishlist_id;
        Utility.showConfirmDialog(mContext, this,
                "Remove Product",
                "Are you sure you want to remove product from wishlist?",
                "Yes", "Cancel", getFragmentManager());
    }

    @Override
    public void ItemClick(int pos, String product_id) {

        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra(AppConstants.RequestDataKey.PRODUCT_ID, product_id);
        startActivity(intent);
    }

    @Override
    public void onPositiveButtonClick() {
        Utility.dismissConfirmDialog();
        if(!TextUtils.isEmpty(wishlist_id) && !wishlist_id.equalsIgnoreCase("0")){

            HTTPWebRequest.RemoveFromWishList(mContext, wishlist_id, AppConstants
                    .APICode.REMOVE_FROM_WISHLIST, this, getFragmentManager());
        } else {
            Toast.makeText(mContext, "wishlist id not set for delation", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNegativeButtonClick() {
        Utility.dismissConfirmDialog();
    }


}
