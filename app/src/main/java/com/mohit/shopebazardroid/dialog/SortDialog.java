package com.mohit.shopebazardroid.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.SortListner;
import com.mohit.shopebazardroid.utility.AppConstants;

/**
 * Created by msp on 21/7/16.
 */
public class SortDialog extends DialogFragment implements View.OnClickListener {
    public static String TAG = SortDialog.class.getSimpleName();
    AppCompatTextView ascendingTextView, descendingTextView;
    TextView sort_dialog_title_lbl;
    SortListner listner;

    int themeCode;

    Context mContext;

    public void setListner(SortListner listner)
    {
        this.listner = listner;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_sorting, container, false);

        mContext = getActivity();

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        sort_dialog_title_lbl=(TextView) view.findViewById(R.id.sort_dialog_title_lbl);
        sort_dialog_title_lbl.setTypeface(SplashActivity.opensans_regular);
        ascendingTextView = (AppCompatTextView) view.findViewById(R.id.sort_dialog_ascending_lbl);
        ascendingTextView.setTypeface(SplashActivity.opensans_regular);
        descendingTextView = (AppCompatTextView) view.findViewById(R.id.sort_dialog_descending_lbl);
        descendingTextView.setTypeface(SplashActivity.opensans_regular);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ascendingTextView.setOnClickListener(this);
        descendingTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.sort_dialog_ascending_lbl:
                listner.onAscendingSortSelect();
                break;
            case R.id.sort_dialog_descending_lbl:
                listner.onDescendingSortSelect();
                break;
        }
    }
}
