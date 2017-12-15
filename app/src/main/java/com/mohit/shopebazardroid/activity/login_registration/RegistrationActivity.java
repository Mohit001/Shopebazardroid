package com.mohit.shopebazardroid.activity.login_registration;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.enums.ApiResponseStatus;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.models.Person;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;


public class RegistrationActivity extends BaseActivity implements View.OnClickListener, ApiResponse {

    public static String TAG = RegistrationActivity.class.getSimpleName();
    private Context mContext;

    public static boolean isUpdate = false;

    private String userid;
    private String firstnameString;

    private ImageView btn_back;

    private TextInputLayout firstnameEditText, lastnameEditText,
            emailEditText, passwordEditText, confPasswordEditText, mobileNumberEditText;
    private AppCompatButton submitButton;

    private  Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        mContext = this;

        firstnameEditText = (TextInputLayout) findViewById(R.id.firstname_inputlayout_txt);
        firstnameEditText.setTypeface(SplashActivity.opensans_regular);

        lastnameEditText = (TextInputLayout) findViewById(R.id.lastname_inputlayout_txt);
        lastnameEditText.setTypeface(SplashActivity.opensans_regular);

        emailEditText = (TextInputLayout) findViewById(R.id.email_inputlayout_txt);
        emailEditText.setTypeface(SplashActivity.opensans_regular);

        mobileNumberEditText = (TextInputLayout) findViewById(R.id.mobile_inputlayout_txt);
        mobileNumberEditText.setTypeface(SplashActivity.opensans_regular);


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
            getSupportActionBar().setTitle(R.string.lbl_update_profile);
            userid = getUserid();
            HTTPWebRequest.GetProfile(mContext, userid, AppConstants.APICode.GET_PROFILE, this,
                    getSupportFragmentManager());
            // get profile api call
        } else {
            getSupportActionBar().setTitle(R.string.lbl_sign_up);
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

                String lastnameString = lastnameEditText.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(lastnameString)) {
//                    firstnameEditText.setErrorEnabled(true);
//                    firstnameEditText.setError(getString(R.string.empty_firstname));
                    Utility.toastMessage(mContext, R.string.empty_lastname);
                    Log.d(TAG, "Please enter lastname");
                    return;
                }



                String emailString = emailEditText.getEditText().getText().toString().trim();
                if (!Utility.isValidEmail(emailString)) {
//                    emailEditText.setErrorEnabled(true);
//                    emailEditText.setError(getString(R.string.empty_email));
                    Utility.toastMessage(mContext, R.string.empty_email);
                    Log.d(TAG, "Please enter email");
                    return;
                }

                String mobileNumberString = mobileNumberEditText.getEditText().getText().toString().trim();
                if (mobileNumberString.length() != 10) {
//                    mobileNumberEditText.setErrorEnabled(true);
//                    mobileNumberEditText.setError(getString(R.string.empty_email));
                    Utility.toastMessage(mContext, R.string.invalid_mobile);
                    Log.d(TAG, "Please enter mobile");
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



                if(person == null)
                    person = new Person();
                person.setFname(firstnameString);
                person.setLname(lastnameString);
                person.setEmail(emailString);
                person.setPassword(passwordString);
                person.setPhone(mobileNumberString);




                String jsonRequest = new Gson().toJson(person);
                if (!isUpdate) {

                    HTTPWebRequest.Registration(mContext, jsonRequest, AppConstants.APICode
                            .REGISTRATION, this, getSupportFragmentManager());
                } else {

                    HTTPWebRequest.UpdateProfile(mContext, jsonRequest, AppConstants.APICode
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

                Type type = new TypeToken<BaseResponse<Person>>(){}.getType();
                BaseResponse<Person> baseResponse = new Gson().fromJson(response, type);
                Toast.makeText(mContext, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(baseResponse.getStatus() == 1){
                    Person user = baseResponse.getInfo();
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.USER_ID, String.valueOf(user.getUser_id()));

                    String fullname = user.getFname() + " "+ user.getLname();
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.NAME, fullname);
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.EMAIL, user.getEmail());

                    setUserLoggedIn();

                    this.finish();
                }
                break;
            case AppConstants.APICode.GET_PROFILE:

                Type profileType = new TypeToken<BaseResponse<Person>>(){}.getType();
                BaseResponse<Person> getProfileResponse = new Gson().fromJson(response, profileType);
                if(getProfileResponse.getStatus() == 1){
                    person = getProfileResponse.getInfo();
                    emailEditText.getEditText().setText(person.getEmail());
                    passwordEditText.getEditText().setText(person.getPassword());
                    confPasswordEditText.getEditText().setText(person.getPassword());

                    firstnameEditText.getEditText().setText(person.getFname());
                    lastnameEditText.getEditText().setText(person.getLname());
                    mobileNumberEditText.getEditText().setText(person.getPhone());
                    //set email non editable
                    emailEditText.setEnabled(false);

                } else {
                    Toast.makeText(mContext, getProfileResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case AppConstants.APICode.UPDATE_PROFILE:
               Type updateProfileType = new TypeToken<BaseResponse<Person>>(){}.getType();
                Gson updateProfileGson = new GsonBuilder().serializeNulls().create();
                BaseResponse<Person> updateProfileResponse = updateProfileGson.fromJson(response, updateProfileType);

                Toast.makeText(mContext, updateProfileResponse.getMessage(), Toast.LENGTH_SHORT).show();

                if (updateProfileResponse.getStatus() == ApiResponseStatus.UPDATE_PROFILE_SUCCESS.getStatus_code()) {
                    Person user = updateProfileResponse.getInfo();
                    firstnameString =  user.getFname() + " " + user.getLname();
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
