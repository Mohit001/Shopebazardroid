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
import com.mohit.shopebazardroid.model.response.OrderTotals;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.ArrayList;

public class CartTotalPricesAdapter extends RecyclerView.Adapter<CartTotalPricesAdapter.MyViewHolder> {

    private static String LOG_TAG = "CartTotalAmountAdapter";
    private ArrayList<OrderTotals> mDataset;
    Context mContext;
    double amount = 0;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    public CartTotalPricesAdapter(Context context, ArrayList<OrderTotals> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_total_amount, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_name, txt_amount;

        public MyViewHolder(View itemView) {
            super(itemView);


            baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));

            baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_RATE, 1);

            txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            txt_name.setTypeface(SplashActivity.opensans_regular);

            txt_amount = (TextView) itemView.findViewById(R.id.txt_amount);
            txt_amount.setTypeface(SplashActivity.opensans_regular);

        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txt_name.setText(mDataset.get(position).getTitle());

        amount = mDataset.get(position).getAmount();
        float tempAmount = (float) (/*baseCurrencyValue **/ amount);
        holder.txt_amount.setText(baseCurrencyCode + String.format("%.2f", tempAmount));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
