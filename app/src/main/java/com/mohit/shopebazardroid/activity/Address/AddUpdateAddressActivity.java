package com.mohit.shopebazardroid.activity.Address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Checkout.CartActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.models.Address;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by msp on 28/7/16.
 */
public class AddUpdateAddressActivity extends BaseActivity implements View.OnClickListener, ApiResponse {

    public static String TAG = AddUpdateAddressActivity.class.getSimpleName();
    Context mContext;

    //public static Address address;
    Address address;
    TextInputLayout fullNameInputLayout;
    TextInputLayout emailInputLayout;
    TextInputLayout addressLine1InputLayout;
    TextInputLayout addressLine2InputLayout;
    TextInputLayout stateInputLayout;
    TextInputLayout cityInputLayout;
    TextInputLayout countryInputLayout;
    TextInputLayout pincodeInputLayout;
    TextInputLayout additionalDetailsInputLayout;
    TextInputLayout contactNumberInputLayout;

    AppCompatButton cancelButton, submitButton;

    boolean showCart;

    AppCompatCheckBox billingCheckBox, shippingCheckBox;
    Intent intent;
    Bundle bundle;

    Menu menu;
    View cartBadget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addupdateaddress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Add new Address");
        mContext = this;

        fullNameInputLayout = (TextInputLayout) findViewById(R.id.fullname_inputlayout);
        fullNameInputLayout.setTypeface(SplashActivity.opensans_regular);

        addressLine1InputLayout = (TextInputLayout) findViewById(R.id.address_line1_inputlayout);
        addressLine1InputLayout.setTypeface(SplashActivity.opensans_regular);

        addressLine2InputLayout = (TextInputLayout) findViewById(R.id.address_line2_inputlayout);
        addressLine2InputLayout.setTypeface(SplashActivity.opensans_regular);

        cityInputLayout = (TextInputLayout) findViewById(R.id.address_city_inputlayout);
        cityInputLayout.setTypeface(SplashActivity.opensans_regular);

        stateInputLayout = (TextInputLayout) findViewById(R.id.address_state_inputlayout);
        stateInputLayout.setTypeface(SplashActivity.opensans_regular);

        pincodeInputLayout = (TextInputLayout) findViewById(R.id.address_pincode_inputlayout);
        pincodeInputLayout.setTypeface(SplashActivity.opensans_regular);

        emailInputLayout = (TextInputLayout) findViewById(R.id.email_inputlayout);
        emailInputLayout.setTypeface(SplashActivity.opensans_regular);

        contactNumberInputLayout = (TextInputLayout) findViewById(R.id.contact_number_inputlayout);
        contactNumberInputLayout.setTypeface(SplashActivity.opensans_regular);

        additionalDetailsInputLayout = (TextInputLayout) findViewById(R.id.additional_details_inputlayout);
        additionalDetailsInputLayout.setTypeface(SplashActivity.opensans_regular);


        billingCheckBox = (AppCompatCheckBox) findViewById(R.id.address_billing_checkbox);
        billingCheckBox.setTypeface(SplashActivity.opensans_regular);


        shippingCheckBox = (AppCompatCheckBox) findViewById(R.id.address_shipping_checkbox);
        shippingCheckBox.setTypeface(SplashActivity.opensans_regular);

        cancelButton = (AppCompatButton) findViewById(R.id.cancel_btn);
        cancelButton.setTypeface(SplashActivity.opensans_regular);
        submitButton = (AppCompatButton) findViewById(R.id.submit_btn);
        submitButton.setTypeface(SplashActivity.opensans_regular);

        cancelButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        String emailString = getStringPreferencesValue(AppConstants.SharedPreferenceKeys.EMAIL);
        emailInputLayout.getEditText().setText(emailString);

