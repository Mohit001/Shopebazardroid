package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.RewardHistoryResponse;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.ArrayList;


public class MyRewardsAdapter extends RecyclerView.Adapter<MyRewardsAdapter.MyViewHolder> {

    private static String LOG_TAG = "MyRewardsAdapter";
    private ArrayList<RewardHistoryResponse.Transactions> mDataset;
    Context mContext;

    String prefStorenamee = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_NAME, "store_name");

    public MyRewardsAdapter(Context context, ArrayList<RewardHistoryResponse.Transactions> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_my_rewards, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_date, txt_points, txt_trans_detail, txt_pending;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            txt_date.setTypeface(SplashActivity.opensans_regular);

            txt_points = (TextView) itemView.findViewById(R.id.txt_points);
            txt_points.setTypeface(SplashActivity.opensans_regular);

            txt_trans_detail = (TextView) itemView.findViewById(R.id.txt_trans_detail);
            txt_trans_detail.setTypeface(SplashActivity.opensans_regular);

            txt_pending = (TextView) itemView.findViewById(R.id.txt_pending);
            txt_pending.setTypeface(SplashActivity.opensans_bold);

        }

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txt_date.setText(mDataset.get(position).getTransaction_time());
        holder.txt_trans_detail.setText(Html.fromHtml(mDataset.get(position).getTransaction_detail()));
        holder.txt_points.setText(mDataset.get(position).getAmount());

        String status = mDataset.get(position).getStatus();

        if (status.equalsIgnoreCase("1")) {

            String pending = "<b>" + "Pending" + "</b>";

            holder.txt_date.setText(Html.fromHtml(mDataset.get(position).getTransaction_time() + "\n" + pending));

//            holder.txt_pending.setVisibility(View.VISIBLE);
        } else {
            holder.txt_date.setText(mDataset.get(position).getTransaction_time());
//            holder.txt_pending.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
