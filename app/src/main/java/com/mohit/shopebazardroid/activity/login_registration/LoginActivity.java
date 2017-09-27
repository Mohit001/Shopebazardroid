package com.mohit.shopebazardroid.activity.login_registration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.RegistrationIntentService;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.gcm.QuickstartPreferences;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.LoginRequest;
import com.mohit.shopebazardroid.model.response.LoginResponse;
import com.mohit.shopebazardroid.model.response.Result;
import com.mohit.shopebazardroid.model.response.Userinfo;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ApiResponse {

    public static String TAG = LoginActivity.class.getSimpleName();
    Context mContext;

    TextInputLayout emailEditText, passwordEditText;
    Button submitButton;
    TextView forgotPasswordTextView, registrationTextView;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private boolean isReceiverRegistered;

    private AppCompatCheckBox chk_remember_me;
    int themeCode;

    String orderid;
    TextView poweredbyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orderid = getIntent().getStringExtra("notification");
        if (MyApplication.preferenceGetBoolean(AppConstants.SharedPreferenceKeys.IS_LOGGED_IN, false)) {

                startActivity(new Intent(this, NavigationDrawerActivity.class));
                this.finish();

        }

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mContext = this;

        emailEditText = (TextInputLayout) findViewById(R.id.email_inputlayout_txt);
        emailEditText.setTypeface(SplashActivity.opensans_regular);

        passwordEditText = (TextInputLayout) findViewById(R.id.password_inputlayout_txt);
        passwordEditText.setTypeface(SplashActivity.opensans_regular);

        submitButton = (Button) findViewById(R.id.submit_btn);
        submitButton.setTypeface(SplashActivity.opensans_regular);

        forgotPasswordTextView = (TextView) findViewById(R.id.forgot_password_lbl);
        forgotPasswordTextView.setTypeface(SplashActivity.opensans_regular);

        registrationTextView = (TextView) findViewById(R.id.registration_lbl);
        registrationTextView.setTypeface(SplashActivity.opensans_regular);

        chk_remember_me = (AppCompatCheckBox) findViewById(R.id.chk_remember_me);
        chk_remember_me.setTypeface(SplashActivity.opensans_regular);
        chk_remember_me.setChecked(false);
//        MyApplication.preferencePutBooleanRemember(AppConstants.SharedPreferenceKeys.IS_REMEMBER_ME, false);

//        poweredbyTextView = Utility.bottomTextview(mContext, findViewById(android.R.id.content));

        if (MyApplication.preferenceGetBooleanRemember(AppConstants.SharedPreferenceKeys.IS_REMEMBER_ME, false)) {

            emailEditText.getEditText().setText(MyApplication.preferenceGetStringRemember(AppConstants.SharedPreferenceKeys.EMAIL_Address, ""));
            passwordEditText.getEditText().setText(MyApplication.preferenceGetStringRemember(AppConstants.SharedPreferenceKeys.PASSWORD, ""));
            chk_remember_me.setChecked(true);
        } else {
            chk_remember_me.setChecked(false);
        }

        chk_remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    MyApplication.preferencePutBooleanRemember(AppConstants.SharedPreferenceKeys.IS_REMEMBER_ME, true);
                    MyApplication.preferencePutStringRemember(AppConstants.SharedPreferenceKeys.EMAIL_Address, emailEditText.getEditText().getText().toString().trim());
                    MyApplication.preferencePutStringRemember(AppConstants.SharedPreferenceKeys.PASSWORD, passwordEditText.getEditText().getText().toString().trim());
                } else {
                    MyApplication.preferencePutBooleanRemember(AppConstants.SharedPreferenceKeys.IS_REMEMBER_ME, false);
                }

            }
        });

        forgotPasswordTextView.setOnClickListener(this);
        registrationTextView.setOnClickListener(this);
        submitButton.setOnClickListener(this);


        // get device token by register device from gcm server
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    Log.d(TAG, getString(R.string.gcm_send_message));
                } else {
                    Log.d(TAG, getString(R.string.token_error_message));
                }
            }
        };


        // Registering BroadcastReceiver
        registerReceiver();

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }



