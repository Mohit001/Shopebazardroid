package com.mohit.shopebazardroid.activity.Checkout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.CartTotalPricesAdapter;
import com.mohit.shopebazardroid.adapter.OrderReviewCartAdapter;
import com.mohit.shopebazardroid.enums.ApiResponseStatus;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.CreateOrderRequest;
import com.mohit.shopebazardroid.model.request.VerifyCouponRequest;
import com.mohit.shopebazardroid.model.response.LoginResponse;
import com.mohit.shopebazardroid.model.response.OrderReviewResponse;
import com.mohit.shopebazardroid.model.response.OrderTotals;
import com.mohit.shopebazardroid.model.response.PaymentMethod;
import com.mohit.shopebazardroid.model.response.RewardsResponse;
import com.mohit.shopebazardroid.models.Address;
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
 * Created by msp on 26/7/16.
 */
public class ActivityCheckoutOrderReview extends BaseActivity implements View
        .OnClickListener, ApiResponse{

    public static String TAG = ActivityCheckoutOrderReview.class.getSimpleName();
    Context mContext;

    public List<UserCartProduct> cartEntityArrayList;
    public static int paymentMethod;
    //    public static Address address_billing;
    public static float shippingCharge;
    public static String selectedPaymentMethod = "";

    RelativeLayout order_review_cart_total_rl/*, order_review_delivery_charges_rl,
            order_review_discount_rl, order_review_gross_total_rl, order_review_tax_rl*/;

    private ExpandableHeightListView listView;
    private OrderReviewCartAdapter adapter;
    private AppCompatTextView totalTextView;
    private AppCompatTextView billingAddressTextView;
    private AppCompatTextView shippingAddressTextView;
    private AppCompatTextView paymentMethodTextView;
//    private AppCompatTextView deliveryChargesTextView;
//    private AppCompatTextView discountTextView;
//    private AppCompatTextView grossTotalTextView;
//    private AppCompatTextView order_review_tax_content;

    private AppCompatTextView verifyCoupanButton;
    private AppCompatButton verifyPincodeButton;
    private AppCompatButton placeOrderButton;

    TextInputLayout verifycoupanTextInputLayout;
    TextInputLayout verifyPincodeTextInputLayout;

    double total = 0, discount = 0, grossTotal = 0, tax = 0;

    UserCart cart;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    private String  orderid;

    private AppCompatTextView order_review_cartitem_lbl, order_review_cart_total_lbl,
            order_review_shipping_address_lbl, order_review_billing_address_lbl,
            order_review_payment_method_lbl/*, order_review_delivery_charges_lbl,order_review_tax_lbl, order_review_discount_lbl,order_review_gross_total_lbl*/;

    private AppCompatEditText order_review_coupan_content_txt;

    RecyclerView recyclerView;
    ArrayList<OrderTotals> totals;
    CartTotalPricesAdapter totals_adapter;

    //Redeem Points
    RelativeLayout rl_redeem_points;
    TextView txt_lbl_redeem_points;
    EditText edt_points;
    SeekBar seekBar;

    String rewardPoints = "";


    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "store_id");
    String userid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "user_id");
    String email = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.EMAIL, "");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_review);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Order Review");


        listView = (ExpandableHeightListView) findViewById(R.id.order_review_cartitem_listview);
        listView.setExpanded(true);

        totalTextView = (AppCompatTextView) findViewById(R.id.order_review_cart_total_content);
        totalTextView.setTypeface(SplashActivity.opensans_semi_bold);

        order_review_coupan_content_txt = (AppCompatEditText) findViewById(R.id.order_review_coupan_content_txt);
        order_review_coupan_content_txt.setTypeface(SplashActivity.opensans_regular);

        order_review_cartitem_lbl = (AppCompatTextView) findViewById(R.id
                .order_review_cartitem_lbl);
        order_review_cartitem_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        order_review_shipping_address_lbl = (AppCompatTextView) findViewById(R.id
                .order_review_shipping_address_lbl);
        order_review_shipping_address_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        order_review_cart_total_lbl = (AppCompatTextView) findViewById(R.id
                .order_review_cart_total_lbl);
        order_review_cart_total_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        order_review_billing_address_lbl = (AppCompatTextView) findViewById(R.id
                .order_review_billing_address_lbl);
        order_review_billing_address_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        order_review_payment_method_lbl = (AppCompatTextView) findViewById(R.id.order_review_payment_method_lbl);
        order_review_payment_method_lbl.setTypeface(SplashActivity.opensans_regular);

        rl_redeem_points = (RelativeLayout) findViewById(R.id.rl_redeem_points);

        txt_lbl_redeem_points = (TextView) findViewById(R.id.txt_lbl_redeem_points);
        txt_lbl_redeem_points.setTypeface(SplashActivity.opensans_regular);

        edt_points = (EditText) findViewById(R.id.edt_points);

        txt_lbl_redeem_points.setTypeface(SplashActivity.opensans_regular);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                Toast.makeText(mContext, "Seekbar : " + progress, Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onProgressChanged: " + progress );
                edt_points.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

//        order_review_delivery_charges_lbl = (AppCompatTextView) findViewById(R.id.order_review_delivery_charges_lbl);
//        order_review_delivery_charges_lbl.setTypeface(SplashActivity.opensans_regular);

//        order_review_discount_lbl = (AppCompatTextView) findViewById(R.id.order_review_discount_lbl);
//        order_review_discount_lbl.setTypeface(SplashActivity.opensans_regular);

//        order_review_tax_lbl = (AppCompatTextView) findViewById(R.id.order_review_tax_lbl);
//        order_review_tax_lbl.setTypeface(SplashActivity.opensans_regular);
//
//        order_review_gross_total_lbl = (AppCompatTextView) findViewById(R.id.order_review_gross_total_lbl);
//        order_review_gross_total_lbl.setTypeface(SplashActivity.opensans_regular);

        order_review_cart_total_rl = (RelativeLayout) findViewById(R.id.order_review_cart_total_rl);
//        order_review_delivery_charges_rl = (RelativeLayout) findViewById(R.id.order_review_delivery_charges_rl);
//        order_review_discount_rl = (RelativeLayout) findViewById(R.id.order_review_discount_rl);
//        order_review_gross_total_rl = (RelativeLayout) findViewById(R.id.order_review_gross_total_rl);
//        order_review_tax_rl = (RelativeLayout) findViewById(R.id.order_review_tax_rl);

        billingAddressTextView = (AppCompatTextView) findViewById(R.id
                .order_review_billing_address);
        billingAddressTextView.setTypeface(SplashActivity.opensans_regular);
        shippingAddressTextView = (AppCompatTextView) findViewById(R.id
                .order_review_shipping_address);
        shippingAddressTextView.setTypeface(SplashActivity.opensans_regular);
        paymentMethodTextView = (AppCompatTextView) findViewById(R.id
                .order_review_payment_method_content);
        paymentMethodTextView.setTypeface(SplashActivity.opensans_regular);
//        deliveryChargesTextView = (AppCompatTextView) findViewById(R.id
//                .order_review_delivery_charges_content);
//        deliveryChargesTextView.setTypeface(SplashActivity.opensans_regular);
//        discountTextView = (AppCompatTextView) findViewById(R.id.order_review_discount_content);
//        discountTextView.setTypeface(SplashActivity.opensans_regular);
//        grossTotalTextView = (AppCompatTextView) findViewById(R.id
//                .order_review_gross_total_content);
//        grossTotalTextView.setTypeface(SplashActivity.opensans_regular);
//
//        order_review_tax_content = (AppCompatTextView) findViewById(R.id.order_review_tax_content);
//        order_review_tax_content.setTypeface(SplashActivity.opensans_regular);

        verifyCoupanButton = (AppCompatTextView) findViewById(R.id.order_review_coupan_verify_btn);
        verifyCoupanButton.setTypeface(SplashActivity.opensans_semi_bold);
        verifyPincodeButton = (AppCompatButton) findViewById(R.id.order_review_verify_pincode_btn);
        verifyPincodeButton.setTypeface(SplashActivity.opensans_regular);
        placeOrderButton = (AppCompatButton) findViewById(R.id.order_review_place_order_btn);
        placeOrderButton.setTypeface(SplashActivity.opensans_regular);
        verifycoupanTextInputLayout = (TextInputLayout) findViewById(R.id
                .order_review_coupan_inputlayout);
        verifycoupanTextInputLayout.setTypeface(SplashActivity.opensans_regular);
        verifyPincodeTextInputLayout = (TextInputLayout) findViewById(R.id
                .order_review_verify_pincode_inputlayout);
        verifyPincodeTextInputLayout.setTypeface(SplashActivity.opensans_regular);

//        adapter = new OrderReviewCartAdapter(mContext, cartEntityArrayList);
//        listView.setAdapter(adapter);

        if (ishideprice.equalsIgnoreCase("0")) {
            order_review_cart_total_rl.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            order_review_cart_total_rl.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }

        verifyCoupanButton.setOnClickListener(this);
        verifyPincodeButton.setOnClickListener(this);
        placeOrderButton.setOnClickListener(this);

        baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_CODE, getString(R.string.rupee_sign));
