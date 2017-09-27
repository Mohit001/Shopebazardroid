package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
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
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by msp on 23/7/16.
 */
public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter
        .RecyclerViewHolders> {
    Context mContext;
    ArrayList<ProductEntity> arrayList;

    TextView latestPriceTextView, oldpriceTextView;

    float baseCurrencyRate;
    String baseCurrencyCode = "";

    int themeCode;
    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public ProductGridAdapter(Context mContext, ArrayList<ProductEntity> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView imagebgTextView;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            int mHeightWidth = (int) NavigationDrawerActivity.width / 2;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                    (mHeightWidth, mHeightWidth);

            baseCurrencyRate = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                    .DISPLAY_CURRENCY_RATE, 1);
            baseCurrencyCode = MyApplication.preferenceGetString(AppConstants
                    .SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
            nameTextView = (TextView) itemView.findViewById(R.id.name_lbl);
            latestPriceTextView = (TextView) itemView.findViewById(R.id.pd_latest_price_lbl);
            oldpriceTextView = (TextView) itemView.findViewById(R.id.pd_old_price_lbl);
            imagebgTextView = (TextView) itemView.findViewById(R.id.productgrid_image_bg);
            imagebgTextView.setLayoutParams(layoutParams);
            imageView = (ImageView) itemView.findViewById(R.id.productgrid_image);
            imageView.setLayoutParams(layoutParams);


            if (ishideprice.equalsIgnoreCase("0")) {
                latestPriceTextView.setVisibility(View.VISIBLE);
                oldpriceTextView.setVisibility(View.VISIBLE);

            }else {

                latestPriceTextView.setVisibility(View.GONE);
                oldpriceTextView.setVisibility(View.GONE);
            }



        }
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_productgrid, parent, false);

        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        ProductEntity entity = arrayList.get(position);

        try {


            holder.nameTextView.setText(entity.getName());
            if (!TextUtils.isEmpty(entity.getThumbnail())) {
                Picasso.with(mContext)
                        .load(imagePrefix+entity.getThumbnail())
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .into(holder.imageView);
            } else {
                holder.imageView.setImageBitmap(BitmapFactory.decodeResource(mContext
                                .getResources(),
                        R.drawable.ic_placeholder));
            }

            if (TextUtils.isEmpty(entity.getSpecial_price())) {
                Float tempLatestPrice = Float.parseFloat(entity.getPrice()) * baseCurrencyRate;
                latestPriceTextView.setText(baseCurrencyCode + " " + (String.format("%.2f",
                        tempLatestPrice)));
                oldpriceTextView.setVisibility(View.GONE);
            } else {

                Float tempLatestPrice = Float.parseFloat(entity.getSpecial_price()) *
                        baseCurrencyRate;
                latestPriceTextView.setText(baseCurrencyCode + " " + (String.format("%.2f",
                        tempLatestPrice)));

                Float tempOldPrice = Float.parseFloat(entity.getPrice()) * baseCurrencyRate;
                oldpriceTextView.setText(baseCurrencyCode + " " + (String.format("%.2f",
                        tempOldPrice)));
                oldpriceTextView.setPaintFlags(oldpriceTextView.getPaintFlags() | Paint
                        .STRIKE_THRU_TEXT_FLAG);
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
