package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.models.Category;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Home_CategoriesGridAdapter extends RecyclerView.Adapter<Home_CategoriesGridAdapter.MyViewHolder> {

    public static String TAG = CategoriesAdapter.class.getSimpleName();
    Context mContext;
    List<Category> arrayList;
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public Home_CategoriesGridAdapter(Context mContext, List<Category> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_home_category_grid, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        ImageView mImageView;
        TextView lableTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mImageView = (ImageView) itemView.findViewById(R.id.adp_oftd_image);
            lableTextView = (TextView) itemView.findViewById(R.id.adp_oftd_lable);

            int viewHeightWidth = (int) NavigationDrawerActivity.width/2;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(viewHeightWidth, viewHeightWidth);
            mImageView.setLayoutParams(layoutParams);
        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Category entity = arrayList.get(position);

        holder.lableTextView.setVisibility(View.VISIBLE);
        holder.lableTextView.setText(entity.getCat_name());

        try {

            if (!TextUtils.isEmpty(entity.getCat_image())) {

                Picasso.with(mContext)
                        .load(imagePrefix+entity.getCat_image())
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(holder.mImageView);
            } else {
                try {
                    holder.mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext
                            .getResources(), R.drawable.ic_placeholder));

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
