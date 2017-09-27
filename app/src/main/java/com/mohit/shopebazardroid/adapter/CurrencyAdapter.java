package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.CurrencyListner;
import com.mohit.shopebazardroid.model.response.CurrencyEntity;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.ArrayList;

/**
 * Created by msp on 23/7/16.
 */
public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.RecyclerViewHolders> {
    Context mContext;
    ArrayList<CurrencyEntity> arrayList;
    CurrencyListner listner;
    public String currencyCode;
    public CurrencyAdapter(Context mContext, ArrayList<CurrencyEntity> arrayList, CurrencyListner listner) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        currencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder
    {
        AppCompatRadioButton currencyRadioButton;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            currencyRadioButton = (AppCompatRadioButton) itemView.findViewById(R.id.shipping_method_radiobutton);
            currencyRadioButton.setTypeface(SplashActivity.opensans_semi_bold);
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
        CurrencyEntity entity = arrayList.get(position);

        holder.currencyRadioButton.setText(entity.getNametocurrency());
        String currencyCodeValue = entity.getSymbol();
        if(currencyCodeValue.equalsIgnoreCase(currencyCode))
            holder.currencyRadioButton.setChecked(true);
        else
            holder.currencyRadioButton.setChecked(false);

        holder.currencyRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listner.onCurrencyCheckedChange(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
