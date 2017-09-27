package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.RatingVotes;
import com.mohit.shopebazardroid.model.response.ReviewList;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;


public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder> {

    private static String LOG_TAG = "ReviewListAdapter";
    private ArrayList<ReviewList> mDataset;

    private ArrayList<RatingVotes> votes;

    Context mContext;

    public ReviewListAdapter(Context context, ArrayList<ReviewList> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_review_list, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);


        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView txt_review, txt_lbl_review_by, txt_username, txt_posted_date;// txt_title;
        //RatingBar ratingBar;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_review = (TextView) itemView.findViewById(R.id.txt_review);
            txt_review.setTypeface(SplashActivity.opensans_bold);
            txt_lbl_review_by = (TextView) itemView.findViewById(R.id.txt_lbl_review_by);
            txt_lbl_review_by.setTypeface(SplashActivity.opensans_regular);
            txt_username = (TextView) itemView.findViewById(R.id.txt_username);
            txt_username.setTypeface(SplashActivity.opensans_regular);
            //txt_title = (TextView) itemView.findViewById(txt_title);
            //txt_title.setTypeface(SplashActivity.opensans_bold);

            txt_posted_date = (TextView) itemView.findViewById(R.id.txt_posted_date);
            txt_posted_date.setTypeface(SplashActivity.opensans_regular);

            //ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_dynamic);

        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.txt_review.setText(mDataset.get(position).getTitle());
        holder.txt_username.setText(mDataset.get(position).getNickname());

        String date = mDataset.get(position).getCreated_at();

        String convert_date = Utility.convertToAPIFormat(date);

        Log.e("TAG", "onBindViewHolder: " + convert_date);

        holder.txt_posted_date.setText(mDataset.get(position).getDetail() + " (Posted on " + convert_date + " )");

//        votes = mDataset.get(position).getRating_votes();

        LinearLayout linearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 0; i < mDataset.get(position).getRating_votes().size(); i++) {

            LinearLayout innerLayout = new LinearLayout(mContext);
            LinearLayout.LayoutParams innerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            innerLayout.setLayoutParams(innerParams);
            innerLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView = new TextView(mContext);
            textView.setText(mDataset.get(position).getRating_votes().get(i).getRating_code());
            textView.setTypeface(SplashActivity.opensans_bold);
            textView.setId(i);
            textView.setPadding(0, 0, 5, 0);
            LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(textParams);
            innerLayout.addView(textView);

            RatingBar ratingBar = new RatingBar(mContext, null, android.R.attr.ratingBarStyleSmall);
            LinearLayout.LayoutParams ratingParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ratingBar.setLayoutParams(ratingParams);
            ratingBar.setId(i);
            ratingBar.setRating(Float.parseFloat(mDataset.get(position).getRating_votes().get(i).getValue()));
            innerLayout.addView(ratingBar);

            linearLayout.addView(innerLayout);

            //holder.txt_title.setText(mDataset.get(position).getRating_votes().get(position).getRating_code());
            //holder.ratingBar.setRating(Float.parseFloat(mDataset.get(position).getRating_votes().get(position).getOption_id()));
        }

        holder.linearLayout.addView(linearLayout);

//        holder.txt_review.setText(mDataset.get(position).getRating_code());
//        Toast.makeText(mContext, "" + mDataset.get(position).getStorename(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
