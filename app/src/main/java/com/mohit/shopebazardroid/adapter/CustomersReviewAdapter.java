package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.RatingbarListner;
import com.mohit.shopebazardroid.model.response.CustomerReviewResponse;

import java.util.ArrayList;


public class CustomersReviewAdapter extends RecyclerView.Adapter<CustomersReviewAdapter.MyViewHolder> {

    private static String LOG_TAG = "CustomerReviewAdapter";
    private ArrayList<CustomerReviewResponse.Result> mDataset;
    Context mContext;
    RatingbarListner ratingListener;


    public CustomersReviewAdapter(Context context, ArrayList<CustomerReviewResponse.Result> myDataset, RatingbarListner ratingListener) {
        mContext = context;
        mDataset = myDataset;
        this.ratingListener = ratingListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_customer_review, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);


        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_lbl;
        RatingBar ratingBar;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_lbl = (TextView) itemView.findViewById(R.id.txt_lbl);
            txt_lbl.setTypeface(SplashActivity.opensans_regular);

            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        CustomerReviewResponse.Result ratings = mDataset.get(position);
        ratings.setPosition("0");
        holder.txt_lbl.setText(mDataset.get(position).getRating_code());
//        holder.ratingBar.setRating(Float.parseFloat(ratings.getOptions().get(0).getValue()));
//        holder.ratingBar.setRating(1f);
        holder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                long selectRating = (long) Math.ceil(rating);
                ratingBar.setRating(selectRating);
                ratingListener.onRatingClick((int) selectRating, mDataset.get(position).getRating_code(), position);
            }
        });


//        Toast.makeText(mContext, "" + mDataset.get(position).getStorename(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
