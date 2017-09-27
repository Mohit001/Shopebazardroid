package com.mohit.shopebazardroid.activity.Address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Checkout.CartActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.AddressRequest;
import com.mohit.shopebazardroid.model.response.Address;
import com.mohit.shopebazardroid.model.response.AddressResponse;
import com.mohit.shopebazardroid.model.response.CountryResponse;
import com.mohit.shopebazardroid.model.response.Countrylist;
import com.mohit.shopebazardroid.model.response.StateResponse;
import com.mohit.shopebazardroid.model.response.Statelist;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;


import java.util.ArrayList;

/**
 * Created by msp on 28/7/16.
 */
public class AddUpdateAddressActivity extends BaseActivity implements View.OnClickListener, ApiResponse {

    public static String TAG = AddUpdateAddressActivity.class.getSimpleName();
    Context mContext;

    //public static Address address;
    Address address;
    TextInputLayout aliasInputLayout;
    TextInputLayout homeOfficeNoInputLayout;
    TextInputLayout streetInputLayout;
    TextInputLayout landmarkInputLayout;
    TextInputLayout cityInputLayout;
    TextInputLayout stateInputLayout;
    AppCompatAutoCompleteTextView stateAutoCompleteTextView;
    TextInputLayout countryInputLayout;
    AppCompatAutoCompleteTextView countryAutoCompleteTextView;
    TextInputLayout pincodeInputLayout;
    TextInputLayout emailInputLayout;
    TextInputLayout mobileInputLayout;

    AppCompatButton cancelButton, submitButton;

    ArrayList<Countrylist> countryArrayList;
    String[] countryArray;
    ArrayAdapter<String> countryArrayAdapter;
    int countrySelectedIndex = -1;


    ArrayList<Statelist> stateArrayList;
    String[] stateArray;
    ArrayAdapter<String> stateArrayAdapter;
    int stateSelectedIndex = 0;

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

        aliasInputLayout = (TextInputLayout) findViewById(R.id.address_alias_inputlayout);
        aliasInputLayout.setTypeface(SplashActivity.opensans_regular);

        homeOfficeNoInputLayout = (TextInputLayout) findViewById(R.id
                .address_homeoffice_no_inputlayout);
        homeOfficeNoInputLayout.setTypeface(SplashActivity.opensans_regular);

        streetInputLayout = (TextInputLayout) findViewById(R.id.address_street_inputlayout);
        streetInputLayout.setTypeface(SplashActivity.opensans_regular);

        landmarkInputLayout = (TextInputLayout) findViewById(R.id.address_landmark_inputlayout);
        landmarkInputLayout.setTypeface(SplashActivity.opensans_regular);

        cityInputLayout = (TextInputLayout) findViewById(R.id.address_city_inputlayout);
        cityInputLayout.setTypeface(SplashActivity.opensans_regular);

        stateInputLayout = (TextInputLayout) findViewById(R.id.address_state_inputlayout);
        stateInputLayout.setTypeface(SplashActivity.opensans_regular);

        stateAutoCompleteTextView = (AppCompatAutoCompleteTextView) findViewById(R.id
                .address_state_txt);
        stateAutoCompleteTextView.setTypeface(SplashActivity.opensans_regular);

        countryInputLayout = (TextInputLayout) findViewById(R.id.address_country_inputlayout);
        countryInputLayout.setTypeface(SplashActivity.opensans_regular);

        countryAutoCompleteTextView = (AppCompatAutoCompleteTextView) findViewById(R.id
                .address_country_txt);
        countryAutoCompleteTextView.setTypeface(SplashActivity.opensans_regular);

        pincodeInputLayout = (TextInputLayout) findViewById(R.id.address_pincode_inputlayout);
        pincodeInputLayout.setTypeface(SplashActivity.opensans_regular);

        emailInputLayout = (TextInputLayout) findViewById(R.id.address_email_inputlayout);
        emailInputLayout.setTypeface(SplashActivity.opensans_regular);

        mobileInputLayout = (TextInputLayout) findViewById(R.id.address_mobile_inputlayout);
        mobileInputLayout.setTypeface(SplashActivity.opensans_regular);

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

        countryArrayList = new ArrayList<>();
        countryArray = new String[0];
        stateArrayList = new ArrayList<>();
        stateArray = new String[0];

        countryAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String countrynameString = countryAutoCompleteTextView.getText().toString().trim();
                for (int j = 0; j < countryArray.length; j++) {
                    if (countryArray[j].equalsIgnoreCase(countrynameString)) {
                        countrySelectedIndex = j;
                    }
                }
                stateSelectedIndex = 0;
                stateArrayList.clear();
                stateArray = new String[0];
                HTTPWebRequest.GetState(mContext, String.valueOf(countryArrayList.get
                                (countrySelectedIndex).getCountrycode()), AppConstants.APICode
                                .STATE,
                        AddUpdateAddressActivity.this, getSupportFragmentManager());
            }
        });


        /*stateAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String  statenameString = stateAutoCompleteTextView.getText().toString().trim();
                for (int j = 0; j < stateArray.length; j++) {
                    if(stateArray[j].equalsIgnoreCase(statenameString))
                    {
                        stateSelectedIndex = j;
                    }
                }
            }
        });*/

        try {


            intent = getIntent();
            if (intent.getExtras().getString("key").equals("add")) {
                HTTPWebRequest.GetCountryList(mContext, AppConstants.APICode.COUNTRY, this,
                        getSupportFragmentManager());
            } else {
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
            String[] streetAddress = address.getStreet().split("\n");
            Log.d("streetAddress", "streetAddress==" + streetAddress);
            String street1 = "", street2 = "";
            try {
                street1 = streetAddress[0];
                street2 = streetAddress[1];
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }

            getSupportActionBar().setTitle("Update Address");
            submitButton.setText("UPDATE");
            aliasInputLayout.getEditText().setText(address.getFirstname());
            homeOfficeNoInputLayout.getEditText().setText(street1);
            streetInputLayout.getEditText().setText(street2);
            landmarkInputLayout.getEditText().setText(address.getLandmark());
            cityInputLayout.getEditText().setText(address.getCity());
            stateInputLayout.getEditText().setText(address.getRegion());
//            countryInputLayout.getEditText().setText(address.getCountry());

            pincodeInputLayout.getEditText().setText(address.getPostcode());
            emailInputLayout.getEditText().setText(address.getEmail());
            mobileInputLayout.getEditText().setText(address.getTelephone());

            if (address.is_default_billing()) {
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
            }

        }
        HTTPWebRequest.GetCountryList(mContext, AppConstants.APICode.COUNTRY, this,
                getSupportFragmentManager());

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


                    String aliasString = aliasInputLayout.getEditText().getText().toString().trim();
                    if (TextUtils.isEmpty(aliasString)) {
//                        aliasInputLayout.setErrorEnabled(true);
//                        aliasInputLayout.setError("Please enter alias");
                        Utility.toastMessage(mContext, "Please enter alias");
                        return;
                    }

                    String homeOfficenoString = homeOfficeNoInputLayout.getEditText().getText()
                            .toString().trim();
                    if (TextUtils.isEmpty(homeOfficenoString)) {
//                        homeOfficeNoInputLayout.setErrorEnabled(true);
//                        homeOfficeNoInputLayout.setError("Please enter home/office no");
                        Utility.toastMessage(mContext, "Please enter home/office no");
                        return;
                    }

                    String streetString = streetInputLayout.getEditText().getText().toString()
                            .trim();
                    if (TextUtils.isEmpty(streetString)) {
//                        streetInputLayout.setErrorEnabled(true);
//                        streetInputLayout.setError("Please enter street address");
                        Utility.toastMessage(mContext, "Please enter street address");
                        return;
                    }

                /*String  lanmarkString = landmarkInputLayout.getEditText().getText().toString()
                .trim();
                if(TextUtils.isEmpty(lanmarkString))
                {
                    landmarkInputLayout.setErrorEnabled(true);
                    landmarkInputLayout.setError("Please enter landmark");
                    return;
                }*/

                    String countryString = "";
                    if (countrySelectedIndex >= 0) {
                        countryString = countryArrayList.get(countrySelectedIndex)
                                .getCountrycode();
                        Log.d(TAG, countryString);
                    } else {
                        Utility.toastMessage(mContext, "Please enter valid country");
                        return;
                    }

                    if (TextUtils.isEmpty(countryString)) {
//                        countryInputLayout.setErrorEnabled(true);
//                        countryInputLayout.setError("Please enter country name");
                        Utility.toastMessage(mContext, "Please enter country name");
                        return;
                    }

                    String statenameString = stateAutoCompleteTextView.getText().toString().trim();
                    for (int j = 0; j < stateArray.length; j++) {
                        if (stateArray[j].equalsIgnoreCase(statenameString)) {
                            stateSelectedIndex = j;
                        }
                    }
                    String stateString = "";
                    if (stateArrayList != null && (stateArrayList.size() > 0)) {
                        stateString = stateArrayList.get(stateSelectedIndex).getRegion_id();
                        if (TextUtils.isEmpty(stateString)) {
//                            stateInputLayout.setErrorEnabled(true);
//                            stateInputLayout.setError("Please enter state name");
                            Utility.toastMessage(mContext, "Please enter state name");
                            return;
                        }
                    }


                    String cityString = cityInputLayout.getEditText().getText().toString().trim();
                    if (TextUtils.isEmpty(cityString)) {
//                        cityInputLayout.setErrorEnabled(true);
//                        cityInputLayout.setError("Please enter city name");
                        Utility.toastMessage(mContext, "Please enter city name");
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

                /*String  emailString = emailInputLayout.getEditText().getText().toString().trim();
                if(TextUtils.isEmpty(emailString))
                {
                    emailInputLayout.setErrorEnabled(true);
                    emailInputLayout.setError("Please enter ");
                    return;
                }*/

                    String mobileString = mobileInputLayout.getEditText().getText().toString()
                            .trim();
                    if (TextUtils.isEmpty(mobileString)) {
//                        mobileInputLayout.setErrorEnabled(true);
//                        mobileInputLayout.setError("Please enter ");
                        Utility.toastMessage(mContext, "Please enter mobile number");
                        return;
                    }

                    disableErrorMessage();
                    AddressRequest request = new AddressRequest();
                    request.setUser_id(MyApplication.preferenceGetString(AppConstants
                            .SharedPreferenceKeys.USER_ID, "0"));
                    request.setFirstname(aliasString);
                    request.setLastname(aliasString);
                    request.setStreet(homeOfficenoString);
                    request.setStreet2(streetString);
                    request.setCity(cityString);
                    request.setRegion(statenameString);
                    request.setRegion_id(stateString);
                    request.setCountry_id(countryString);
                    request.setPostcode(pincodeString);
                    request.setTelephone(mobileString);
                    request.setDefaultBillingAddress(billingCheckBox.isChecked() == true ? 1 : 0);
                    request.setDefaultShippingAddress(shippingCheckBox.isChecked() == true ? 1 : 0);
                    if (address != null) {
                        request.setAction(String.valueOf(3));
                        request.setAddressId(address.getCustomer_address_id());
                        HTTPWebRequest.AddressEdit(mContext, request, AppConstants.APICode
                                        .ADDRESS_ADD_EDIT, AddUpdateAddressActivity.this,
                                getSupportFragmentManager());
                    } else {
                        request.setAction(String.valueOf(2));
                        HTTPWebRequest.AddressAdd(mContext, request, AppConstants.APICode
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
        aliasInputLayout.setErrorEnabled(false);
        homeOfficeNoInputLayout.setErrorEnabled(false);
        streetInputLayout.setErrorEnabled(false);
        landmarkInputLayout.setErrorEnabled(false);
        cityInputLayout.setErrorEnabled(false);
        stateInputLayout.setErrorEnabled(false);
        countryInputLayout.setErrorEnabled(false);
        pincodeInputLayout.setErrorEnabled(false);
        emailInputLayout.setErrorEnabled(false);
        mobileInputLayout.setErrorEnabled(false);
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
                AddressResponse addressResponse = new Gson().fromJson(response, AddressResponse
                        .class);
                if (addressResponse.getStatus().equalsIgnoreCase("success")) {
                    Utility.toastMessage(mContext, addressResponse.getResult().getMessage());
                    this.finish();
                }
                break;
            case AppConstants.APICode.COUNTRY:
                try {

                    CountryResponse countryResponse = new Gson().fromJson(response,
                            CountryResponse.class);
                    if (countryResponse.getResult().getCountrylist().size() > 0) {
                        countryArrayList = countryResponse.getResult().getCountrylist();
                        countryArray = new String[countryArrayList.size()];
                        Countrylist countrylist;
                        for (int i = 0; i < countryArrayList.size(); i++) {
                            countrylist = countryArrayList.get(i);
                            countryArray[i] = countrylist.getValue();
                            if (address != null && countrylist.getCountrycode().equalsIgnoreCase
                                    (address.getCountry_id()))
                                countrySelectedIndex = i;
                        }


                        countryArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout
                                .simple_expandable_list_item_1, countryArray);
                        countryAutoCompleteTextView.setAdapter(countryArrayAdapter);
                        if (countrySelectedIndex >= 0) {
                            countryAutoCompleteTextView.setText(countryArray[countrySelectedIndex]);
                            stateArrayList.clear();
                            stateArray = new String[0];
                            HTTPWebRequest.GetState(mContext, String.valueOf(countryArrayList.get
                                            (countrySelectedIndex).getCountrycode()), AppConstants
                                            .APICode.STATE, AddUpdateAddressActivity.this,
                                    getSupportFragmentManager());
                        }

                    } else {
                        Utility.toastMessage(mContext, "Sorry not state found");
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

                break;
            case AppConstants.APICode.STATE:

                try {

                    StateResponse stateResponse = new Gson().fromJson(response, StateResponse
                            .class);
                    if (stateResponse.getResult().getStatelist().size() > 0) {
                        stateArrayList = stateResponse.getResult().getStatelist();
                        Statelist statelist;
                        stateArray = new String[stateArrayList.size()];
                        for (int i = 0; i < stateArrayList.size(); i++) {
                            statelist = stateArrayList.get(i);
                            stateArray[i] = statelist.getValue();
                            if (address != null && statelist.getRegion_id().equalsIgnoreCase
                                    (address.getRegion_id()))
                                stateSelectedIndex = i;
                        }

                        stateArrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout
                                .simple_expandable_list_item_1, stateArray);
                        stateAutoCompleteTextView.setAdapter(stateArrayAdapter);
                        stateAutoCompleteTextView.setText(stateArray[stateSelectedIndex]);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (IndexOutOfBoundsException e) {
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
}
