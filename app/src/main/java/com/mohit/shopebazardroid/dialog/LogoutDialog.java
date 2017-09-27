package com.mohit.shopebazardroid.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.listener.LogoutListner;
import com.mohit.shopebazardroid.utility.AppConstants;

/**
 * Created by msp on 21/7/16.
 */
public class LogoutDialog extends DialogFragment implements View.OnClickListener {
    public static String TAG = LogoutDialog.class.getSimpleName();
    AppCompatButton cancelButton, submitButton;
    LogoutListner listner;


    public void setListner(LogoutListner listner)
    {
        this.listner = listner;
    }

    Context mContext;

    int themeCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_logout, container, false);

        mContext = getActivity();

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        cancelButton = (AppCompatButton) view.findViewById(R.id.cancel_btn);
        submitButton = (AppCompatButton) view.findViewById(R.id.submit_btn);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cancelButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cancel_btn:
                listner.onLogoutCancel();
                break;
            case R.id.submit_btn:
                listner.onLogoutSubmit();
                break;
        }
    }
}
