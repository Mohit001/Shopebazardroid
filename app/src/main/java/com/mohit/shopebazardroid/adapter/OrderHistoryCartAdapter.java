package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.CartListner;
import com.mohit.shopebazardroid.model.response.AttributesInfo;
import com.mohit.shopebazardroid.model.response.HistoryCartItem;
import com.mohit.shopebazardroid.model.response.HistoryProductOptions;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by msp on 23/7/16.
 */
public class OrderHistoryCartAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<HistoryCartItem> arrayList;
    CartListner listner;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    int themeCode;

    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public OrderHistoryCartAdapter(Context mContext, ArrayList<HistoryCartItem> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        this.baseCurrencyCode = MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
//        this.baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants
// .SharedPreferenceKeys.BASECURRENCY_VALUE, 0);
        this.baseCurrencyValue = 1;
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public HistoryCartItem getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        HistoryCartItem entity = getItem(position);
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_orderreview_cart,
                    viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.productName.setText(entity.getName());
        double discountAmount = Double.parseDouble(entity.getDiscount_amount());
        /*if(discountAmount > 0)
        {
            float tempPrice = (float) discountAmount*baseCurrencyValue;
            holder.latestPrice.setText(baseCurrencyCode+String.format("%.2f", tempPrice));
            holder.oldPrice.setVisibility(View.VISIBLE);
            holder.oldPrice.setText(baseCurrencyCode+Double.parseDouble(entity.getPrice())
            *baseCurrencyValue);
            holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint
            .STRIKE_THRU_TEXT_FLAG);
        }
        else
        {
            float tempPrice = (float) (Double.parseDouble(entity.getPrice())*baseCurrencyValue);
            holder.latestPrice.setText(baseCurrencyCode+(String.format("%.2f", tempPrice)));
            holder.oldPrice.setVisibility(View.GONE);
        }*/

        try {
            float tempPrice = (float) (Double.parseDouble(entity.getPrice_incl_tax()) *
                    baseCurrencyValue);
            holder.latestPrice.setText(baseCurrencyCode + (String.format("%.2f", tempPrice)));
            holder.oldPrice.setVisibility(View.GONE);


        } catch (Exception e) {

        }

        if (entity.getProduct_options().getOptions() != null) {
            ArrayList<HistoryProductOptions.Options> optionsArrayList = entity.getProduct_options
                    ().getOptions();
            HistoryProductOptions.Options options;
            for (int i = 0; i < optionsArrayList.size(); i++) {
                options = optionsArrayList.get(i);
                if (i == (optionsArrayList.size() - 1)) {
                    holder.customOption.setText(options.getLabel() + ": " + options.getValue());
                } else {
                    holder.customOption.setText(options.getLabel() + ": " + options.getValue() +
                            "\n");
                }
            }
        } else {
            holder.customOption.setVisibility(View.GONE);
        }
        holder.quentity.setText(String.format("%.0f", Double.parseDouble(entity.getQty_ordered())));
        float tempSubtotal = (float) (Double.parseDouble(entity.getRow_total()) *
                baseCurrencyValue);
        holder.subtotal.setText(baseCurrencyCode + String.format("%.2f", tempSubtotal));

        if(entity.getMedia() != null
                && entity.getMedia().size() > 0){

            String imgurl = entity.getMedia().get(0).getFile();
            if (TextUtils.isEmpty(imgurl)) {
                holder.imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                        R.drawable.ic_placeholder));
            } else {
                Picasso.with(mContext)
                        .load(imagePrefix+entity.getMedia().get(0).getFile())
                        .error(R.drawable.ic_placeholder)
                        .placeholder(R.drawable.ic_placeholder)
                        .into(holder.imageView);
            }
        } else {
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.ic_placeholder));
        }


        holder.cart_custom_option_ll.removeAllViews();


        if(entity.getProduct_options().getAttributes_info() != null){
            List<AttributesInfo> attributesInfos = entity.getProduct_options().getAttributes_info();

            //configurable option
            for (int i = 0; i < attributesInfos.size(); i++) {
                AttributesInfo info = attributesInfos.get(i);
                LinearLayout horlinearLayout = new LinearLayout(mContext);
                horlinearLayout.setOrientation(LinearLayout.HORIZONTAL);

                AppCompatTextView lblTv = new AppCompatTextView(mContext);
                lblTv.setTextColor(Color.parseColor("#000000"));
                lblTv.setTypeface(SplashActivity.opensans_regular);
                lblTv.setText(info.getLabel() + " : ");
                horlinearLayout.addView(lblTv);
                AppCompatTextView valueTv = new AppCompatTextView(mContext);
                valueTv.setTextColor(Color.parseColor("#000000"));
                valueTv.setText(info.getValue());
                valueTv.setTypeface(SplashActivity.opensans_regular);
                horlinearLayout.addView(valueTv);
                holder.cart_custom_option_ll.addView(horlinearLayout);
            }
        }

        return view;
    }

    public class ViewHolder {
        AppCompatTextView productName;
        AppCompatTextView latestPrice;
        AppCompatTextView oldPrice;
        AppCompatTextView customOption;
        AppCompatTextView quentity;
        AppCompatTextView subtotal;
        LinearLayout cart_custom_option_ll;

        AppCompatTextView cart_product_size_lbl,
                cart_product_quentity_lbl, cart_product_subtotal_lbl;

        ImageView imageView;

        LinearLayout cart_product_price_ll;
        RelativeLayout cart_product_subtotal_ll;

        public ViewHolder(View itemView) {
            productName = (AppCompatTextView) itemView.findViewById(R.id.cart_product_name_content);
            productName.setTypeface(SplashActivity.opensans_regular);
            latestPrice = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_latest_price_content);
            latestPrice.setTypeface(SplashActivity.opensans_regular);
            oldPrice = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_old_price_content);
            oldPrice.setTypeface(SplashActivity.opensans_regular);


            cart_product_size_lbl = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_size_lbl);
            cart_product_size_lbl.setTypeface(SplashActivity.opensans_regular);

            customOption = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_customoption_content);
            customOption.setTypeface(SplashActivity.opensans_regular);

            cart_product_quentity_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_quentity_lbl);
            cart_product_quentity_lbl.setTypeface(SplashActivity.opensans_regular);

            quentity = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_quentity_content);
            quentity.setTypeface(SplashActivity.opensans_regular);

            cart_product_size_lbl = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_size_lbl);
            cart_product_size_lbl.setTypeface(SplashActivity.opensans_regular);

            cart_product_subtotal_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_subtotal_lbl);
            cart_product_subtotal_lbl.setTypeface(SplashActivity.opensans_regular);

            subtotal = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_subttotal_content);
            subtotal.setTypeface(SplashActivity.opensans_regular);

            imageView = (ImageView) itemView.findViewById(R.id.cart_product_image);

            cart_custom_option_ll = (LinearLayout) itemView.findViewById(R.id.cart_custom_option_ll);
            cart_product_price_ll = (LinearLayout) itemView.findViewById(R.id.cart_product_price_ll);
            cart_product_subtotal_ll = (RelativeLayout) itemView.findViewById(R.id.cart_product_subtotal_ll);


            if (ishideprice.equalsIgnoreCase("0")) {
                cart_product_price_ll.setVisibility(View.VISIBLE);
                cart_product_subtotal_ll.setVisibility(View.VISIBLE);
            } else {
                cart_product_price_ll.setVisibility(View.GONE);
                cart_product_subtotal_ll.setVisibility(View.GONE);
            }


        }
    }

}
