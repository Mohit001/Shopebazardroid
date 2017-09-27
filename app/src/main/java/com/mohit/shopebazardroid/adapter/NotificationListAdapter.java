package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.NotificationListResponse;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MyViewHolder> {

    private static String LOG_TAG = "NotificationListAdapter";
    private ArrayList<NotificationListResponse.List> mDataset;
    Context mContext;

    String baseCurrencyCode = "";
    float baseCurrencyValue;

    public NotificationListAdapter(Context context, ArrayList<NotificationListResponse.List> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_notification_list, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_name, txt_date;

        public MyViewHolder(View itemView) {
            super(itemView);


            baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));

            baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_RATE, 1);

            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_name.setTypeface(SplashActivity.opensans_regular);

            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_date.setTypeface(SplashActivity.opensans_regular);

        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.setIsRecyclable(false);

        holder.txt_name.setText(mDataset.get(position).getMessage());

        holder.txt_date.setText("Date : " + Utility.convertToCommentFormat(mDataset.get(position).getCreated_date()));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