//        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
// .DISPLAY_CURRENCY_RATE, 1);
//        baseCurrencyValue = 1;
        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_RATE, 1);

        /*HTTPWebRequest.TotalRewardPoints(mContext, email, AppConstants.APICode.TOTAL_REWARD_POINTS, this,
                getSupportFragmentManager());*/

        getCartDetails();
    }

    @Override
    protected void onResume() {
        super.onResume();
        verifycoupanTextInputLayout.clearFocus();
        listView.requestFocus();
//        setupLayout();
    }

    private void getCartDetails() {
        String cartid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .CART_ID, "0");
        HTTPWebRequest.GetCartDetails(mContext, cartid, AppConstants.APICode.GET_CART, this,
                getSupportFragmentManager());

    }

    private void setupLayout() {

        cartEntityArrayList = cart.getUserCartProduct();
        adapter = new OrderReviewCartAdapter(mContext, cartEntityArrayList);
        listView.setExpanded(true);
        listView.setAdapter(adapter);

//        float tempSubtotal = (float) (/*baseCurrencyValue **/ cart.getSubtotal());
//        totalTextView.setText(baseCurrencyCode + String.format("%.2f", tempSubtotal));

        totalTextView.setText(baseCurrencyCode + String.format("%.2f", calculateTotal()));

        Address billingAddress = cart.getBillingAddress();


        billingAddressTextView.setText(billingAddress.getFull_name()
            +"\n"+ billingAddress.getEmail()
            +"\n"+ billingAddress.getContact_number()
            +"\n"+ billingAddress.getAddress1()
            +"\n"+billingAddress.getAddress2()
            +"\n"+billingAddress.getState()
            +"\n"+ billingAddress.getCity() +", "+ billingAddress.getPostcode()
            +"\n"+billingAddress.getAddition_detail());


        Address shippingAddress = cart.getShippingAddress();
        shippingAddressTextView.setText(shippingAddress.getFull_name()
                +"\n"+ shippingAddress.getEmail()
                +"\n"+ shippingAddress.getContact_number()
                +"\n"+ shippingAddress.getAddress1()
                +"\n"+shippingAddress.getAddress2()
                +"\n"+shippingAddress.getState()
                +"\n"+ shippingAddress.getCity() +", "+ shippingAddress.getPostcode()
                +"\n"+shippingAddress.getAddition_detail());



//        paymentMethodTextView.setText(cart.getPayment().getMethod());
        paymentMethodTextView.setText("COD");


//        double deliveryCharges = Double.parseDouble(cart.getBase_grand_total()) - cart
//                .getSubtotal_with_discount();
//        float tempDeliveryCharges = (float) (baseCurrencyValue * deliveryCharges);
//        deliveryChargesTextView.setText(baseCurrencyCode + String.format("%.2f", shippingCharge));
//
//        discount = (Double.parseDouble(cart.getGrand_total()) -
//                shippingCharge) - (cart.getSubtotal());
//        float tempDiscount = (float) (baseCurrencyValue * discount);
//        discountTextView.setText(baseCurrencyCode + String.format("%.2f", Math.abs(tempDiscount)));

//        discount = cart.getBase_discount_amount_total();
//        float tempDiscount = (float) (baseCurrencyValue * discount);
//        discountTextView.setText(baseCurrencyCode + String.format("%.2f", tempDiscount));


//        tax = cart.getBase_tax_amount_total();
//        Toast.makeText(mContext, "Original Tax : " + tax, Toast.LENGTH_SHORT).show();
//        float tax_amount = (float) (baseCurrencyValue * tax);
//        order_review_tax_content.setText(baseCurrencyCode + String.format("%.2f", tax_amount));
//        Toast.makeText(mContext, "tax : " + baseCurrencyCode + String.format("%.2f", tax_amount), Toast.LENGTH_SHORT).show();
//
//        grossTotal = Double.parseDouble(cart.getGrand_total());
//        float tempGrossTotal = (float) (baseCurrencyValue * grossTotal);
//        grossTotalTextView.setText(baseCurrencyCode + String.format("%.2f", tempGrossTotal));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
//                startActivity(new Intent(this, ActivityCheckoutPaymentMethod.class));
                finish();
                return true;
        }
    }

    @Override
    public void onClick(View view) {
        verifycoupanTextInputLayout.setErrorEnabled(false);
        verifyPincodeTextInputLayout.setErrorEnabled(false);

        switch (view.getId()) {

            case R.id.order_review_coupan_verify_btn:
                String coupanCodeString = verifycoupanTextInputLayout.getEditText().getText()
                        .toString();
                if (TextUtils.isEmpty(coupanCodeString)) {
//                    verifycoupanTextInputLayout.setErrorEnabled(true);
//                    verifycoupanTextInputLayout.setError("Please enter valid coupon code");
                    Utility.toastMessage(mContext, "Please enter valid coupon code");
                    return;
                }

                VerifyCouponRequest request = new VerifyCouponRequest();
                request.setShoppingcartid(MyApplication.preferenceGetString(AppConstants
                        .SharedPreferenceKeys.CART_ID, "0"));
                request.setCouponcode(coupanCodeString);
                request.setAction("1");
                HTTPWebRequest.VerifyCoupon(mContext, request, AppConstants.APICode.COUPON_CODE,
                        this, getSupportFragmentManager());

                break;

            case R.id.order_review_verify_pincode_btn:

                String pinCodeString = verifyPincodeTextInputLayout.getEditText().getText()
                        .toString();
                if (TextUtils.isEmpty(pinCodeString)) {
                    verifyPincodeTextInputLayout.setErrorEnabled(true);
                    verifyPincodeTextInputLayout.setError("Please enter valid pincode");

                }

                break;

            case R.id.order_review_place_order_btn:
//                Utility.toastMessage(mContext, "Order place successfully");

                callPlaceOrderApi();
//                callPayumoneyPaymentApi();
                break;
        }
    }

    public void callPlaceOrderApi(){
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        orderRequest.setShoppingcartid(String.valueOf(cart.getCart_id()));
        orderRequest.setIshideprice(ishideprice);
        orderRequest.setStore_id(storeid);
        orderRequest.setUser_id(userid);

        HTTPWebRequest.PlaceOrder(mContext, orderRequest,
                AppConstants.APICode.GET_PLACEORDER, this, getSupportFragmentManager());
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.GET_CART:
                Type orderReviewType = new TypeToken<BaseResponse<UserCart>>(){}.getType();
                Gson gson = new GsonBuilder().serializeNulls().create();
                BaseResponse<UserCart> baseResponse = gson.fromJson(response, orderReviewType);

                if (baseResponse.getStatus() == ApiResponseStatus.CART_GET_DETAILS_SUCCESS.getStatus_code()) {
                    cart = baseResponse.getInfo();

//                    totals_adapter = new CartTotalPricesAdapter(mContext, totals);
//                    recyclerView.setAdapter(totals_adapter);
                    grossTotal = calculateTotal();
                    setupLayout();

                }

                break;
            case AppConstants.APICode.GET_PLACEORDER:
                OrderReviewResponse placeOrderResponse = new Gson().fromJson(response,
                        OrderReviewResponse.class);

                if (placeOrderResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }


                if (placeOrderResponse.getStatus().equalsIgnoreCase("success")) {
//                    Utility.toastMessage(mContext, R.string.order_place_success);
                    PaymentMethod paymentMethod = (PaymentMethod) getIntent().getSerializableExtra(AppConstants.INTENTDATA.PAYMENT_METHOD);
//                    paymentMethod.setCode("payucheckout_shared");
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID,"0");
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS,"0");

                    if(paymentMethod.getCode().equalsIgnoreCase("payucheckout_shared")){
//                        callPayumoneyPaymentApi(placeOrderResponse.getResult().getOrderid());
                        orderid = placeOrderResponse.getResult().getOrderid();
                    } else{
                        Utility.toastMessage(mContext, R.string.order_place_success);
                        this.finish();
                        Intent intent = new Intent(this, NavigationDrawerActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                } else {
                    Utility.toastMessage(mContext, placeOrderResponse.getResult().getMessage());
                }
                break;
            case AppConstants.APICode.COUPON_CODE:
                LoginResponse couponCodeResponse = new Gson().fromJson(response, LoginResponse
                        .class);
                if (couponCodeResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (couponCodeResponse.getStatus().equalsIgnoreCase("success")) {
                    Utility.toastMessage(mContext, couponCodeResponse.getResult().getMessage());
                    getCartDetails();
                    verifycoupanTextInputLayout.getEditText().setText("");
                } else {
                    Utility.toastMessage(mContext, "Invalid coupon code.");
                    verifycoupanTextInputLayout.getEditText().setText("");
                }
                break;

            case AppConstants.APICode.TOTAL_REWARD_POINTS:

                try {

                    RewardsResponse response1 = new Gson().fromJson(response,
                            RewardsResponse.class);


                    if (response1.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(mContext, LoginActivity.class));
                        finish();
                        return;
                    }

                    rewardPoints = response1.getResult().getRewardpoints();

                    if (response1.getResult().getRewardpoints().equalsIgnoreCase("0")) {
                        rl_redeem_points.setVisibility(View.GONE);
                    }

                    edt_points.setText(rewardPoints);

                    seekBar.setMax(Integer.parseInt(rewardPoints));
                    Log.e(TAG, "onProgressChanged: " + seekBar.getMax());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case AppConstants.APICode.PAYU_MONEY_PLACE_ORDER:
                Log.d(TAG, response);
//                Utility.toastMessage(mContext, R.string.order_place_success);
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID,"0");
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS,"0");
                this.finish();
                Intent intent = new Intent(this, NavigationDrawerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private double calculateTotal(){
        total = 0;
        List<UserCartProduct> list = cart.getUserCartProduct();
        for (UserCartProduct userCartProduct : list){
            total += Double.parseDouble(userCartProduct.getSubtotal());
        }

        return total;
    }


}