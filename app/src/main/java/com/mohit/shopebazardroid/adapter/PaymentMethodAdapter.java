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
import com.mohit.shopebazardroid.models.PaymentMethod;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.List;

/**
 * Created by msp on 23/7/16.
 */
public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.RecyclerViewHolders> {
    Context mContext;
    List<PaymentMethod> arrayList;
    AddressListner listner;
    int themeCode;

    public PaymentMethodAdapter(Context mContext, List<PaymentMethod> arrayList, AddressListner listner) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder
    {
        AppCompatRadioButton shippingMethodRadioButton;

        TextView txt_shipping_method;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            shippingMethodRadioButton = (AppCompatRadioButton) itemView.findViewById(R.id.shipping_method_radiobutton);
            shippingMethodRadioButton.setTypeface(SplashActivity.opensans_semi_bold);

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
        PaymentMethod entity = arrayList.get(position);

        holder.shippingMethodRadioButton.setText(entity.getTitle());

        if(entity.isSelected())
            holder.shippingMethodRadioButton.setChecked(true);

        holder.shippingMethodRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
