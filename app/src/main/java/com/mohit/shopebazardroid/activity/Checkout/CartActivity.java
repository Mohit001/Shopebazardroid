package com.mohit.shopebazardroid.activity.Checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.CartAdapter;
import com.mohit.shopebazardroid.enums.ApiResponseStatus;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.CartListner;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.models.UserCart;
import com.mohit.shopebazardroid.models.UserCartProduct;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by msp on 22/7/16.
 */
public class CartActivity extends BaseActivity implements View.OnClickListener, CartListner,
        ApiResponse, ConfirmDialogListner {

    Context mContext;
    private UserCart userCart;
    private List<UserCartProduct> arrayList;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CartAdapter mAdapter;

    private RelativeLayout cart_gross_total_rl;
    private AppCompatTextView grossTotalTextView;
    private AppCompatButton checkoutTextView;
    private int position;
    private String baseCurrencyCode = "";
    private float baseCurrencyValue;

    private TextView gross_total_lbl;
    private String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");

    private boolean isCallFromBackPress = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Cart");
        mContext = this;
        baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_CODE, getString(R.string.rupee_sign));
//        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
// .BASECURRENCY_VALUE, 0);
//        baseCurrencyValue = 1;
        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_RATE, 1);


        checkoutTextView = (AppCompatButton) findViewById(R.id.proceed_checkout_btn);
        checkoutTextView.setTypeface(SplashActivity.opensans_semi_bold);
        checkoutTextView.setOnClickListener(this);

        grossTotalTextView = (AppCompatTextView) findViewById(R.id.gross_total_content);
        grossTotalTextView.setTypeface(SplashActivity.opensans_semi_bold);

        gross_total_lbl = (TextView) findViewById(R.id.gross_total_lbl);
        gross_total_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        cart_gross_total_rl = (RelativeLayout) findViewById(R.id.cart_gross_total_rl);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.white));
        linearLayoutManager = new LinearLayoutManager(mContext);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (ishideprice.equalsIgnoreCase("0")) {
            cart_gross_total_rl.setVisibility(View.VISIBLE);
        } else {
            cart_gross_total_rl.setVisibility(View.GONE);
        }

        gross_total_lbl.setOnClickListener(this);

        /*HTTPWebRequest.GetCartItems(mContext, getUserid(),AppConstants.APICode.GETCARTITEMS,
                this,getSupportFragmentManager());*/

    }

    private void calculateGrosstotal() {
        // count gross total
        UserCartProduct userCartProduct;
        double grosstotal = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            userCartProduct = arrayList.get(i);
            grosstotal = grosstotal + Double.parseDouble(userCartProduct.getSubtotal());
        }

        grossTotalTextView.setText(baseCurrencyCode + String.format("%.2f", grosstotal));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                onBackPressed();
                return true;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        isCallFromBackPress = true;
        if(arrayList != null && arrayList.size() > 0){
            callUpdateCart();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String cart_id = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0");
        HTTPWebRequest.GetCartItems(mContext, cart_id, AppConstants.APICode.GETCARTITEMS, this,
                getSupportFragmentManager());
    }


    private void callUpdateCart(){
        Gson gson = new GsonBuilder().serializeNulls().create();
        userCart.setUserCartProduct(arrayList);
        String jsonRequest = gson.toJson(userCart);
        HTTPWebRequest.UpdateCart(mContext, jsonRequest, AppConstants.APICode.UPDATE_CART, this, getSupportFragmentManager());
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.proceed_checkout_btn:

                if (arrayList.size() == 0) {
                    Utility.toastMessage(mContext, "Please add item to cart");
                    return;
                } else {
                    isCallFromBackPress = false;
                    if(TextUtils.isEmpty(getUserid()) || getUserid().equalsIgnoreCase("0")){
                        startActivity(new Intent(this, LoginActivity.class));
                    }else {
                        userCart.setUser_id(Integer.parseInt(getUserid()));
                        callUpdateCart();
                    }
                }
                break;
        }
    }

    @Override
    public void onQuentityDecreaseClick(int index) {
        UserCartProduct entity = arrayList.get(index);
        if (entity.getProduct_qty() > 1) {
            int qty = entity.getProduct_qty();
            qty -= 1;
            entity.setProduct_qty(qty);
            Double subtotal = (entity.getProduct_qty() * Double.parseDouble(entity.getProduct_price()))
                    +(entity.getProduct_qty() * entity.getShipping_charge());

            entity.setSubtotal(String.valueOf(subtotal));
            arrayList.set(index, entity);
            calculateGrosstotal();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onQuentityIncreaseClick(int index) {

        UserCartProduct entity = arrayList.get(index);
        int qty = entity.getProduct_qty();
        qty += 1;
        entity.setProduct_qty(qty);
        Double subtotal = (entity.getProduct_qty() * Double.parseDouble(entity.getProduct_price()))
                +(entity.getProduct_qty() * entity.getShipping_charge());

        entity.setSubtotal(String.valueOf(subtotal));
        arrayList.set(index, entity);
        calculateGrosstotal();
        mAdapter.notifyDataSetChanged();


    }

    @Override
    public void onProductDeleteClick(int index) {
        position = index;
        Utility.showConfirmDialog(mContext, this, "Delete Product", "Are you sure you want to " +
                "delete product from cart?", "Yes", "Cancel", getSupportFragmentManager());

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if(TextUtils.isEmpty(response)){
            Toast.makeText(mContext, R.string.host_not_reachable, Toast.LENGTH_SHORT).show();
            return;
        }


        Gson gson = new GsonBuilder().serializeNulls().create();
        switch (apiCode) {
            case AppConstants.APICode.REMOVE_CART:
            case AppConstants.APICode.GETCARTITEMS:

                Type getCartItemsType = new TypeToken<BaseResponse<UserCart>>(){}.getType();
                BaseResponse<UserCart> baseResponse = gson.fromJson(response, getCartItemsType);
                userCart = baseResponse.getInfo();
                if(arrayList == null) {
                    arrayList = new ArrayList<>();
                } else {
                    arrayList.clear();
                }
                arrayList.addAll(userCart.getUserCartProduct());

                if (arrayList.size() > 0) {
                    calculateGrosstotal();
                    mAdapter = new CartAdapter(this, arrayList, this);
                    recyclerView.setAdapter(mAdapter);
                } else {
                    if(mAdapter != null)
                        mAdapter.notifyDataSetChanged();

                    cart_gross_total_rl.setVisibility(View.GONE);
                    Toast.makeText(mContext, "Cart is empty", Toast.LENGTH_SHORT).show();
                }
                break;
            /*case AppConstants.APICode.REMOVE_CART:
                RemoveCartResponse removeCartResponse = gson.fromJson(response, RemoveCartResponse.class);

                if (removeCartResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }


                if (removeCartResponse.getStatus().equalsIgnoreCase("success")) {

                    MyApplication.preferencePutString(AppConstants
                            .SharedPreferenceKeys.CART_TOTAL_ITEMS, "" + removeCartResponse.getResult().getData().getCart().getItems_count());
                    Utility.toastMessage(mContext, removeCartResponse.getResult().getMessage());

                    if (removeCartResponse.getResult().getData().getCart().getItems().size() == 0) {
                        cart_gross_total_rl.setVisibility(View.GONE);
//                            Toast.makeText(mContext, "Cart is empty", Toast.LENGTH_SHORT).show();
                    }

                    String cartItemCount = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0");
                    *//*int count= Integer.parseInt(cartItemCount);
                    count = count -1;*//*

                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, String.valueOf(cartItemCount));
//                        cartItemBadge.setText(String.valueOf(Integer.parseInt(cartItemBadge.getText().toString())-1));

                    arrayList.remove(position);
                    calculateGrosstotal();
                    mAdapter.notifyDataSetChanged();
                }
                break;*/
            case AppConstants.APICode.UPDATE_CART:

                Gson updateCartGson = new GsonBuilder().serializeNulls().create();
                Type updateCartType = new TypeToken<BaseResponse<UserCart>>(){}.getType();
                BaseResponse<UserCart> updateCartResponse = updateCartGson.fromJson(response, updateCartType);


            if (updateCartResponse.getStatus() == ApiResponseStatus.CART_QUENTITY_EXCEED.getStatus_code()) {
                Utility.showConfirmDialog(this, new ConfirmDialogListner() {
                            @Override
                            public void onPositiveButtonClick() {
                                //call get cart item
                                HTTPWebRequest.GetCartItems(mContext, getUserid(),
                                        AppConstants.APICode.GETCARTITEMS,
                                        CartActivity.this,getSupportFragmentManager());
                            }

                            @Override
                            public void onNegativeButtonClick() {

                            }
                        }, "Cart Update Fail",
                        updateCartResponse.getMessage(),
                        getString(R.string.ok),
                        null,
                        getSupportFragmentManager());


            } else if(updateCartResponse.getStatus() == ApiResponseStatus.CART_PRODUCT_UPDATE_FAIL.getStatus_code()){
                Toast.makeText(mContext, updateCartResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }else{
                if(isCallFromBackPress){
                    this.finish();
                } else{
                    startActivity(new Intent(this, ActivityShippingAddress.class));
                    this.finish();
                }
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
    public void onPositiveButtonClick() {

        Utility.dismissConfirmDialog();
        UserCartProduct userCartProduct = arrayList.get(position);
        HTTPWebRequest.RemoveProductFromCart(mContext, userCart.getCart_id(),
                userCartProduct.getProduct_id(), AppConstants.APICode.REMOVE_CART,
                this, getSupportFragmentManager());
    }

    @Override
    public void onNegativeButtonClick() {

        Utility.dismissConfirmDialog();
    }

}