package com.mohit.shopebazardroid.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.HistoryEntity;

/**
 * Created by msp on 21/7/16.
 */
public class TrackingProcessDialog extends DialogFragment implements View.OnClickListener {
    public static String TAG = TrackingProcessDialog.class.getSimpleName();

    Context mContext;

    ImageView img_process;

    HistoryEntity historyEntity;
    String temp_process;

    Button btn_ok;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_tracking_img, container, false);

        mContext = getActivity();

        img_process = (ImageView) view.findViewById(R.id.img_process);
        btn_ok = (Button) view.findViewById(R.id.btn_ok);
        btn_ok.setTypeface(SplashActivity.opensans_regular);
        btn_ok.setOnClickListener(this);

        historyEntity = new HistoryEntity();

        temp_process = getArguments().getString("web_track");
        ;

        Log.e("Temp Process ", temp_process);


        if (temp_process.equalsIgnoreCase("Pending")) {
            img_process.setBackgroundDrawable(getResources().getDrawable(R.drawable.process_pending));
        } else if (temp_process.equalsIgnoreCase("Processing")) {
            img_process.setBackgroundDrawable(getResources().getDrawable(R.drawable.process_processing));
        } else if (temp_process.equalsIgnoreCase("Dispatched")) {
            img_process.setBackgroundDrawable(getResources().getDrawable(R.drawable.process_dispatch));
        } else if (temp_process.equalsIgnoreCase("Completed")) {
            img_process.setBackgroundDrawable(getResources().getDrawable(R.drawable.process_delivered));
        } else if (temp_process.equalsIgnoreCase("Canceled")) {
            img_process.setBackgroundDrawable(getResources().getDrawable(R.drawable.process_cancel));
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                this.dismiss();
                break;
        }
    }
}
