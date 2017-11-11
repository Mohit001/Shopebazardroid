package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
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
import com.mohit.shopebazardroid.models.UserCartProduct;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by msp on 23/7/16.
 */
public class OrderReviewCartAdapter extends BaseAdapter {
    Context mContext;
    List<UserCartProduct> arrayList;
    CartListner listner;
    String baseCurrencyCode = "";
    float baseCurrencyValue;
    int themeCode;
    String paymentMethod = "";
    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");

    public OrderReviewCartAdapter(Context mContext, List<UserCartProduct> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        this.baseCurrencyCode = MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
//        this.baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants
// .SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 1);
//        this.baseCurrencyValue = 1;
        this.baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_RATE, 1);

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public UserCartProduct getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        UserCartProduct entity = getItem(position);
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_orderreview_cart,
                    viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        holder.productName.setText(entity.getProduct_name());
        /*if(entity.getDiscount_amount() > 0)
        {
            float tempPrice = (float) entity.getDiscount_amount()*baseCurrencyValue;
            holder.latestPrice.setText(baseCurrencyCode+String.format("%.2f", tempPrice));
            holder.oldPrice.setVisibility(View.VISIBLE);
            holder.oldPrice.setText(baseCurrencyCode+entity.getPrice()*baseCurrencyValue);
            holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint
            .STRIKE_THRU_TEXT_FLAG);
        }
        else
        {
            float tempPrice = (float) (entity.getPrice()*baseCurrencyValue);
            holder.latestPrice.setText(baseCurrencyCode+(String.format("%.2f", tempPrice)));
            holder.oldPrice.setVisibility(View.GONE);
        }*/

//        float tempPrice = (float) (entity.getpricePrice_incl_tax()/* * baseCurrencyValue*/);
//        holder.latestPrice.setText(baseCurrencyCode + (String.format("%.2f", tempPrice)));
//        holder.oldPrice.setVisibility(View.GONE);

            holder.latestPrice.setText(baseCurrencyCode + (String.format("%.2f", Double.parseDouble(entity.getProduct_price()))));
            holder.oldPrice.setVisibility(View.GONE);

        holder.customOption.setVisibility(View.GONE);

        /*if (entity.getCustom_option() != null) {
            CartCustomOption customOption;
            for (int i = 0; i < entity.getCustom_option().size(); i++) {
                customOption = entity.getCustom_option().get(i);
                if (i == (entity.getCustom_option().size() - 1)) {
                    holder.customOption.setText(customOption.getLabel() + ": " + customOption
                            .getValue());
                } else {
                    holder.customOption.setText(customOption.getLabel() + ": " + customOption
                            .getValue() + "\n");
                }
            }
        } else {
            holder.customOption.setVisibility(View.GONE);
        }*/


        holder.quentity.setText(String.valueOf(entity.getProduct_qty()));

//        float tempSubtotal = (float) (entity.getRow_total_incl_tax() /* * baseCurrencyValue*/);
//        holder.subtotal.setText(baseCurrencyCode + String.format("%.2f", tempSubtotal));
        double shippingCharge = entity.getShipping_charge() * entity.getProduct_qty();
        holder.shippingCharge.setText(String.valueOf(shippingCharge));
        holder.subtotal.setText(baseCurrencyCode + String.format("%.2f", Double.parseDouble(entity.getSubtotal())));

        if(entity.getImage_name() != null
                && entity.getImage_name().length() > 0){

            String imgurl = entity.getImage_name();
            if (TextUtils.isEmpty(imgurl)) {
                holder.imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                        R.drawable.ic_placeholder));
            } else {
                Picasso.with(mContext)
                        .load(imagePrefix+imgurl)
                        .error(R.drawable.ic_placeholder)
                        .placeholder(R.drawable.ic_placeholder)
                        .into(holder.imageView);
            }
        } else {
            holder.imageView.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),
                    R.drawable.ic_placeholder));
        }

        holder.cart_custom_option_ll.removeAllViews();

        /*if(entity.getCustom_option() != null){

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
        }

        if(entity.getConfigurable_option() != null){
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
        }*/

        return view;
    }

    public class ViewHolder {
        AppCompatTextView productName;
        AppCompatTextView latestPrice;
        AppCompatTextView oldPrice;
        AppCompatTextView customOption;
        AppCompatTextView quentity;
        AppCompatTextView shippingCharge;
        AppCompatTextView subtotal;
        LinearLayout cart_custom_option_ll;

        AppCompatTextView cart_product_size_lbl, cart_product_quentity_lbl, shipping_charge_lbl,
                cart_product_subtotal_lbl;

        ImageView imageView;

        LinearLayout cart_product_price_ll;
        RelativeLayout cart_product_subtotal_ll;

        String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");

        public ViewHolder(View itemView) {
            productName = (AppCompatTextView) itemView.findViewById(R.id.cart_product_name_content);
            productName.setTypeface(SplashActivity.opensans_regular);

            latestPrice = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_latest_price_content);
            latestPrice.setTypeface(SplashActivity.opensans_regular);

            oldPrice = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_old_price_content);
            oldPrice.setTypeface(SplashActivity.opensans_regular);

            customOption = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_customoption_content);
            customOption.setTypeface(SplashActivity.opensans_regular);

            cart_product_size_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_size_lbl);
            cart_product_size_lbl.setTypeface(SplashActivity.opensans_regular);

            quentity = (AppCompatTextView) itemView.findViewById(R.id
                    .cart_product_quentity_content);
            quentity.setTypeface(SplashActivity.opensans_regular);

            cart_product_quentity_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_quentity_lbl);
            cart_product_quentity_lbl.setTypeface(SplashActivity.opensans_regular);

            shipping_charge_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_shipping_charge_lbl);
            shipping_charge_lbl.setTypeface(SplashActivity.opensans_regular);

            cart_product_subtotal_lbl = (AppCompatTextView) itemView.findViewById(R.id.cart_product_subtotal_lbl);
            cart_product_subtotal_lbl.setTypeface(SplashActivity.opensans_regular);


            shippingCharge = (AppCompatTextView) itemView.findViewById(R.id.cart_product_shipping_charge_content);
            shippingCharge.setTypeface(SplashActivity.opensans_semi_bold);

            subtotal = (AppCompatTextView) itemView.findViewById(R.id.cart_product_subttotal_content);
            subtotal.setTypeface(SplashActivity.opensans_semi_bold);


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
