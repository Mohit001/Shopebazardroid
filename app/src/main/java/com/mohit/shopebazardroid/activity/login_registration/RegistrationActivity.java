package com.mohit.shopebazardroid.activity.login_registration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.RegistrationRequest;
import com.mohit.shopebazardroid.model.response.LoginResponse;
import com.mohit.shopebazardroid.model.response.Result;
import com.mohit.shopebazardroid.model.response.Userinfo;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;


public class RegistrationActivity extends BaseActivity implements View.OnClickListener, ApiResponse {

    public static String TAG = RegistrationActivity.class.getSimpleName();
    private Context mContext;

    public static boolean isUpdate = false;

    private String userid;
    private String firstnameString;

    private TextView txt_lbl_add_detail_header;
    private ImageView btn_back;

    private TextInputLayout firstnameEditText, middlenameEditText, lastnameEditText,
            emailEditText, passwordEditText, confPasswordEditText;
    private AppCompatButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        mContext = this;

        firstnameEditText = (TextInputLayout) findViewById(R.id.firstname_inputlayout_txt);
        firstnameEditText.setTypeface(SplashActivity.opensans_regular);

        middlenameEditText = (TextInputLayout) findViewById(R.id.middlename_inputlayout_txt);
        middlenameEditText.setTypeface(SplashActivity.opensans_regular);

        lastnameEditText = (TextInputLayout) findViewById(R.id.lastname_inputlayout_txt);
        lastnameEditText.setTypeface(SplashActivity.opensans_regular);

        emailEditText = (TextInputLayout) findViewById(R.id.email_inputlayout_txt);
        emailEditText.setTypeface(SplashActivity.opensans_regular);

        passwordEditText = (TextInputLayout) findViewById(R.id.password_inputlayout_txt);
        passwordEditText.setTypeface(SplashActivity.opensans_regular);

        confPasswordEditText = (TextInputLayout) findViewById(R.id.password_conf_inputlayout_txt);
        confPasswordEditText.setTypeface(SplashActivity.opensans_regular);

        submitButton = (AppCompatButton) findViewById(R.id.submit_btn);
        submitButton.setTypeface(SplashActivity.opensans_regular);

        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        submitButton.setOnClickListener(this);

        if (isUpdate) {
            getSupportActionBar().setTitle("Update Profile");
            userid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.EMAIL, "");
            HTTPWebRequest.GetProfile(mContext, userid, AppConstants.APICode.GET_PROFILE, this,
                    getSupportFragmentManager());
            // get profile api call
        } else {
            getSupportActionBar().setTitle("Registration");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                isUpdate = false;
                NavUtils.navigateUpFromSameTask(this);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        isUpdate = false;
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

            case R.id.submit_btn:
                firstnameString = firstnameEditText.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(firstnameString)) {
//                    firstnameEditText.setErrorEnabled(true);
//                    firstnameEditText.setError(getString(R.string.empty_firstname));
                    Utility.toastMessage(mContext, R.string.empty_firstname);
                    Log.d(TAG, "Please enter firstname");
                    return;
                }


                // middlename and lastname optional
                String middlenameString = middlenameEditText.getEditText().getText().toString()
                        .trim();
                String lastnameString = lastnameEditText.getEditText().getText().toString().trim();


                // middlename and lastname compulsory
                /*String middlenameString = middlenameEditText.getEditText().getText().toString()
                .trim();
                if(TextUtils.isEmpty(middlenameString))
                {
                    middlenameEditText.setErrorEnabled(true);
                    middlenameEditText.setError(getString(R.string.empty_middlename));
//                    Utility.toastMessage(mContext, R.string.empty_middlename);
                    Log.d(TAG, "Please enter middlename");
                    return;
                }

                String lastnameString = lastnameEditText.getEditText().getText().toString().trim();
                if(TextUtils.isEmpty(lastnameString))
                {
                    lastnameEditText.setErrorEnabled(true);
                    lastnameEditText.setError(getString(R.string.empty_lastname));
//                    Utility.toastMessage(mContext, R.string.empty_lastname);
                    Log.d(TAG, "Please enter lastname");
                    return;
                }*/

                String emailString = emailEditText.getEditText().getText().toString().trim();
                if (!Utility.isValidEmail(emailString)) {
//                    emailEditText.setErrorEnabled(true);
//                    emailEditText.setError(getString(R.string.empty_email));
                    Utility.toastMessage(mContext, R.string.empty_email);
                    Log.d(TAG, "Please enter email");
                    return;
                }

                String passwordString = passwordEditText.getEditText().getText().toString().trim();
                if (!isUpdate) {

                    if (TextUtils.isEmpty(passwordString)) {
//                        passwordEditText.setErrorEnabled(true);
//                        passwordEditText.setError(getString(R.string.empty_password));
                        Utility.toastMessage(mContext, R.string.empty_password);
                        Log.d(TAG, "Please enter password");
                        return;
                    }
                    if (passwordString.length() < 6) {
//                        passwordEditText.setErrorEnabled(true);
//                        passwordEditText.setError("password length atleast 6 character");
                        Utility.toastMessage(mContext, "password length atleast 6 character");
                        return;
                    }
                }

                String confpasswordString = confPasswordEditText.getEditText().getText().toString().trim();
                if (!isUpdate) {

                    if (TextUtils.isEmpty(confpasswordString)) {
//                        passwordEditText.setErrorEnabled(true);
//                        passwordEditText.setError(getString(R.string.empty_password));
                        Utility.toastMessage(mContext, R.string.empty_password_conf);
                        Log.d(TAG, "Please enter password");
                        return;
                    }
                    if (passwordString.length() < 6) {
//                        passwordEditText.setErrorEnabled(true);
//                        passwordEditText.setError("password length atleast 6 character");
                        Utility.toastMessage(mContext, "password length atleast 6 character");
                        return;
                    }

                    if(!passwordString.equalsIgnoreCase(confpasswordString))
                    {
                        Utility.toastMessage(mContext, "Password and confirm password are not same");
                        return;
                    }
                }

