package com.mohit.shopebazardroid.activity.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.adapter.ReviewListAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.CustomerReviewListRequest;
import com.mohit.shopebazardroid.model.response.CustomerReviewList;
import com.mohit.shopebazardroid.model.response.ReviewList;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;

/**
 * Created by msp on 1/6/17.
 */

public class CustomerReviewListingActivity extends BaseActivity implements ApiResponse {

    Context mContext;
    int screenWidth;
    int screenHeight;
    int themeCode;
    Menu menu;

    RecyclerView recyclerview;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "store_id");

    String product_id;
    ArrayList<ReviewList> arr_review_list;
    ReviewListAdapter reviewListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cust_rev_listing);

        mContext = this;

        getSupportActionBar().setTitle("Reviews");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getScreenResolution(mContext);

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

//        productEntity = (ProductEntity) getIntent().getSerializableExtra(ProductEntity.KEY_OBJECT);

        product_id = getIntent().getStringExtra("product");


        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));

        CustomerReviewListRequest cust_review_list = new CustomerReviewListRequest();
        cust_review_list.setStore_id(storeid);
        cust_review_list.setProduct_id(product_id);
        cust_review_list.setPage(String.valueOf(1));
        cust_review_list.setPagesize(String.valueOf(10000));

        // network call
        HTTPWebRequest.ReviewListShowAll(mContext, cust_review_list, AppConstants.APICode.REVIEW_LIST, this, getSupportFragmentManager());


    }

    private void getScreenResolution(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        screenWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);

//        return "{" + screenWidth + "," + screenHeight + "}";
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {

            case AppConstants.APICode.REVIEW_LIST:
                CustomerReviewList review_response = new Gson().fromJson(response, CustomerReviewList.class);
                if (review_response.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                if (review_response.getStatus().equalsIgnoreCase("success")) {
//                    related_product_arrayList = review_response.getResult().getProductlist();
                    arr_review_list = review_response.getResult().getReviewlist();

                    reviewListAdapter = new ReviewListAdapter(mContext, arr_review_list);
                    recyclerview.setAdapter(reviewListAdapter);

                } else {
//                    Utility.toastMessage(mContext, "No product Found");

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_cart);
        item.setVisible(false);

        MenuItem item2 = menu.findItem(R.id.action_search);
        item2.setVisible(false);

        return true;
    }


}
