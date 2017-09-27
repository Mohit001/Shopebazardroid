package com.mohit.shopebazardroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.FeedbackRequest;
import com.mohit.shopebazardroid.model.response.LoginResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

/**
 * Created by msp on 27/7/16.
 */
public class FeedbackFragment extends BaseFragment implements View.OnClickListener, ApiResponse {

    public static String TAG = FeedbackFragment.class.getSimpleName();
    Context mContext;

    TextInputLayout nameTextInputLayout, emailInputLayout;
    TextInputLayout subjectTextInputLayout, feedbackInputLayout;
    AppCompatButton submitButton;

    AppCompatEditText feedback_name_txt,feedback_email_txt,feedback_subject_txt,feedback_content_txt;

    boolean isLogin = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        nameTextInputLayout = (TextInputLayout) view.findViewById(R.id.feedback_name_inputlayout);
        nameTextInputLayout.setTypeface(SplashActivity.opensans_regular);

        emailInputLayout = (TextInputLayout) view.findViewById(R.id.feedback_email_inputlayout);
        emailInputLayout.setTypeface(SplashActivity.opensans_regular);

        subjectTextInputLayout = (TextInputLayout) view.findViewById(R.id
                .feedback_subject_inputlayout);
        subjectTextInputLayout.setTypeface(SplashActivity.opensans_regular);

        feedbackInputLayout = (TextInputLayout) view.findViewById(R.id
                .feedback_content_inputlayout);
        feedbackInputLayout.setTypeface(SplashActivity.opensans_regular);

        submitButton = (AppCompatButton) view.findViewById(R.id.submit_btn);
        submitButton.setTypeface(SplashActivity.opensans_regular);

        feedback_name_txt = (AppCompatEditText) view.findViewById(R.id.feedback_name_txt);
        feedback_name_txt.setTypeface(SplashActivity.opensans_regular);

        feedback_email_txt = (AppCompatEditText) view.findViewById(R.id.feedback_email_txt);
        feedback_email_txt.setTypeface(SplashActivity.opensans_regular);

        feedback_subject_txt = (AppCompatEditText) view.findViewById(R.id.feedback_subject_txt);
        feedback_subject_txt.setTypeface(SplashActivity.opensans_regular);

        feedback_content_txt = (AppCompatEditText) view.findViewById(R.id.feedback_content_txt);
        feedback_content_txt.setTypeface(SplashActivity.opensans_regular);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getContext();
        submitButton.setOnClickListener(this);

        isLogin = MyApplication.preferenceGetBoolean(AppConstants.SharedPreferenceKeys
                .IS_LOGGED_IN, false);
        if (isLogin) {
            nameTextInputLayout.getEditText().setText(MyApplication.preferenceGetString
                    (AppConstants.SharedPreferenceKeys.NAME, ""));
            emailInputLayout.getEditText().setText(MyApplication.preferenceGetString(AppConstants
                    .SharedPreferenceKeys.EMAIL, ""));
        }
    }

    @Override
    public void onClick(View view) {

        nameTextInputLayout.setErrorEnabled(false);
        emailInputLayout.setErrorEnabled(false);
        subjectTextInputLayout.setErrorEnabled(false);
        feedbackInputLayout.setErrorEnabled(false);
        switch (view.getId()) {
            case R.id.submit_btn:
                String nameString = nameTextInputLayout.getEditText().getText().toString().trim();
                String emailString = emailInputLayout.getEditText().getText().toString().trim();
                String subjectString = subjectTextInputLayout.getEditText().getText().toString()
                        .trim();
                String feedbackString = feedbackInputLayout.getEditText().getText().toString()
                        .trim();
                if (TextUtils.isEmpty(nameString)) {
//                    nameTextInputLayout.setErrorEnabled(true);
//                    nameTextInputLayout.setError("Please enter name");
                    Utility.toastMessage(mContext, "Please enter name");
                } else if (TextUtils.isEmpty(emailString)) {
//                    emailInputLayout.setErrorEnabled(true);
//                    emailInputLayout.setError("Please enter your email");
                    Utility.toastMessage(mContext, "Please enter your email");
                } else if (TextUtils.isEmpty(subjectString)) {
//                    subjectTextInputLayout.setErrorEnabled(true);
//                    subjectTextInputLayout.setError("Please enter your Subject");
                    Utility.toastMessage(mContext, "Please enter your Subject");
                } else if (TextUtils.isEmpty(feedbackString)) {
//                    feedbackInputLayout.setErrorEnabled(true);
//                    feedbackInputLayout.setError("Please enter your feedback");
                    Utility.toastMessage(mContext, "Please enter your feedback");
                } else {

                    FeedbackRequest request = new FeedbackRequest();
                    request.setName(nameString);
                    request.setEmail(emailString);
                    request.setSubject(subjectString);
                    request.setMessage(feedbackString);
                    HTTPWebRequest.Feedback(mContext, request, AppConstants.APICode.FEEDBACK,
                            this, getFragmentManager());

                    /*Utility.toastMessage(mContext, "Your feedback submited successfully");
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_frame, new HomeFragment(), HomeFragment.TAG)
                            .commit();*/
                }
                break;
        }
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.FEEDBACK:
                LoginResponse feedbackRespose = new Gson().fromJson(response, LoginResponse.class);

                if(feedbackRespose.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0"))
                {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                Utility.toastMessage(mContext, feedbackRespose.getResult().getMessage());
                if (feedbackRespose.getStatus().equalsIgnoreCase("success")) {
//                    getFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.main_frame, new HomeFragment(), HomeFragment.TAG)
//                            .commit();
                    feedback_subject_txt.setText("");
                    feedback_content_txt.setText("");


//                    Utility.toastMessage(mContext,"Feedback clear!");
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
