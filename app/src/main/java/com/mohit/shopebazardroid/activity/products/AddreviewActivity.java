package com.mohit.shopebazardroid.activity.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.CustomersReviewAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.RatingbarListner;
import com.mohit.shopebazardroid.model.request.AddReviewRequest;
import com.mohit.shopebazardroid.model.request.RatingsRequest;
import com.mohit.shopebazardroid.model.response.AddReviewResponse;
import com.mohit.shopebazardroid.model.response.CustomerReviewResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


public class AddreviewActivity extends BaseActivity implements ApiResponse, View.OnClickListener {

    public static String TAG = AddreviewActivity.class.getSimpleName();
    Context mContext;
    int screenWidth;
    int screenHeight;
    int themeCode;

    CustomersReviewAdapter reviewAdapter;
    ArrayList<CustomerReviewResponse.Result> arrayList_options;
    RecyclerView recyclerview;
    Button btn_submit;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "store_id");
    String customerid = getUserid();

    TextView txt_product_id, txt_rate_product, txt_nickname, txt_summary_review, txt_review;
    EditText edt_nickname, edt_summary_review, edt_review;

    ArrayList<CustomerReviewResponse.Result> arr_ratings;

    String product_id, product_name;

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreview);

        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Add Review");
        getScreenResolution(mContext);
        arr_ratings = new ArrayList<>();
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        product_id = getIntent().getStringExtra("product");
        product_name = getIntent().getStringExtra("name");

        txt_product_id = (TextView) findViewById(R.id.txt_product_id);
        txt_product_id.setTypeface(SplashActivity.opensans_regular);

        txt_product_id.setText("You're reviewing: " + product_name);

        txt_rate_product = (TextView) findViewById(R.id.txt_rate_product);
        txt_rate_product.setTypeface(SplashActivity.opensans_regular);
        txt_nickname = (TextView) findViewById(R.id.txt_nickname);
        txt_nickname.setTypeface(SplashActivity.opensans_regular);
        txt_summary_review = (TextView) findViewById(R.id.txt_summary_review);
        txt_summary_review.setTypeface(SplashActivity.opensans_regular);
        txt_review = (TextView) findViewById(R.id.txt_review);
        txt_review.setTypeface(SplashActivity.opensans_regular);

        String star = "<font color='#ff0000'>*</font>";

        txt_nickname.setText(Html.fromHtml(getResources().getString(R.string.str_nickname) + star));
        txt_summary_review.setText(Html.fromHtml(getResources().getString(R.string.str_summary_of_your_review) + star));
        txt_review.setText(Html.fromHtml(getResources().getString(R.string.str_review) + star));
        txt_rate_product.setText(Html.fromHtml(getResources().getString(R.string.how_do_you_rate_this_product) + star));

        edt_nickname = (EditText) findViewById(R.id.edt_nickname);
        edt_nickname.setTypeface(SplashActivity.opensans_regular);
        edt_summary_review = (EditText) findViewById(R.id.edt_summary_review);
        edt_summary_review.setTypeface(SplashActivity.opensans_regular);
        edt_review = (EditText) findViewById(R.id.edt_review);
        edt_review.setTypeface(SplashActivity.opensans_regular);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        RatingsRequest rating_request = new RatingsRequest();
        rating_request.setStore_id(storeid);

        // network call
        HTTPWebRequest.Ratings(mContext, rating_request, AppConstants.APICode.RATINGS, this, getSupportFragmentManager());

    }

    private boolean checkValidation() {

        StringBuilder strb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < arr_ratings.size(); i++) {
            String ratings = "0";

            if (arr_ratings.get(i).getPosition().equalsIgnoreCase("0")) {
                Utility.toastMessage(mContext, R.string.rating_error);
                Log.d(TAG, "Please enter ratings");
                return false;
            }

            if (count > 0) {
                ratings = String.valueOf(Integer.parseInt(arr_ratings.get(i).getPosition()) + 5 * count);
            } else {
                ratings = arr_ratings.get(i).getPosition();
            }

            if (i == arr_ratings.size() - 1) {
                strb.append(arr_ratings.get(i).getRating_id()).append(":").append(ratings);
            } else {
                strb.append(arr_ratings.get(i).getRating_id()).append(":").append(ratings).append("@");
            }

            count++;
        }

//            Toast.makeText(mContext, strb.toString(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Ratings: " + strb.toString());

       /* if (strb.toString().equalsIgnoreCase("1:0@2:5@3:10")) {
            Utility.toastMessage(mContext, R.string.rating_error);
            Log.d(TAG, "Please enter ratings");
            return false;
        } else*/
        if (edt_nickname.getText().toString().trim().length() == 0) {
            Utility.toastMessage(mContext, R.string.nickname);
            Log.d(TAG, "Please enter nickname");
            return false;
        } else if (edt_summary_review.getText().toString().trim().length() == 0) {
            Utility.toastMessage(mContext, R.string.summary_review);
            Log.d(TAG, "Please enter nickname");
            return false;
        } else if (edt_review.getText().toString().trim().length() == 0) {
            Utility.toastMessage(mContext, R.string.review);
            Log.d(TAG, "Please enter nickname");
            return false;
        } else {

            AddReviewRequest add_review_ = new AddReviewRequest();
            add_review_.setNickname(edt_nickname.getText().toString());
            add_review_.setStore_id(storeid);
            add_review_.setCustomer_id(customerid);
            add_review_.setTitle(edt_summary_review.getText().toString());
            add_review_.setDetail(edt_review.getText().toString());
            add_review_.setProduct_id(product_id);
            add_review_.setOption_id(strb.toString());

            // network call
            HTTPWebRequest.AddReview(mContext, add_review_, AppConstants.APICode.ADD_REVIEW, this, getSupportFragmentManager());

            return true;
        }

    }


    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {


        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {

            case AppConstants.APICode.RATINGS:
                CustomerReviewResponse cust_rev_response = new Gson().fromJson(response, CustomerReviewResponse.class);
                if (cust_rev_response.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                if (cust_rev_response.getStatus().equalsIgnoreCase("success")) {

                    arrayList_options = cust_rev_response.getResult();
                    arr_ratings = cust_rev_response.getResult();
                    reviewAdapter = new CustomersReviewAdapter(mContext, arrayList_options, ratingListener);
                    recyclerview.setAdapter(reviewAdapter);

                } else {
                    Utility.toastMessage(mContext, "No product Found");
                }

                break;

            case AppConstants.APICode.ADD_REVIEW:
                AddReviewResponse add_review_response = new Gson().fromJson(response, AddReviewResponse.class);
                if (add_review_response.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                if (add_review_response.getStatus().equalsIgnoreCase("success")) {

                    Utility.toastMessage(mContext, add_review_response.getResult().getMessage());
                    finish();

                } else {
                    Utility.toastMessage(mContext, add_review_response.getResult().getMessage());
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
            case R.id.btn_submit:

                checkValidation();

                break;
        }

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

    private void getScreenResolution(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        screenWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);

//        return "{" + screenWidth + "," + screenHeight + "}";
    }

    RatingbarListner ratingListener = new RatingbarListner() {
        @Override
        public void onRatingClick(int rating, String name, int position) {

//            Toast.makeText(mContext, position + "." + name + ": " + rating, Toast.LENGTH_SHORT).show();
            arr_ratings.get(position).setPosition(rating + "");

        }
    };

}
