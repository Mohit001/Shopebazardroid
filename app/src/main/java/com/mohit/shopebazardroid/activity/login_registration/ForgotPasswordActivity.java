package com.mohit.shopebazardroid.activity.login_registration;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.models.Person;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;

/**
 * Created by msp on 21/7/16.
 */
public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener,
        ApiResponse {

    public static String TAG = ForgotPasswordActivity.class.getSimpleName();
    Context mContext;

    private TextInputLayout emailTextInputLayout;
    private AppCompatButton submitButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle(R.string.lbl_forgot_password);
        mContext = this;

        emailTextInputLayout = (TextInputLayout) findViewById(R.id.email_inputlayout_txt);
        emailTextInputLayout.setTypeface(SplashActivity.opensans_regular);

        submitButton = (AppCompatButton) findViewById(R.id.submit_btn);
        submitButton.setTypeface(SplashActivity.opensans_regular);
//
        submitButton.setOnClickListener(this);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.submit_btn:
                String emailString = emailTextInputLayout.getEditText().getText().toString().trim();
                if (!Utility.isValidEmail(emailString)) {
//                    emailTextInputLayout.setErrorEnabled(true);
//                    emailTextInputLayout.setError("Please enter valid email id");
                    Utility.toastMessage(mContext, getString(R.string.lbl_invalid_email));
                    return;
                }

//                emailTextInputLayout.setErrorEnabled(false);
//                Utility.toastMessage(mContext, "email sent successfully");
                HTTPWebRequest.ForgotPassword(mContext, emailString, AppConstants.APICode
                        .FORGOT_PASSWORD, this, getSupportFragmentManager());
                break;
        }
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        switch (apiCode) {
            case AppConstants.APICode.FORGOT_PASSWORD:

                if (response == null) {
                    Utility.toastMessage(mContext, R.string.host_not_reachable);
                    return;
                }

                Type type = new TypeToken<BaseResponse<Person>>(){}.getType();
                BaseResponse<Person> forgotPasswordResponse = new Gson().fromJson(response,type);

                Toast.makeText(mContext, forgotPasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                if(forgotPasswordResponse.getStatus() == 1){
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
