package com.mohit.shopebazardroid.activity.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;
import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.AddCommentsOrderHistoryAdapter;
import com.mohit.shopebazardroid.adapter.OrderHistoryCartAdapter;
import com.mohit.shopebazardroid.dialog.ConfirmDialog;
import com.mohit.shopebazardroid.dialog.TrackingProcessDialog;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.model.request.AddCommentInOrderRequest;
import com.mohit.shopebazardroid.model.request.Mobile_return;
import com.mohit.shopebazardroid.model.request.ReorderRequest;
import com.mohit.shopebazardroid.model.response.AddCartResponse;
import com.mohit.shopebazardroid.model.response.AddCommentResponse;
import com.mohit.shopebazardroid.model.response.Address;
import com.mohit.shopebazardroid.model.response.HistoryCartItem;
import com.mohit.shopebazardroid.model.response.HistoryEntity;
import com.mohit.shopebazardroid.model.response.HistoryStatus;
import com.mohit.shopebazardroid.model.response.OrderReviewResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;

/**
 * Created by msp on 27/7/16.
 */
public class OrderHistoryDetailActivity extends BaseActivity implements ApiResponse, View.OnClickListener, ConfirmDialogListner {

    public static String TAG = OrderHistoryDetailActivity.class.getSimpleName();
    Context mContext;
    private ArrayList<HistoryCartItem> arrayList;
    private int paymentMethod = 0;
    private Address address;

    private ExpandableHeightListView listView;
    private OrderHistoryCartAdapter adapter;
    private AppCompatTextView totalTextView;
    private AppCompatTextView billingAddressTextView;
    private AppCompatTextView shippingAddressTextView;
    private AppCompatTextView paymentMethodTextView;
    private AppCompatTextView deliveryChargesTextView;
    private AppCompatTextView discountTextView;
    private AppCompatTextView grossTotalTextView;
    private AppCompatTextView coupancodeTextView;
    private AppCompatTextView orderStatusTextView;
    private AppCompatTextView ohd_total_lbl;
    private TextView ohd_billingaddress_lbl, ohd_shippingaddress_lbl;
    private AppCompatTextView ohd_status_lbl, ohd_payment_lbl, ohd_coupan_code_lbl,
            ohd_delivery_charges_lbl, ohd_discount_lbl, ohd_gross_total_lbl;


    double total = 0, discount = 0, grossTotal = 0;
    private String status, coupanCode, incrementid;
    private HistoryEntity orderinfo;

    String baseCurrencyCode = "";
    float baseCurrencyRate;

    int themeCode;

    Button btn_order_tracking, btn_refund_process;
    TrackingProcessDialog trackingProcessDialog;

    String str_web_track, str_return_days, str_delivery_date, custom_shipment_id, order_id;
//    String str_disable;

    ConfirmDialog confirmDialog;

    RelativeLayout ohd_cart_total_rl, ohd_del_charges_rl, ohd_extra_disc_rl, ohd_total_rl, rel_comments;

    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");

    RecyclerView recyclerView;
    private ArrayList<HistoryStatus> status_history;
    AddCommentsOrderHistoryAdapter comment_adapter;

