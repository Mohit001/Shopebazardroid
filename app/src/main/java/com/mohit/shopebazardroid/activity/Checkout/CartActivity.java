package com.mohit.shopebazardroid.activity.Checkout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.CartAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.CartListner;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.model.request.CartItemsRequest;
import com.mohit.shopebazardroid.model.request.CartJsonRequest;
import com.mohit.shopebazardroid.model.request.CartProductOptionRequest;
import com.mohit.shopebazardroid.model.request.CartProductRequest;
import com.mohit.shopebazardroid.model.response.CartItems;
import com.mohit.shopebazardroid.model.response.CartResponse;
import com.mohit.shopebazardroid.model.response.RemoveCartResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


/**
 * Created by msp on 22/7/16.
 */
public class CartActivity extends BaseActivity implements View.OnClickListener, CartListner,
        ApiResponse, ConfirmDialogListner {

    Context mContext;
    ArrayList<CartItems> arrayList;
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    CartAdapter mAdapter;

    RelativeLayout cart_gross_total_rl;
    AppCompatTextView grossTotalTextView;
    AppCompatButton checkoutTextView;
    int position;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    TextView gross_total_lbl;


    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    private int isFirstTime = 0; //0=firstTime, 1=checkoutlogin, 2=backfromBilling address

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

        HTTPWebRequest.GetCartItems(mContext, AppConstants.APICode.GETCARTITEMS, this,
                getSupportFragmentManager());

    }

    private void calculateGrosstotal() {
        // count gross total
        CartItems cartEntity;
        double grosstotal = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            cartEntity = arrayList.get(i);
            grosstotal = grosstotal + cartEntity.getRow_total_incl_tax();
        }

        float tempGrossTotal = (float) (grosstotal /** baseCurrencyValue*/);
        grossTotalTextView.setText(baseCurrencyCode + String.format("%.2f", tempGrossTotal));

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

        if(isFirstTime == 0 || isFirstTime == 2){
            HTTPWebRequest.GetCartItems(mContext, AppConstants.APICode.GETCARTITEMS, this,
                    getSupportFragmentManager());
            isFirstTime = 1;
        } else if (isFirstTime == 1){
            if(isUserLogin()){
                startActivity(new Intent(mContext, ActivityBillingAddress.class));
                isFirstTime =2;
            }
        }

    }


    private void callUpdateCart(){
        Gson gson = new Gson();
        CartJsonRequest cartJsonRequest = new CartJsonRequest();
        CartItemsRequest cartItemsRequest = new CartItemsRequest();
        cartItemsRequest.setTotalitems("" + arrayList.size());
        ArrayList<CartProductRequest> cartitems = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            CartProductRequest cartProductRequest = new CartProductRequest();
            cartProductRequest.setProduct_id(arrayList.get(i).getProduct_id());
            cartProductRequest.setType(arrayList.get(i).getProduct_type());
            cartProductRequest.setQty("" + arrayList.get(i).getQty());
            ArrayList<CartProductOptionRequest> custom_option_id = new ArrayList<>();
            for (int j = 0; j < arrayList.get(i).getCustom_option_id().size(); j++) {
                CartProductOptionRequest cartProductOptionRequest = new
                        CartProductOptionRequest();
                cartProductOptionRequest.setOption_id(arrayList.get(i)
                        .getCustom_option_id().get(j).getOption_id());
                cartProductOptionRequest.setValue_id(arrayList.get(i).getCustom_option_id
                        ().get(j).getValue_id());
                custom_option_id.add(cartProductOptionRequest);
            }
            for (int j = 0; j < arrayList.get(i).getSupper_attribute_id().size(); j++) {
                CartProductOptionRequest cartProductOptionRequest = new
                        CartProductOptionRequest();
                cartProductOptionRequest.setOption_id(arrayList.get(i)
                        .getSupper_attribute_id().get(j).getOption_id());
                cartProductOptionRequest.setValue_id(arrayList.get(i)
                        .getSupper_attribute_id().get(j).getValue_id());
                custom_option_id.add(cartProductOptionRequest);
            }
            cartProductRequest.setCustom_option_id(custom_option_id);
            cartitems.add(cartProductRequest);
        }
        cartItemsRequest.setCartitems(cartitems);
        cartJsonRequest.setJsonrequest(cartItemsRequest);
        String jsonrequest = gson.toJson(cartJsonRequest);
        Log.d("jsonrequest", "jsonrequest==" + jsonrequest);
        HTTPWebRequest.UpdateProductFromCart(mContext, jsonrequest, AppConstants.APICode
                .UPDATE_CART, this, getSupportFragmentManager());
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
                    callUpdateCart();
                }
                break;
        }
    }

    @Override
    public void onQuentityDecreaseClick(int index) {
        CartItems entity = arrayList.get(index);
        if (entity.getQty() > 1) {
            int qty = entity.getQty();
            qty -= 1;
            entity.setQty(qty);
            entity.setRow_total_incl_tax(entity.getQty() * entity.getPrice_incl_tax());
//            arrayList.remove(index);
//            arrayList.add(index, entity);
            arrayList.set(index, entity);
            calculateGrosstotal();
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onQuentityIncreaseClick(int index) {

        CartItems entity = arrayList.get(index);
        int qty = entity.getQty();
        qty += 1;
        entity.setQty(qty);
        entity.setRow_total_incl_tax(entity.getQty() * entity.getPrice_incl_tax());
//        arrayList.remove(index);
//        arrayList.add(index, entity);
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
        if (response != null) {
            Gson gson = new GsonBuilder().serializeNulls().create();
            switch (apiCode) {
                case AppConstants.APICode.GETCARTITEMS:

                    CartResponse cartResponse = gson.fromJson(response, CartResponse.class);

                    if (cartResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(this, LoginActivity.class));
                        this.finish();
                        return;
                    }

                    if (cartResponse.getStatus().equalsIgnoreCase("fail")) {
                        if (arrayList != null && arrayList.size() > 0) {
                            arrayList.clear();
                            calculateGrosstotal();
                            mAdapter.notifyDataSetChanged();
                            return;
                        }
                    }
                    if (cartResponse.getResult().getCart() != null) {
                        arrayList = cartResponse.getResult().getCart().getItems();

                        if (arrayList.size() == 0) {
                            cart_gross_total_rl.setVisibility(View.GONE);
                            Toast.makeText(mContext, "Cart is empty", Toast.LENGTH_SHORT).show();
                        }

                        calculateGrosstotal();

                        // remove item with parentid
                        CartItems cartItems;
                        for (int i = 0; i < arrayList.size(); i++) {
                            cartItems = arrayList.get(i);
                            if (!TextUtils.isEmpty(cartItems.getParent_item_id()))
                                arrayList.remove(i);
                        }
                        mAdapter = new CartAdapter(this, arrayList, this);
                        recyclerView.setAdapter(mAdapter);
                    }
                    break;
                case AppConstants.APICode.REMOVE_CART:
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
                        /*int count= Integer.parseInt(cartItemCount);
                        count = count -1;*/

                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, String.valueOf(cartItemCount));
//                        cartItemBadge.setText(String.valueOf(Integer.parseInt(cartItemBadge.getText().toString())-1));

                        arrayList.remove(position);
                        calculateGrosstotal();
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
                case AppConstants.APICode.UPDATE_CART:
                    cartResponse = gson.fromJson(response, CartResponse.class);

                    if (cartResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(this, LoginActivity.class));
                        this.finish();
                        return;
                    }
                    if (cartResponse.getStatus().equalsIgnoreCase("success")) {
                        if(isCallFromBackPress){
                            this.finish();
                        } else{
                            if(isUserLogin()){
                                isFirstTime = 2;
                                startActivity(new Intent(mContext, ActivityBillingAddress.class));
                            } else {
                                Intent intent = new Intent(this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }



                    } else{
//                        Toast.makeText(mContext, cartResponse.getResult().getMessage(), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setTitle("Quantity Exceeds");
                        builder.setMessage(cartResponse.getResult().getMessage());

                        String positiveText = getString(android.R.string.ok);
                        builder.setPositiveButton(positiveText,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // positive button logic
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        // display dialog
                        dialog.show();
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
    public void onPositiveButtonClick() {

        Utility.dismissConfirmDialog();
        HTTPWebRequest.RemoveProductFromCart(mContext, arrayList.get(position), AppConstants
                .APICode.REMOVE_CART, this, getSupportFragmentManager());
    }

    @Override
    public void onNegativeButtonClick() {

        Utility.dismissConfirmDialog();
    }

}