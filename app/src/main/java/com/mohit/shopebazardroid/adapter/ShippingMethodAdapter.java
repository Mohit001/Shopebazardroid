package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.model.response.ShippingMethod;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.ArrayList;

/**
 * Created by msp on 23/7/16.
 */
public class ShippingMethodAdapter extends RecyclerView.Adapter<ShippingMethodAdapter
        .RecyclerViewHolders> {
    Context mContext;
    ArrayList<ShippingMethod> arrayList;
    AddressListner listner;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    int themeCode;

    public ShippingMethodAdapter(Context mContext, ArrayList<ShippingMethod> arrayList,
                                 AddressListner listner) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 1);
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        AppCompatRadioButton shippingMethodRadioButton;

        TextView txt_shipping_method, txt_shipping_price;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            shippingMethodRadioButton = (AppCompatRadioButton) itemView.findViewById(R.id
                    .shipping_method_radiobutton);
            shippingMethodRadioButton.setTypeface(SplashActivity.opensans_semi_bold);

            txt_shipping_method = (TextView) itemView.findViewById(R.id.txt_shipping_method);
            txt_shipping_method.setTypeface(SplashActivity.opensans_semi_bold);

            txt_shipping_price = (TextView) itemView.findViewById(R.id.txt_shipping_price);
            txt_shipping_price.setTypeface(SplashActivity.opensans_semi_bold);


        }
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_shipping_method, parent, false);

        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        ShippingMethod entity = arrayList.get(position);

        float shippingPrice = baseCurrencyValue * Float.parseFloat(entity.getPrice());

        String shippingmethodName = "<font color=#333333>" + entity.getMethod_title()
                + "</font> <br/> " + "<font color=#960000>" + baseCurrencyCode + String.format("%.2f", shippingPrice) + "</font>";

//        holder.shippingMethodRadioButton.setText(Html.fromHtml(shippingmethodName));
        holder.txt_shipping_method.setText(entity.getMethod_title());
        holder.txt_shipping_price.setText(baseCurrencyCode + String.format("%.2f", shippingPrice));



//        holder.shippingMethodRadioButton.setText(entity.getMethod_title() + "\n"
//                + baseCurrencyCode + String.format("%.2f", shippingPrice));

        holder.shippingMethodRadioButton.setTypeface(SplashActivity.opensans_semi_bold);

        if (entity.isSelected())
            holder.shippingMethodRadioButton.setChecked(true);

        holder.shippingMethodRadioButton.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listner.onSelectionChange(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