    EditText edt_add_comment;
    Button btn_send;
    String orderid;
    private Button reorderButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_detail);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Order Details");
        /*baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .BASECURRENCY_SYMBOL, "$");
        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
        .BASECURRENCY_VALUE, 0);*/

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        listView = (ExpandableHeightListView) findViewById(R.id.ohd_cartitem_listview);
        listView.setExpanded(true);


        ohd_total_lbl = (AppCompatTextView) findViewById(R.id.ohd_total_lbl);
        ohd_total_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        ohd_shippingaddress_lbl = (TextView) findViewById(R.id.ohd_shippingaddress_lbl);
        ohd_shippingaddress_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        totalTextView = (AppCompatTextView) findViewById(R.id.ohd_total_content);
        totalTextView.setTypeface(SplashActivity.opensans_semi_bold);

        ohd_billingaddress_lbl = (TextView) findViewById(R.id.ohd_billingaddress_lbl);
        ohd_billingaddress_lbl.setTypeface(SplashActivity.opensans_semi_bold);

        billingAddressTextView = (AppCompatTextView) findViewById(R.id.ohd_billingaddress_content);
        billingAddressTextView.setTypeface(SplashActivity.opensans_regular);

        shippingAddressTextView = (AppCompatTextView) findViewById(R.id
                .ohd_shippingaddress_content);
        shippingAddressTextView.setTypeface(SplashActivity.opensans_regular);

        orderStatusTextView = (AppCompatTextView) findViewById(R.id.ohd_status_content);
        orderStatusTextView.setTypeface(SplashActivity.opensans_regular);

        paymentMethodTextView = (AppCompatTextView) findViewById(R.id.ohd_payment_content);
        paymentMethodTextView.setTypeface(SplashActivity.opensans_regular);

        coupancodeTextView = (AppCompatTextView) findViewById(R.id.ohd_coupan_code_content);
        coupancodeTextView.setTypeface(SplashActivity.opensans_regular);

        deliveryChargesTextView = (AppCompatTextView) findViewById(R.id
                .ohd_delivery_charges_content);
        deliveryChargesTextView.setTypeface(SplashActivity.opensans_regular);

        discountTextView = (AppCompatTextView) findViewById(R.id.ohd_discount_content);
        discountTextView.setTypeface(SplashActivity.opensans_regular);

        grossTotalTextView = (AppCompatTextView) findViewById(R.id.ohd_gross_total_content);
        grossTotalTextView.setTypeface(SplashActivity.opensans_regular);

        ohd_status_lbl = (AppCompatTextView) findViewById(R.id.ohd_status_lbl);
        ohd_status_lbl.setTypeface(SplashActivity.opensans_regular);

        ohd_payment_lbl = (AppCompatTextView) findViewById(R.id.ohd_payment_lbl);
        ohd_payment_lbl.setTypeface(SplashActivity.opensans_regular);

        ohd_coupan_code_lbl = (AppCompatTextView) findViewById(R.id.ohd_coupan_code_lbl);
        ohd_coupan_code_lbl.setTypeface(SplashActivity.opensans_regular);

        ohd_delivery_charges_lbl = (AppCompatTextView) findViewById(R.id.ohd_delivery_charges_lbl);
        ohd_delivery_charges_lbl.setTypeface(SplashActivity.opensans_regular);

        ohd_discount_lbl = (AppCompatTextView) findViewById(R.id.ohd_discount_lbl);
        ohd_discount_lbl.setTypeface(SplashActivity.opensans_regular);

        ohd_gross_total_lbl = (AppCompatTextView) findViewById(R.id.ohd_gross_total_lbl);
        ohd_gross_total_lbl.setTypeface(SplashActivity.opensans_regular);

        btn_order_tracking = (Button) findViewById(R.id.btn_order_tracking);
        btn_order_tracking.setOnClickListener(this);

        btn_refund_process = (Button) findViewById(R.id.btn_refund_process);
        btn_refund_process.setOnClickListener(this);

        ohd_cart_total_rl = (RelativeLayout) findViewById(R.id.ohd_cart_total_rl);
        ohd_del_charges_rl = (RelativeLayout) findViewById(R.id.ohd_del_charges_rl);
        ohd_extra_disc_rl = (RelativeLayout) findViewById(R.id.ohd_extra_disc_rl);
        ohd_total_rl = (RelativeLayout) findViewById(R.id.ohd_total_rl);

        edt_add_comment = (EditText) findViewById(R.id.edt_add_comment);

        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);

        rel_comments = (RelativeLayout) findViewById(R.id.rel_comments);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        orderid = getIntent().getStringExtra("notification");
        incrementid = getIntent().getStringExtra(AppConstants.RequestDataKey.INVOICE_ID);
        status = getIntent().getStringExtra(AppConstants.RequestDataKey.STATUS);
        coupanCode = getIntent().getStringExtra(AppConstants.RequestDataKey.COUPON_CODE);
        baseCurrencyCode = getIntent().getStringExtra(AppConstants.RequestDataKey.CURRENCY_SYMBOL);

        baseCurrencyRate = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_RATE, 1);
        baseCurrencyCode = MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, getString(R.string.rupee_sign));

        reorderButton = (Button) findViewById(R.id.reorder_button);
        reorderButton.setVisibility(View.VISIBLE);
        reorderButton.setOnClickListener(this);

        if (ishideprice.equalsIgnoreCase("0")) {
            ohd_cart_total_rl.setVisibility(View.VISIBLE);
            ohd_del_charges_rl.setVisibility(View.VISIBLE);
            ohd_extra_disc_rl.setVisibility(View.VISIBLE);
            ohd_total_rl.setVisibility(View.VISIBLE);
        } else {
            ohd_cart_total_rl.setVisibility(View.GONE);
            ohd_del_charges_rl.setVisibility(View.GONE);
            ohd_extra_disc_rl.setVisibility(View.GONE);
            ohd_total_rl.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

//        setupLayout();
        getOrderDetails();
    }

    private void getOrderDetails() {

        if (getIntent().getStringExtra("isFrom").equalsIgnoreCase("notif")) {
            HTTPWebRequest.OrderDetails(mContext, orderid, AppConstants.APICode.ORDER_DETAIL, this,
                    getSupportFragmentManager());
        } else if (getIntent().getStringExtra("isFrom").equalsIgnoreCase("order")) {
            HTTPWebRequest.OrderDetails(mContext, incrementid, AppConstants.APICode.ORDER_DETAIL, this,
                    getSupportFragmentManager());
        }


    }
    /*private void setupLayout()
    {
        arrayList = new ArrayList<>();
        CartEntity entity1= new CartEntity();
        entity1.setId(1);
        entity1.setName("Product1");
        entity1.setQuentity(1);
        entity1.setSize("XL");
        entity1.setLatestprice(4000);
        entity1.setOldprice(5000);
        entity1.setSubtotal(entity1.getQuentity() * entity1.getLatestprice());
        entity1.setImageurl("https://www.flourish
        .org/wordpress/wp-content/uploads/2012/04/bestin_product_range-300x221.jpg");
        arrayList.add(entity1);

        CartEntity entity2= new CartEntity();
        entity2.setId(2);
        entity2.setName("Product2");
        entity2.setQuentity(2);
        entity2.setSize("XXXL");
        entity2.setLatestprice(1000);
        entity2.setOldprice(2000);
        entity2.setSubtotal(entity2.getQuentity() * entity2.getLatestprice());
        entity2.setImageurl("https://www.flourish
        .org/wordpress/wp-content/uploads/2012/04/bestin_product_range-300x221.jpg");
        arrayList.add(entity2);


        adapter = new OrderReviewCartAdapter(mContext, arrayList);
        listView.setAdapter(adapter);

        total = 0;
        CartEntity entity;
        for (int i = 0; i < arrayList.size(); i++) {
            entity = arrayList.get(i);
            total += entity.getSubtotal();
        }

        totalTextView.setText("$"+String.valueOf(total));

        String addressString = "test alias"
                +"\n"+"A\404"
                +"\n"+"test street"
                +"\n"+"test landmark"
                +"\n"+"Ahmedabd, gujarat"
                +"\n"+"india"
                +"\n"+"380009"
                +"\n"+"test@gmail.com"
                +"\n"+"1234567890";

        addressTextView.setText(addressString);

        orderStatusTextView.setText("Delivered");

        switch (paymentMethod)
        {
            default:
            case 0:
                paymentMethodTextView.setText("COD");
                break;
            case 1:
                paymentMethodTextView.setText("card payment");
                break;
            case 2:
                paymentMethodTextView.setText("Bank transfer");
                break;
        }

        coupancodeTextView.setText("NEW50");
        deliveryChargesTextView.setText("$0");

        discount = 100;
        discountTextView.setText("$"+String.valueOf(discount));

        grossTotal = total-discount;
        grossTotalTextView.setText("$"+String.valueOf(grossTotal));



    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                this.finish();
                return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reorder_button:
                ReorderRequest request = new ReorderRequest();
                request.setCustomer_id(getUserid());
                request.setStore_id(getStoreID());
                if (getIntent().getStringExtra("isFrom").equalsIgnoreCase("notif")) {
                    request.setOrderid(orderid);
                } else if (getIntent().getStringExtra("isFrom").equalsIgnoreCase("order")) {
                    request.setOrderid(incrementid);
                }


                HTTPWebRequest.Reorder(mContext, request, AppConstants.APICode.REORDER, this,getSupportFragmentManager());

                break;
            case R.id.btn_order_tracking:
                trackingProcessDialog = new TrackingProcessDialog();
                Bundle args = new Bundle();
                args.putString("web_track", str_web_track);
                trackingProcessDialog.setArguments(args);
                trackingProcessDialog.show(getSupportFragmentManager(), TrackingProcessDialog.TAG);

                break;
            case R.id.btn_refund_process:
//                Utility.toastMessage(mContext, "Refund Process");
                confirmDialog = new ConfirmDialog();
                confirmDialog.setListner(this, getResources().getString(R.string.app_name), "Are you sure you want to return this order?", "Ok", "Cancel");
                confirmDialog.show(getSupportFragmentManager(), ConfirmDialog.TAG);
                break;
            case R.id.btn_send:

                if (!edt_add_comment.getText().toString().trim().isEmpty()) {
                    AddCommentInOrderRequest comment_request = new AddCommentInOrderRequest();
                    comment_request.setIncrement_id(orderinfo.getIncrement_id());
                    comment_request.setComment(edt_add_comment.getText().toString());
                    comment_request.setOrder_status(orderinfo.getStatus());

                    HTTPWebRequest.AddCommentOrder(mContext, comment_request, AppConstants.APICode.ADD_COMMENT, this,
                            getSupportFragmentManager());
                } else {
                    Toast.makeText(mContext, "Please write comment", Toast.LENGTH_SHORT).show();
                }


                break;
        }

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {

            case AppConstants.APICode.ADD_COMMENT:

                try {

                    AddCommentResponse comment_response = new Gson().fromJson(response,
                            AddCommentResponse.class);

                    if (comment_response.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                        Utility.toastMessage(mContext, R.string.subscription_over);
                        MyApplication.clearPreference();
                        startActivity(new Intent(mContext, LoginActivity.class));
                        finish();
                        return;
                    }
                    if (comment_response.getStatus().equalsIgnoreCase("success")) {
                        edt_add_comment.setText("");
                        Utility.toastMessage(mContext, comment_response.getResult().getMessage());

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                getOrderDetails();

                break;

            case AppConstants.APICode.MOBILE_RETURN:
            case AppConstants.APICode.ORDER_DETAIL:
                OrderReviewResponse reviewResponse = new Gson().fromJson(response,
                        OrderReviewResponse.class);
                if (reviewResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase
                        ("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (reviewResponse.getStatus().equalsIgnoreCase("success")) {
                    if (apiCode == AppConstants.APICode.MOBILE_RETURN)
                        Utility.toastMessage(mContext, reviewResponse.getResult().getMessage());
                    orderinfo = reviewResponse.getResult().getOrderinfo();

                }
                setupLayout();
                break;

            case AppConstants.APICode.REORDER:
                AddCartResponse addCartResponse = new Gson().fromJson(response, AddCartResponse.class);
                Toast.makeText(mContext, addCartResponse.getResult().getMessage(), Toast.LENGTH_SHORT).show();
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID, addCartResponse.getResult().getData().getShoppingcartid());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, addCartResponse.getResult().getData().getItems_count());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, addCartResponse.getResult().getData().getItems_qty());
                updateCartCount();
                break;

            /*case AppConstants.APICode.MOBILE_RETURN:
                LoginResponse returnResponse = new Gson().fromJson(response, LoginResponse.class);

                if (returnResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }


                if (returnResponse.getStatus().equalsIgnoreCase("success")) {
                    Utility.toastMessage(mContext, "Product returned successfully");
                    setupLayout();
                } else {
                    Utility.toastMessage(mContext, "Error occured");
                }*/
        }
    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(int apiCode) {

    }

    private void setupLayout() {

        if(orderinfo == null)
            return;

        arrayList = orderinfo.getItems();
        adapter = new OrderHistoryCartAdapter(mContext, arrayList);

        status_history = orderinfo.getStatus_history();
        comment_adapter = new AddCommentsOrderHistoryAdapter(mContext, status_history);
        recyclerView.setAdapter(comment_adapter);

        listView.setExpanded(true);
        listView.setAdapter(adapter);

//        str_disable = orderinfo.getDisabled();
        str_return_days = orderinfo.getReturn_days();
        str_web_track = orderinfo.getState();
        str_delivery_date = orderinfo.getDelivery_date();

        float tempSubtotal = (float) (baseCurrencyRate * Double.parseDouble(orderinfo
                .getSubtotal()));
        totalTextView.setText(baseCurrencyCode + String.format("%.2f", tempSubtotal));

//        orderStatusTextView.setText(TextUtils.isEmpty(str_web_track)?status_history.get(status_history.size() -1).getStatus():str_web_track);
        orderStatusTextView.setText(TextUtils.isEmpty(str_web_track)?status_history.get(status_history.size() -1).getStatus():str_web_track);
        coupancodeTextView.setText(TextUtils.isEmpty(coupanCode) == true ? "No coupon" :
                coupanCode);

        Address address_billing = orderinfo.getBilling_address();
        String[] street_billing = address_billing.getStreet().split("\n");

        String addressString, getLandmark, getCity, getRegion, getCountry, getPostcode, getEmail,
                getTelephone;

        getLandmark = address_billing.getLandmark();
        getCity = address_billing.getCity();
        getRegion = address_billing.getRegion();
        getCountry = address_billing.getCountry();
        getPostcode = address_billing.getPostcode();
        getEmail = address_billing.getEmail();
        getTelephone = address_billing.getTelephone();


        addressString = address_billing.getFirstname()
                + "\n" + street_billing[0];
        if(street_billing.length > 1) {
            addressString = addressString+"\n" + street_billing[1];
        }

        if (!TextUtils.isEmpty(getLandmark)) {
            addressString = addressString + "\n" + address_billing.getLandmark();
        }
        if (!TextUtils.isEmpty(getCity)) {
            addressString = addressString + "\n" + address_billing.getCity();

        }
        if (!TextUtils.isEmpty(getRegion)) {
            addressString = addressString + "\n" + address_billing.getRegion();

        }
        if (!TextUtils.isEmpty(getCountry)) {
            addressString = addressString + "\n" + address_billing.getCountry();

        }
        if (!TextUtils.isEmpty(getPostcode)) {
            addressString = addressString + "\n" + address_billing.getPostcode();

        }
        if (!TextUtils.isEmpty(getEmail)) {
            addressString = addressString + "\n" + address_billing.getEmail();

        }
//        if (!TextUtils.isEmpty(getCity)) {
//            addressString = addressString + "\n" + address_billing.getCity();
//
//        }
//        if (!TextUtils.isEmpty(getTelephone)) {
//            addressString = addressString + "\n" + address_billing.getTelephone() ;
//
//        }

//        addressString = addressString.replace("null", "");

        billingAddressTextView.setText(addressString);

        Address address_shipping = orderinfo.getShipping_address();
        String[] street_shipping = address_shipping.getStreet().split("\n");

        String shippingAddressString, s_getCity, s_getRegion, s_getCountry, s_getPostcode,
                s_getEmail, s_getTelephone;

        s_getCity = address_shipping.getCity();
        s_getRegion = address_shipping.getRegion();
        s_getCountry = address_shipping.getCountry();
        s_getPostcode = address_shipping.getPostcode();
        s_getEmail = address_shipping.getEmail();
        s_getTelephone = address_shipping.getTelephone();


        shippingAddressString = address_shipping.getFirstname()
                + "\n" + street_shipping[0];

        if(street_shipping.length > 1){
            shippingAddressString = shippingAddressString + "\n" + street_shipping[1];
        }

        if (!TextUtils.isEmpty(s_getCity)) {
            shippingAddressString = shippingAddressString + "\n" + address_shipping.getCity();
        }

        if (!TextUtils.isEmpty(s_getRegion)) {
            shippingAddressString = shippingAddressString + "\n" + address_shipping.getRegion();
        }

        if (!TextUtils.isEmpty(s_getCountry)) {
            shippingAddressString = shippingAddressString + "\n" + address_shipping.getCountry();
        }

        if (!TextUtils.isEmpty(s_getPostcode)) {
            shippingAddressString = shippingAddressString + "\n" + address_shipping.getPostcode();
        }

        if (!TextUtils.isEmpty(s_getEmail)) {
            shippingAddressString = shippingAddressString + "\n" + address_shipping.getEmail();
        }
//
//        if (!TextUtils.isEmpty(s_getTelephone)) {
//            shippingAddressString = shippingAddressString + "\n" + address_shipping
// .getTelephone();
//        }

//        shippingAddressString = shippingAddressString.replace("null", "");

        shippingAddressTextView.setText(shippingAddressString);

        paymentMethodTextView.setText(orderinfo.getPayment().getMethod());


//        float tempDeliveryCharges = (float) ((Double.parseDouble(orderinfo.getGrand_total()) +
//                Double.parseDouble(orderinfo.getDiscount_amount())) - Double.parseDouble
//                (orderinfo.getSubtotal()));
//        tempDeliveryCharges = baseCurrencyValue * tempDeliveryCharges;
        float tempDeliveryCharges = (float) (baseCurrencyRate * Double.parseDouble(orderinfo
                .getShipping_amount()));
        deliveryChargesTextView.setText(baseCurrencyCode + String.format("%.2f", Math.abs
                (tempDeliveryCharges)));

        discount = baseCurrencyRate * Double.parseDouble(orderinfo.getDiscount_amount());
        discountTextView.setText(baseCurrencyCode + String.format("%.2f", Math.abs(discount)));

        grossTotal = baseCurrencyRate * Double.parseDouble(orderinfo.getGrand_total());
        grossTotalTextView.setText(baseCurrencyCode + String.format("%.2f", grossTotal));


        custom_shipment_id = orderinfo.getCustom_shipment_id();
        order_id = orderinfo.getOrder_id();

        btn_order_tracking.setVisibility(View.GONE);
        btn_refund_process.setVisibility(View.GONE);
        rel_comments.setVisibility(View.GONE);

        /*if (str_disable.equalsIgnoreCase("1")) {
            btn_order_tracking.setVisibility(View.GONE);
            btn_refund_process.setVisibility(View.GONE);
            rel_comments.setVisibility(View.GONE);
        } else {
            btn_order_tracking.setVisibility(View.VISIBLE);
            btn_refund_process.setVisibility(View.VISIBLE);
            rel_comments.setVisibility(View.VISIBLE);

            if (str_web_track.equalsIgnoreCase("Canceled")) {
                btn_order_tracking.setVisibility(View.GONE);
                btn_refund_process.setVisibility(View.GONE);
                rel_comments.setVisibility(View.GONE);
            }

            if (!TextUtils.isEmpty(str_delivery_date)) {

//        String dt = "2016-10-14";  // Start date
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(str_delivery_date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                c.add(Calendar.DATE, Integer.parseInt(str_return_days));  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                String DeliveryDate = sdf1.format(c.getTime());

                String Current_date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                Log.e("Delivery Date", "---->>>" + DeliveryDate);

                Log.e("Current date ", "---->>>>" + Current_date);

                Date strDate = null;
                Date curdate = null;
                try {
                    strDate = sdf.parse(DeliveryDate);
                    curdate = sdf.parse(Current_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (curdate.after(strDate)) {
                    btn_refund_process.setVisibility(View.GONE);
                }
            }
        }*/


//        if (sdf1.parse(Current_date).after(sdf1.parse(DeliveryDate))){
//
//        }

    }


    @Override
    public void onPositiveButtonClick() {
        confirmDialog.dismiss();

        Mobile_return mobile_return = new Mobile_return();
        mobile_return.setCustom_shipment_id(custom_shipment_id);
        mobile_return.setShipment_status("4");
        mobile_return.setOrder_id(order_id);
        //API calling
        HTTPWebRequest.Mobile_return(mContext, mobile_return, AppConstants.APICode.MOBILE_RETURN, this,
                getSupportFragmentManager());

    }

    @Override
    public void onNegativeButtonClick() {

        confirmDialog.dismiss();


    }


}
