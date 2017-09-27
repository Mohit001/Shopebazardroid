package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.CartListner;
import com.mohit.shopebazardroid.model.response.CartItems;
import com.mohit.shopebazardroid.model.response.CartMediaItem;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by msp on 23/7/16.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.RecyclerViewHolders> {
    Context mContext;
    ArrayList<CartItems> arrayList;
    CartListner listner;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    int themeCode;

    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public CartAdapter(Context mContext, ArrayList<CartItems> arrayList, CartListner listner) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        this.baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
//        this.baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 1);
//        this.baseCurrencyValue = 1;
        this.baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_RATE, 1);
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
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
        LinearLayout cart_custom_option_ll;
        RelativeLayout cart_product_subtotal_ll;

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

            qty_minus = (ImageView) itemView.findViewById(R.id.cart_product_minus_image);
            qty_addition = (ImageView) itemView.findViewById(R.id.cart_product_addition_image);
            delete_product = (ImageView) itemView.findViewById(R.id.cart_product_delete_image);
            imageView = (ImageView) itemView.findViewById(R.id.cart_product_image);
            cart_custom_option_ll = (LinearLayout) itemView.findViewById(R.id.cart_custom_option_ll);

            if (ishideprice.equalsIgnoreCase("0")) {
                latestPrice.setVisibility(View.VISIBLE);
                oldPrice.setVisibility(View.VISIBLE);
                subtotal.setVisibility(View.VISIBLE);
                cart_product_subtotal_ll.setVisibility(View.VISIBLE);
            } else {
                latestPrice.setVisibility(View.GONE);
                oldPrice.setVisibility(View.GONE);
                subtotal.setVisibility(View.GONE);
                cart_product_subtotal_ll.setVisibility(View.GONE);
            }


        }
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_cart, parent, false);

        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        CartItems entity = arrayList.get(position);
        holder.productName.setText(entity.getName());
        if(entity.getDiscount_amount() > 0)
        {
            float tempPrice = (float) entity.getDiscount_amount()*baseCurrencyValue;
            holder.latestPrice.setText(baseCurrencyCode+String.format("%.2f", tempPrice));
            holder.oldPrice.setText(baseCurrencyCode+entity.getPrice()*baseCurrencyValue);
            holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.oldPrice.setVisibility(View.VISIBLE);
        }
        else
        {
            float tempPrice = (float) (entity.getPrice()*baseCurrencyValue);
            holder.latestPrice.setText(baseCurrencyCode+(String.format("%.2f", tempPrice)));
            holder.oldPrice.setVisibility(View.GONE);

        }

        holder.latestPrice.setVisibility(View.VISIBLE);
        /*float tempPrice = (float) (entity.getPrice_incl_tax() *//** baseCurrencyValue*//*);
        holder.latestPrice.setText(baseCurrencyCode + (String.format("%.2f", tempPrice)));
        holder.oldPrice.setVisibility(View.GONE);*/


        //holder.size.setText(entity.getSize());
        holder.quentity.setText(String.valueOf(entity.getQty()));
//        float tempSubtotal = (float) (entity.getRow_total_incl_tax()*baseCurrencyValue);
        float tempSubtotal = (float) (entity.getRow_total_incl_tax() /** baseCurrencyValue*/);
        holder.subtotal.setText(baseCurrencyCode + String.format("%.2f", tempSubtotal));
        if (holder.cart_custom_option_ll.getChildCount() > 0)
            holder.cart_custom_option_ll.removeAllViews();

        for (int i = 0; i < entity.getCustom_option().size(); i++) {
            LinearLayout horlinearLayout = new LinearLayout(mContext);
            horlinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            AppCompatTextView lblTv = new AppCompatTextView(mContext);
            lblTv.setTextColor(Color.parseColor("#000000"));
            lblTv.setTypeface(SplashActivity.opensans_regular);
            lblTv.setText(entity.getCustom_option().get(i).getLabel() + " : ");
            horlinearLayout.addView(lblTv);
            AppCompatTextView valueTv = new AppCompatTextView(mContext);
            valueTv.setTextColor(Color.parseColor("#000000"));
            valueTv.setText(entity.getCustom_option().get(i).getValue());
            valueTv.setTypeface(SplashActivity.opensans_regular);
            horlinearLayout.addView(valueTv);
            holder.cart_custom_option_ll.addView(horlinearLayout);
        }
        //configurable option
        for (int i = 0; i < entity.getConfigurable_option().size(); i++) {
            LinearLayout horlinearLayout = new LinearLayout(mContext);
            horlinearLayout.setOrientation(LinearLayout.HORIZONTAL);

            AppCompatTextView lblTv = new AppCompatTextView(mContext);
            lblTv.setTextColor(Color.parseColor("#000000"));
            lblTv.setTypeface(SplashActivity.opensans_regular);
            lblTv.setText(entity.getConfigurable_option().get(i).getLabel() + " : ");
            horlinearLayout.addView(lblTv);
            AppCompatTextView valueTv = new AppCompatTextView(mContext);
            valueTv.setTextColor(Color.parseColor("#000000"));
            valueTv.setText(entity.getConfigurable_option().get(i).getValue());
            valueTv.setTypeface(SplashActivity.opensans_regular);
            horlinearLayout.addView(valueTv);
            holder.cart_custom_option_ll.addView(horlinearLayout);
        }


        ArrayList<CartMediaItem> cartMediaItems = entity.getMedia();

        /*for (int j = 0; j < cartMediaItems.size(); j++) {
            for (int k = 0; k < cartMediaItems.get(j).getTypes().size(); k++) {

                if (cartMediaItems.get(j).getTypes().get(k).equals("thumbnail")) {
                    Picasso.with(mContext)
                            .load(imagePrefix+cartMediaItems.get(j).getUrl())
                            .into(holder.imageView);
                }
            }
        }*/

        if(cartMediaItems != null
                && cartMediaItems.size() > 0
                && !TextUtils.isEmpty(cartMediaItems.get(0).getFile())){
            Picasso.with(mContext)
                    .load(imagePrefix+cartMediaItems.get(0).getFile())
                    .error(R.drawable.ic_placeholder)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(holder.imageView);
        }

        holder.qty_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onQuentityDecreaseClick(position);
            }
        });

        holder.qty_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onQuentityIncreaseClick(position);

            }
        });

        holder.delete_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onProductDeleteClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