        try {

            intent = getIntent();
            if (intent.getExtras().getString("key").equals("edit")) {
                setupScreen();
            }

            showCart = intent.getBooleanExtra(AppConstants.INTENTDATA.Show_cart, false);

        } catch (Exception e) {

        }

    }

    private void setupScreen() {


        if (intent.getExtras().getString("key").equals("edit")) {
            bundle = intent.getExtras().getBundle("bundle");
            address = (Address) bundle.getSerializable("address");
            getSupportActionBar().setTitle("Update Address");
            submitButton.setText("UPDATE");
            fullNameInputLayout.getEditText().setText(address.getFull_name());
            emailInputLayout.getEditText().setText(address.getEmail());
            contactNumberInputLayout.getEditText().setText(address.getContact_number());
            addressLine1InputLayout.getEditText().setText(address.getAddress1());
            addressLine2InputLayout.getEditText().setText(address.getAddress2());
            cityInputLayout.getEditText().setText(address.getCity());
            stateInputLayout.getEditText().setText(address.getState());
            pincodeInputLayout.getEditText().setText(address.getPostcode());
            additionalDetailsInputLayout.getEditText().setText(address.getAddition_detail());

            /*if (address.is_default_billing()) {
                billingCheckBox.setChecked(true);
                billingCheckBox.setText(" This is default billing address");
            }

            if (address.is_default_shipping()) {
                shippingCheckBox.setChecked(true);
                shippingCheckBox.setText(" This is default Shipping address");
            }

            if (billingCheckBox.isChecked()) {
                billingCheckBox.setClickable(false);
            }

            if (shippingCheckBox.isChecked()) {
                shippingCheckBox.setClickable(false);
            }*/

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (showCart == true) {
            this.menu = menu;
            getMenuInflater().inflate(R.menu.main_menu, menu);

            MenuItem item = menu.findItem(R.id.action_search);
            item.setVisible(false);
            //        searchView.setMenuItem(item);
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            cartBadget = inflater.inflate(R.layout.cart_badge_actionbar, null);
            cartBadget.setLayoutParams(new RelativeLayout.LayoutParams(item.getIcon()
                    .getIntrinsicWidth() + 50, item.getIcon().getIntrinsicHeight() + 20));

            TextView textView = (TextView) cartBadget.findViewById(R.id.counter);
            //        textView.setText("0");
            textView.setText("" + MyApplication.preferenceGetString(AppConstants
                    .SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
            MenuItem item2 = menu.findItem(R.id.action_cart);
            item2.setActionView(cartBadget);

            cartBadget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (MyApplication.preferenceGetBoolean(AppConstants.SharedPreferenceKeys
                            .IS_LOGGED_IN, false)) {
                        startActivity(new Intent(mContext, CartActivity.class));
                    } else {
                        finish();
                        startActivity(new Intent(mContext, LoginActivity.class));
                    }
                }
            });
        } else {

        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                address = null;
                this.finish();
                return true;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cancel_btn:
                this.finish();
                break;
            case R.id.submit_btn:

                disableErrorMessage();

                try {


                    String fullNameString = fullNameInputLayout.getEditText().getText().toString().trim();
                    if (TextUtils.isEmpty(fullNameString)) {
//                        fullNameInputLayout.setErrorEnabled(true);
//                        fullNameInputLayout.setError("Please enter alias");
                        Utility.toastMessage(mContext, "Please enter full name");
                        return;
                    }

                    String  emailString = emailInputLayout.getEditText().getText().toString().trim();
                    if(TextUtils.isEmpty(emailString))
                    {
//                        emailInputLayout.setErrorEnabled(true);
//                        emailInputLayout.setError("Please enter email");
                        Utility.toastMessage(mContext, "Please enter full name");
                        return;
                    }

                    String  contactNumberString = contactNumberInputLayout.getEditText().getText().toString().trim();
                    if(TextUtils.isEmpty(contactNumberString))
                    {
//                        emailInputLayout.setErrorEnabled(true);
//                        emailInputLayout.setError("Please enter contact number");
                        Utility.toastMessage(mContext, "Please enter full name");
                        return;
                    }



                    String addressLine1String = addressLine1InputLayout.getEditText().getText()
                            .toString().trim();
                    if (TextUtils.isEmpty(addressLine1String)) {
//                        addressLine1InputLayout.setErrorEnabled(true);
//                        addressLine1InputLayout.setError("Please enter home/office no");
                        Utility.toastMessage(mContext, "Please enter address line 1");
                        return;
                    }

                    String addressLine2String = addressLine2InputLayout.getEditText().getText().toString()
                            .trim();
                    if (TextUtils.isEmpty(addressLine2String)) {
//                        addressLine2InputLayout.setErrorEnabled(true);
//                        addressLine2InputLayout.setError("Please enter street address");
                        Utility.toastMessage(mContext, "Please enter address line 2");
                        return;
                    }


                    String cityString = cityInputLayout.getEditText().getText().toString().trim();
                    if (TextUtils.isEmpty(cityString)) {
//                        cityInputLayout.setErrorEnabled(true);
//                        cityInputLayout.setError("Please enter city name");
                        Utility.toastMessage(mContext, "Please enter city name");
                        return;
                    }

                    String stateString = stateInputLayout.getEditText().getText().toString().trim();
                    if (TextUtils.isEmpty(stateString)) {
//                        cityInputLayout.setErrorEnabled(true);
//                        cityInputLayout.setError("Please enter city name");
                        Utility.toastMessage(mContext, "Please enter city state");
                        return;
                    }



                    String pincodeString = pincodeInputLayout.getEditText().getText().toString()
                            .trim();
                    if (TextUtils.isEmpty(pincodeString)) {
//                        pincodeInputLayout.setErrorEnabled(true);
//                        pincodeInputLayout.setError("Please enter pincode");
                        Utility.toastMessage(mContext, "Please enter pincode");
                        return;
                    }


                    String additionalDetailsString = additionalDetailsInputLayout.getEditText().getText().toString()
                            .trim();
                    if (TextUtils.isEmpty(additionalDetailsString)) {
//                        mobileInputLayout.setErrorEnabled(true);
//                        mobileInputLayout.setError("Please enter ");

//                        Utility.toastMessage(mContext, "Please enter additional details");
//                        return;

                        additionalDetailsString = "";
                    }
                    disableErrorMessage();

                    if(address == null) {
                        address = new Address();
                        address.setUser_id(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "0"));
                    }

                    address.setFull_name(fullNameString);
                    address.setEmail(emailString);
                    address.setContact_number(contactNumberString);
                    address.setAddress1(addressLine1String);
                    address.setAddress2(addressLine2String);
                    address.setState(stateString);
                    address.setCity(cityString);
                    address.setPostcode(pincodeString);
                    address.setAddition_detail(additionalDetailsString);

                    String jsonRequestString = new Gson().toJson(address);
                    if (address.getAddress_id() == 0) {
                        HTTPWebRequest.AddressAdd(mContext, jsonRequestString, AppConstants.APICode
                                        .ADDRESS_ADD_EDIT, AddUpdateAddressActivity.this,
                                getSupportFragmentManager());
                    } else {

                        HTTPWebRequest.AddressEdit(mContext, jsonRequestString, AppConstants.APICode
                                        .ADDRESS_ADD_EDIT, AddUpdateAddressActivity.this,
                                getSupportFragmentManager());
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }


//                this.finish();
        }
    }


    private void disableErrorMessage() {
        fullNameInputLayout.setErrorEnabled(false);
        emailInputLayout.setErrorEnabled(false);
        addressLine1InputLayout.setErrorEnabled(false);
        addressLine2InputLayout.setErrorEnabled(false);
        stateInputLayout.setErrorEnabled(false);
        cityInputLayout.setErrorEnabled(false);
        pincodeInputLayout.setErrorEnabled(false);
        additionalDetailsInputLayout.setErrorEnabled(false);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        address = null;
        this.finish();
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.ADDRESS_ADD_EDIT:
                address = null;
                Type type = new TypeToken<BaseResponse<List<Address>>>(){}.getType();
                BaseResponse<List<Address>> baseResponse = new Gson().fromJson(response, type);
                Utility.toastMessage(mContext, baseResponse.getMessage());
                if (baseResponse.getStatus() == 1) {
                    this.finish();
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
}
