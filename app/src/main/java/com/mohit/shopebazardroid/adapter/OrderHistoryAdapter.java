package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.ReorderListner;
import com.mohit.shopebazardroid.models.InvoiceMaster;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.List;


/**
 * Created by msp on 23/7/16.
 */
public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.RecyclerViewHolders> {
    Context mContext;
    List<InvoiceMaster> arrayList;
    String baseCurrencyCode = "";
    float baseCurrencyValue;

    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    private ReorderListner reorderListner;

    public OrderHistoryAdapter(Context mContext, List<InvoiceMaster> arrayList, ReorderListner reorderListner) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_CODE, mContext.getString(R.string.rupee_sign));
        baseCurrencyCode = TextUtils.isEmpty(baseCurrencyCode)?mContext.getString(R.string.rupee_sign):baseCurrencyCode;

//        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
// .DISPLAY_CURRENCY_RATE, 1);
//        baseCurrencyValue = 1;
        baseCurrencyValue = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys
                .DISPLAY_CURRENCY_RATE, 1);

        this.reorderListner = reorderListner;
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        AppCompatTextView orderno;
        AppCompatTextView date;
        AppCompatTextView amount;
        AppCompatTextView status;
        Button reorderButton;
        LinearLayout detailsLinearLayout;

        AppCompatTextView order_history_orderno_lbl, order_history_orderdate_lbl,
                order_history_orderamount_lbl, order_history_orderstatus_lbl;

        LinearLayout order_history_orderamount_rl;

        public RecyclerViewHolders(View itemView) {
            super(itemView);

            order_history_orderamount_rl = (LinearLayout) itemView.findViewById(R.id.order_history_orderamount_rl);
            if (ishideprice.equalsIgnoreCase("0")) {
                order_history_orderamount_rl.setVisibility(View.VISIBLE);
            } else {
                order_history_orderamount_rl.setVisibility(View.GONE);
            }

            order_history_orderno_lbl = (AppCompatTextView) itemView.findViewById(R.id
                    .order_history_orderno_lbl);
            order_history_orderno_lbl.setTypeface(SplashActivity.opensans_regular);

            orderno = (AppCompatTextView) itemView.findViewById(R.id.order_history_orderno_content);
            orderno.setTypeface(SplashActivity.opensans_regular);

            order_history_orderdate_lbl = (AppCompatTextView) itemView.findViewById(R.id
                    .order_history_orderdate_lbl);
            order_history_orderdate_lbl.setTypeface(SplashActivity.opensans_regular);

            date = (AppCompatTextView) itemView.findViewById(R.id.order_history_orderdate_content);
            date.setTypeface(SplashActivity.opensans_regular);

            order_history_orderamount_lbl = (AppCompatTextView) itemView.findViewById(R.id
                    .order_history_orderamount_lbl);
            order_history_orderamount_lbl.setTypeface(SplashActivity.opensans_regular);

            amount = (AppCompatTextView) itemView.findViewById(R.id
                    .order_history_orderamount_content);
            amount.setTypeface(SplashActivity.opensans_regular);

            order_history_orderstatus_lbl = (AppCompatTextView) itemView.findViewById(R.id
                    .order_history_orderstatus_lbl);
            order_history_orderstatus_lbl.setTypeface(SplashActivity.opensans_regular);

            status = (AppCompatTextView) itemView.findViewById(R.id
                    .order_history_orderstatus_content);
            status.setTypeface(SplashActivity.opensans_regular);

            reorderButton = (Button) itemView.findViewById(R.id.reorder_button);
            detailsLinearLayout = (LinearLayout)  itemView.findViewById(R.id.product_details_layout);

        }
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_order_history, parent, false);

        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, final int position) {
        final InvoiceMaster entity = arrayList.get(position);

        holder.orderno.setText(String.valueOf(entity.getInvoice_id()));

//        holder.date.setVisibility(View.GONE);
        holder.date.setText(Utility.convertToNotDateFormat(entity.getCreate_date()));

        holder.amount.setText(baseCurrencyCode + entity.getGrand_total());
        holder.status.setText(entity.getOrder_status());
        holder.reorderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reorderListner != null){
                    reorderListner.orReorderClick(entity.getInvoice_id());
                }
            }
        });

        holder.detailsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reorderListner != null){
                    reorderListner.onDetailsClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
