package com.mohit.shopebazardroid.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.utility.AppConstants;

/**
 * Created by msp on 21/7/16.
 */
public class ConfirmDialog extends DialogFragment implements View.OnClickListener {
    public static String TAG = ConfirmDialog.class.getSimpleName();
    AppCompatButton negativeButton, positiveButton;
    AppCompatTextView titleTV,messageTV;
    ConfirmDialogListner listner;
    String titlestr,messagestr,posbtn_name,negbtn_name;

    Context mContext;

    int themeCode;

    public void setListner(ConfirmDialogListner listner,String title,String message,String posbtn_name,String negbtn_name)
    {
        this.listner = listner;
        this.titlestr =title;
        this.messagestr = message;
        this.posbtn_name =posbtn_name;
        this.negbtn_name = negbtn_name;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_confirm, container, false);

        mContext = getActivity();

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        negativeButton = (AppCompatButton) view.findViewById(R.id.negative_btn);
        positiveButton = (AppCompatButton) view.findViewById(R.id.positive_btn);
        titleTV =(AppCompatTextView)view.findViewById(R.id.dialog_title_lbl);
        messageTV =(AppCompatTextView)view.findViewById(R.id.dialog_content_lbl);

        titleTV.setText(titlestr);
        messageTV.setText(messagestr);
        positiveButton.setText(posbtn_name);
        negativeButton.setText(negbtn_name);

        positiveButton.setOnClickListener(this);
        negativeButton.setOnClickListener(this);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.negative_btn:
                listner.onNegativeButtonClick();
                break;
            case R.id.positive_btn:
                listner.onPositiveButtonClick();
                break;
        }
    }
}
