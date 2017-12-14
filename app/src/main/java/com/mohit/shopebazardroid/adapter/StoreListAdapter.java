package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.StoreList;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.ArrayList;


public class StoreListAdapter extends RecyclerView.Adapter<StoreListAdapter.MyViewHolder> {

    private static String LOG_TAG = "StoreListAdapter";
    private ArrayList<StoreList> mDataset;
    Context mContext;

    String prefStorenamee = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_NAME, "store_name");

    public StoreListAdapter(Context context, ArrayList<StoreList> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_store_list, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_store_name;
        ImageView img_checked;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_store_name = (TextView) itemView.findViewById(R.id.txt_store_name);
            txt_store_name.setTypeface(SplashActivity.opensans_regular);

            img_checked = (ImageView) itemView.findViewById(R.id.img_checked);

        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txt_store_name.setText(mDataset.get(position).getStorename());

        String storename = mDataset.get(position).getStorename();

        if (storename.equalsIgnoreCase(prefStorenamee)) {
            holder.txt_store_name.setTypeface(SplashActivity.opensans_bold);
            holder.img_checked.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
