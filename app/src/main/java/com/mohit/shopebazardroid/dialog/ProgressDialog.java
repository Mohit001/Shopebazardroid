package com.mohit.shopebazardroid.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.mohit.shopebazardroid.R;


/**
 * Created by msp on 21/7/16.
 */
public class ProgressDialog extends DialogFragment {
    public static String TAG = ProgressDialog.class.getSimpleName();
    String dialogMessage;
    TextView dialogMessageTextView;

    public void setDialogMessage(String dialogMessage) {
        this.dialogMessage = dialogMessage;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_pregress, container, false);
        dialogMessageTextView = (TextView) view.findViewById(R.id.progress_message_lbl);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            if (!TextUtils.isEmpty(dialogMessage))
                dialogMessageTextView.setText(dialogMessage);
            else
                dialogMessageTextView.setText("Please wait....");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
