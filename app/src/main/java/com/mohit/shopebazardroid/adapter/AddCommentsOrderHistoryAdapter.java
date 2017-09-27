package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.HistoryStatus;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


public class AddCommentsOrderHistoryAdapter extends RecyclerView.Adapter<AddCommentsOrderHistoryAdapter.MyViewHolder> {

    private static String LOG_TAG = "AddCommentsOrderHistoryAdapter";
    private ArrayList<HistoryStatus> mDataset;
    Context mContext;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    public AddCommentsOrderHistoryAdapter(Context context, ArrayList<HistoryStatus> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_add_comment_ohd, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_date, txt_comment;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);

            baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));

            baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_RATE, 1);

            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_date.setTypeface(SplashActivity.opensans_regular);

            txt_comment = (TextView) itemView.findViewById(R.id.txt_comment);
//            txt_comment.setTypeface(SplashActivity.opensans_regular);

            view = (View) itemView.findViewById(R.id.view);

        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txt_date.setText(Utility.convertToCommentFormat(mDataset.get(position).getCreated_at()));

        String comment = mDataset.get(position).getComment();

        if (!TextUtils.isEmpty(comment)) {

            holder.txt_comment.setText(Html.fromHtml(comment));
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