//        emailEditText.getEditText().setBackgroundResource(R.drawable.theme3_edit_text_holo_light);
//        passwordEditText.getEditText().setBackgroundResource(R.drawable.theme3_edit_text_holo_light);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_btn:
                String emailString = emailEditText.getEditText().getText().toString().trim();
                if (!Utility.isValidEmail(emailString)) {
//                    emailEditText.setErrorEnabled(true);
//                    emailEditText.setError(getString(R.string.empty_email));
                    Utility.toastMessage(mContext, R.string.empty_email);
                    return;
                }

                String passwordString = passwordEditText.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(passwordString)) {
//                    passwordEditText.setErrorEnabled(true);
//                    passwordEditText.setError(getString(R.string.empty_password));
                    Utility.toastMessage(mContext, R.string.empty_password);
                    Log.d(TAG, "Please enter password");
                    return;
                }

                if (passwordString.length() < 6) {
//                    passwordEditText.setErrorEnabled(true);
//                    passwordEditText.setError("password length atleast 6 character");
                    Utility.toastMessage(mContext, "password length atleast 6 character");
                    return;
                }

                // remove error text below edittext
//                emailEditText.setErrorEnabled(false);
//                passwordEditText.setErrorEnabled(false);


                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setEmail(emailString);
                loginRequest.setPassword(passwordString);
                loginRequest.setDevice_type("1");
                loginRequest.setDevice_id(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.GCM_TOKEN, FirebaseInstanceId.getInstance().getToken()));
                loginRequest.setShoppingCartID(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, ""));
                // network call
                HTTPWebRequest.Login(mContext, loginRequest, AppConstants.APICode.LOGIN, this, getSupportFragmentManager());
                break;
            case R.id.forgot_password_lbl:
//                Log.d(TAG, "Forgot password click");
                startActivity(new Intent(mContext, ForgotPasswordActivity.class));
                break;
            case R.id.registration_lbl:
//                Log.d(TAG, "Forgot registration click");
                RegistrationActivity.isUpdate = false;
                Intent intent = new Intent(mContext, RegistrationActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        Gson gson = new Gson();
//        Log.d(TAG, response);
        switch (apiCode) {
            case AppConstants.APICode.LOGIN:
                if (response == null) {
                    Utility.toastMessage(mContext, R.string.host_not_reachable);
                    return;
                }

                try {

                    LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);

                    if (loginResponse.getStatus().equalsIgnoreCase("success")) {
                        Result result = loginResponse.getResult();
                        Utility.toastMessage(mContext, result.getMessage());
                        Userinfo userinfo = result.getUserinfo();
                        Log.d(TAG, "User Id==" + userinfo.getEntity_id());
                        Log.d(TAG, "ShoppingCart Id==" + userinfo.getShoppingcartid());
                        MyApplication.preferencePutBoolean(AppConstants.SharedPreferenceKeys.IS_LOGGED_IN, true);
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.USER_ID, userinfo.getEntity_id());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.EMAIL, userinfo.getEmail());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.NAME, userinfo.getFirstname());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.SESSIONID, userinfo.getSessionId());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID, String.valueOf(userinfo.getShoppingcartid()));
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.IS_NOTIFICATION, userinfo.getIs_notification());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "" + userinfo.getItems_count());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, "" + userinfo.getItems_qty());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, getString(R.string.rupee_sign));
                        MyApplication.preferencePutFloat(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 1);
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.BASECURRENCY_CODE, "USD");

                        setUserLoggedIn();
                        if(getIs_login_compulsory() == 1)
                            startActivity(new Intent(mContext, NavigationDrawerActivity.class));
                        if(getIntent().hasExtra("addWishlist"))
                            setResult(RESULT_OK, getIntent());
                        this.finish();

                    } else if (loginResponse.getStatus().equalsIgnoreCase("fail")) {
                        Result result = loginResponse.getResult();
                        Utility.toastMessage(mContext, result.getMessage());
                    }
                } catch (NullPointerException e) {
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

    // ------------ gcm register method -----------
    private void registerReceiver() {
        if (!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
