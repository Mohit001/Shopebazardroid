package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class Home_BestSellersAdapter extends RecyclerView.Adapter<Home_BestSellersAdapter.MyViewHolder> {

    public static String TAG = Home_BestSellersAdapter.class.getSimpleName();
    Context mContext;
    ArrayList<ProductEntity> arrayList;

    float baseCurrencyRate;

    String baseCurrencyCode = "";
    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public Home_BestSellersAdapter(Context mContext, ArrayList<ProductEntity> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_home_offeroftheday, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);

        return holder;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView productgrid_image;
        TextView name_lbl, pd_latest_price_lbl, pd_old_price_lbl;

        public MyViewHolder(View itemView) {
            super(itemView);

            baseCurrencyRate = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_RATE, 1);
            baseCurrencyCode = MyApplication.preferenceGetString(AppConstants
                    .SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));


//            mImageView = (ImageView) itemView.findViewById(R.id.adp_oftd_image);
//
//            adp_oftd_lable = (TextView) itemView.findViewById(R.id.adp_oftd_lable);
//            adp_oftd_lable.setTypeface(SplashActivity.opensans_regular);
//
//            adp_latest_price = (TextView) itemView.findViewById(R.id.adp_latest_price);
//            adp_latest_price.setTypeface(SplashActivity.opensans_regular);
//
//            adp_old_price = (TextView) itemView.findViewById(R.id.adp_old_price);
//            adp_old_price.setTypeface(SplashActivity.opensans_regular);

            productgrid_image = (ImageView) itemView.findViewById(R.id.productgrid_image);
            name_lbl = (TextView) itemView.findViewById(R.id.name_lbl);
            pd_latest_price_lbl = (TextView) itemView.findViewById(R.id.pd_latest_price_lbl);
            pd_old_price_lbl = (TextView) itemView.findViewById(R.id.pd_old_price_lbl);
            pd_old_price_lbl.setVisibility(View.VISIBLE);

            if (ishideprice.equalsIgnoreCase("0")) {
                name_lbl.setVisibility(View.VISIBLE);
                pd_latest_price_lbl.setVisibility(View.VISIBLE);
                pd_old_price_lbl.setVisibility(View.VISIBLE);
            } else {
                name_lbl.setVisibility(View.GONE);
                pd_latest_price_lbl.setVisibility(View.GONE);
                pd_old_price_lbl.setVisibility(View.GONE);
            }

        }

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ProductEntity entity = arrayList.get(position);

        holder.name_lbl.setText(entity.getName());

//        holder.adp_oftd_lable.setText(entity.getName());
//        holder.adp_latest_price.setText(baseCurrencyCode + " " + entity.getSpecial_price());
//        holder.adp_old_price.setText(baseCurrencyCode + " " + entity.getPrice());
//        holder.adp_old_price.setPaintFlags(holder.adp_old_price.getPaintFlags() | Paint
//                .STRIKE_THRU_TEXT_FLAG);

        if(TextUtils.isEmpty(entity.getPrice()) && TextUtils.isEmpty(entity.getSpecial_price())){
            Log.d(TAG, "price and special proce is empty");
        } else{

            if (TextUtils.isEmpty(entity.getSpecial_price()) ) {
                Float tempLatestPrice = Float.parseFloat(entity.getPrice()) * baseCurrencyRate;
                holder.pd_latest_price_lbl.setText(baseCurrencyCode + " " + (String.format("%.2f",
                        tempLatestPrice)));
                holder.pd_old_price_lbl.setVisibility(View.GONE);

            } else {

                Float tempLatestPrice = Float.parseFloat(entity.getSpecial_price()) * baseCurrencyRate;
                holder.pd_latest_price_lbl.setText(baseCurrencyCode + " " + (String.format("%.2f",
                        tempLatestPrice)));

                Float tempOldPrice = Float.parseFloat(entity.getPrice()) * baseCurrencyRate;
                holder.pd_old_price_lbl.setText(baseCurrencyCode + " " + (String.format("%.2f",
                        tempOldPrice)));
                holder.pd_old_price_lbl.setPaintFlags(holder.pd_old_price_lbl.getPaintFlags() | Paint
                        .STRIKE_THRU_TEXT_FLAG);

            }
        }


//        holder.pd_latest_price_lbl.setText(baseCurrencyCode + " " + entity.getSpecial_price());
//        holder.pd_old_price_lbl.setText(baseCurrencyCode + " " + entity.getPrice());
//        holder.pd_old_price_lbl.setPaintFlags(holder.pd_old_price_lbl.getPaintFlags() | Paint
//                .STRIKE_THRU_TEXT_FLAG);


//        if(!TextUtils.isEmpty(entity.getImageurl()))
        if (!TextUtils.isEmpty(entity.getThumbnail())) {
//            Log.d(TAG, "Position"+position);
//            Log.d(TAG, "getView: "+entity.toString());
            try {

//                Picasso.with(mContext)
//                        .load(entity.getMedia().get(0).getUrl())
//                        .placeholder(R.drawable.ic_placeholder)
//                        .error(R.drawable.ic_placeholder)
//                        .into(holder.mImageView);

                Picasso.with(mContext)
                        .load(imagePrefix+entity.getThumbnail())
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(holder.productgrid_image);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
//            holder.mImageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_placeholder));

            holder.productgrid_image.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_placeholder));

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