//                firstnameEditText.setErrorEnabled(false);
//                middlenameEditText.setErrorEnabled(false);
//                lastnameEditText.setErrorEnabled(false);
//                emailEditText.setErrorEnabled(false);
//                passwordEditText.setErrorEnabled(false);

                RegistrationRequest request = new RegistrationRequest();
                request.setFirstname(firstnameString);
                request.setLastname(lastnameString);
                request.setMiddlename(middlenameString);
                request.setEmail(emailString);
                if (!isUpdate) {
                    request.setAction("1");
                    request.setPassword(passwordString);
                    request.setDevice_token(MyApplication.preferenceGetString(AppConstants
                            .SharedPreferenceKeys.GCM_TOKEN, FirebaseInstanceId.getInstance().getToken()));
                    request.setDevice_type("1");
                    HTTPWebRequest.Registration(mContext, request, AppConstants.APICode
                            .REGISTRATION, this, getSupportFragmentManager());
                } else {
                    userid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                            .USER_ID, "0");
                    request.setAction("2");
                    request.setDevice_type("1");
                    request.setUserid(userid);
                    HTTPWebRequest.UpdateProfile(mContext, request, AppConstants.APICode
                            .UPDATE_PROFILE, this, getSupportFragmentManager());
                }
                // network call
                break;

        }
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().serializeNulls().create();
        switch (apiCode) {
            case AppConstants.APICode.REGISTRATION:
                if (response == null) {
                    Utility.toastMessage(mContext, R.string.host_not_reachable);
                    return;
                }

                LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
                Result result = loginResponse.getResult();
                Utility.toastMessage(mContext, result.getMessage());
                if (loginResponse.getStatus().equalsIgnoreCase("success")) {
                    this.finish();
                }
                break;
            case AppConstants.APICode.GET_PROFILE:
                LoginResponse getProfileResponse = new Gson().fromJson(response, LoginResponse.class);

                if(getProfileResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0"))
                {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (getProfileResponse.getStatus().equalsIgnoreCase("success")) {
                    Userinfo userinfo = getProfileResponse.getResult().getUserinfo();
                    if (userinfo != null) {
                        firstnameEditText.getEditText().setText(userinfo.getFirstname());
                        middlenameEditText.getEditText().setText(userinfo.getMiddlename());
                        lastnameEditText.getEditText().setText(userinfo.getLastname());
                        emailEditText.getEditText().setText(userinfo.getEmail());
                        emailEditText.getEditText().setEnabled(false);
                        passwordEditText.getEditText().setVisibility(View.GONE);
                        confPasswordEditText.getEditText().setVisibility(View.GONE);
                    } else {
                        Utility.toastMessage(mContext, R.string.host_not_reachable);
                    }
                } else {
                    Utility.toastMessage(mContext, R.string.host_not_reachable);
                }
                break;
            case AppConstants.APICode.UPDATE_PROFILE:
                LoginResponse updateProfileLoginResponse = new Gson().fromJson(response,
                        LoginResponse.class);

                if(updateProfileLoginResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0"))
                {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }

                if (updateProfileLoginResponse.getStatus().equalsIgnoreCase("success")) {
                    Utility.toastMessage(mContext, updateProfileLoginResponse.getResult()
                            .getMessage());
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.NAME,
                            firstnameString);
                    NavigationDrawerActivity.userName.setText(firstnameString);
                } else {
                    Utility.toastMessage(mContext, R.string.host_not_reachable);
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
