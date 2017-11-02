package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.DeleteWishlistProduct;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.models.Product;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by msp on 23/7/16.
 */
public class MyWishListAdapter extends RecyclerView.Adapter<MyWishListAdapter.RecyclerViewHolders> {
    Context mContext;
    List<Product> arrayList;
    DeleteWishlistProduct listner;
    String baseCurrencyCode = "";
    float baseCurrencyRate;

    int themeCode;

    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    String url = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.PRODUCT_MEDIA_URL, AppConstants.RequestDataKey.image_url);

    public MyWishListAdapter(Context mContext, List<Product> arrayList, DeleteWishlistProduct listner) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        this.baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
//        this.baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 0);
//        this.baseCurrencyValue = 1;
        this.baseCurrencyRate = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 1);
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_cart, parent, false);

        return new RecyclerViewHolders(itemView);
    }


    class RecyclerViewHolders extends RecyclerView.ViewHolder {
        AppCompatTextView productName;
        AppCompatTextView latestPrice;
        AppCompatTextView oldPrice;
        AppCompatTextView size;
        AppCompatTextView quentity;
        AppCompatTextView subtotal;
        AppCompatTextView subtotal_lbl;
        AppCompatTextView cart_product_quentity_lbl;
        ImageView qty_minus;
        ImageView qty_addition;
        ImageView delete_product;
        ImageView imageView;
        LinearLayout cart_custom_option_ll, cart_product_quentity_ll;
        RelativeLayout cart_product_subtotal_ll, rel_item_click;
        RelativeLayout cart_shipping_charge_ll;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            productName = (AppCompatTextView) itemView.findViewById(R.id.cart_product_name_content);
            productName.setTypeface(SplashActivity.opensans_regular);

            latestPrice = (AppCompatTextView) itemView.findViewById(R.id.cart_product_latest_price_content);
            latestPrice.setTypeface(SplashActivity.opensans_regular);

            oldPrice = (AppCompatTextView) itemView.findViewById(R.id.cart_product_old_price_content);
            oldPrice.setTypeface(SplashActivity.opensans_regular);

            size = (AppCompatTextView) itemView.findViewById(R.id.cart_product_size_content);

            cart_product_quentity_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_quentity_lbl);
            cart_product_quentity_lbl.setTypeface(SplashActivity.opensans_regular);

            quentity = (AppCompatTextView) itemView.findViewById(R.id.cart_product_quentity_content);
            quentity.setTypeface(SplashActivity.opensans_regular);

            subtotal_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_subtotal_lbl);
            subtotal_lbl.setTypeface(SplashActivity.opensans_semi_bold);

            subtotal = (AppCompatTextView) itemView.findViewById(R.id.cart_product_subttotal_content);
            subtotal.setTypeface(SplashActivity.opensans_semi_bold);

            cart_product_subtotal_ll = (RelativeLayout) itemView.findViewById(R.id.cart_product_subtotal_ll);
            cart_product_subtotal_ll.setVisibility(View.GONE);

            cart_product_quentity_ll = (LinearLayout) itemView.findViewById(R.id.cart_product_quentity_ll);

            rel_item_click = (RelativeLayout) itemView.findViewById(R.id.rel_item_click);

            qty_minus = (ImageView) itemView.findViewById(R.id.cart_product_minus_image);
            qty_minus.setVisibility(View.GONE);
            qty_addition = (ImageView) itemView.findViewById(R.id.cart_product_addition_image);
            qty_addition.setVisibility(View.GONE);
            delete_product = (ImageView) itemView.findViewById(R.id.cart_product_delete_image);
            imageView = (ImageView) itemView.findViewById(R.id.cart_product_image);
            cart_custom_option_ll = (LinearLayout) itemView.findViewById(R.id.cart_custom_option_ll);
            cart_shipping_charge_ll = (RelativeLayout) itemView.findViewById(R.id.cart_product_shipping_charges_ll);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        final Product entity = arrayList.get(position);
        holder.productName.setText(entity.getPro_name());

        Float tempPrice = Float.parseFloat(entity.getPro_price() /** baseCurrencyValue*/);
        holder.latestPrice.setText(baseCurrencyCode + (String.format("%.2f", tempPrice)));
        holder.oldPrice.setVisibility(View.GONE);

        if (entity.getDiscount_price() == 0) {
            Float tempLatestPrice = Float.parseFloat(entity.getPro_price()) * baseCurrencyRate;
            holder.latestPrice.setText(baseCurrencyCode + " " + (String.format("%.2f",tempLatestPrice)));
            holder.oldPrice.setVisibility(View.GONE);
        } else {

            Float tempLatestPrice = entity.getDiscount_price() * baseCurrencyRate;
            holder.latestPrice.setText(baseCurrencyCode + " " + (String.format("%.2f",tempLatestPrice)));

            Float tempOldPrice = Float.parseFloat(entity.getPro_price()) * baseCurrencyRate;
            holder.oldPrice.setText(baseCurrencyCode + " " + (String.format("%.2f",tempOldPrice)));
            holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.oldPrice.setVisibility(View.VISIBLE);
        }

        holder.quentity.setText("1");
//        holder.quentity.setVisibility(View.GONE);

        /*if (holder.cart_custom_option_ll.getChildCount() > 0)
            holder.cart_custom_option_ll.removeAllViews();
        */
        Picasso.with(mContext)
                .load(url + entity.getPro_image())
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(holder.imageView);

        holder.delete_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.DeleteProduct(String.valueOf(entity.getWishlist_id()));
            }
        });

        holder.rel_item_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.ItemClick(position, String.valueOf(entity.getPro_mst_id()));
            }
        });

        holder.cart_shipping_charge_ll.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
