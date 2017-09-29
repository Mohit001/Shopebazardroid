package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.listener.AddressListner;
import com.mohit.shopebazardroid.models.Address;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.List;

/**
 * Created by msp on 23/7/16.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.RecyclerViewHolders> {
    Context mContext;
    List<Address> arrayList;
    AddressListner listner;
    boolean showRadioSelection = true;
    int billing_shipping;   // 0=non, 1=billing, 2=shipping

    int themeCode;

    public AddressAdapter(Context mContext, List<Address> arrayList, AddressListner listner,
                          boolean showRadioSelection, int billing_shiping) {

        this.mContext = mContext;
        this.arrayList = arrayList;
        this.listner = listner;
        this.showRadioSelection = showRadioSelection;
        this.billing_shipping = billing_shiping;
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {
        AppCompatRadioButton selectedRadioButton;
        AppCompatTextView alias;
        AppCompatTextView homeOfficeNo;
        AppCompatTextView street;
        AppCompatTextView landmark;
        AppCompatTextView city;
        AppCompatTextView address_coma_lbl;
        AppCompatTextView state;
        AppCompatTextView country;
        AppCompatTextView pincode;
        AppCompatTextView email;
        AppCompatTextView mobileno;
        ImageView edit;
        ImageView delete;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            selectedRadioButton = (AppCompatRadioButton) itemView.findViewById(R.id.address_select_radiobutton);
            alias = (AppCompatTextView) itemView.findViewById(R.id.address_title_lbl);
            alias.setTypeface(SplashActivity.opensans_semi_bold);
            homeOfficeNo = (AppCompatTextView) itemView.findViewById(R.id.address_home_office_number_lbl);
            homeOfficeNo.setTypeface(SplashActivity.opensans_regular);
            street = (AppCompatTextView) itemView.findViewById(R.id.address_street_lbl);
            street.setTypeface(SplashActivity.opensans_regular);
            landmark = (AppCompatTextView) itemView.findViewById(R.id.address_landmark_lbl);
            landmark.setTypeface(SplashActivity.opensans_regular);
            city = (AppCompatTextView) itemView.findViewById(R.id.address_city_lbl);
            city.setTypeface(SplashActivity.opensans_regular);
            address_coma_lbl  = (AppCompatTextView) itemView.findViewById(R.id.address_coma_lbl);
            state = (AppCompatTextView) itemView.findViewById(R.id.address_state_lbl);
            state.setTypeface(SplashActivity.opensans_regular);
            country = (AppCompatTextView) itemView.findViewById(R.id.address_country_lbl);
            country.setTypeface(SplashActivity.opensans_regular);
            pincode = (AppCompatTextView) itemView.findViewById(R.id.address_pincode_lbl);
            pincode.setTypeface(SplashActivity.opensans_regular);
            email = (AppCompatTextView) itemView.findViewById(R.id.address_email_lbl);
            email.setTypeface(SplashActivity.opensans_regular);
            mobileno = (AppCompatTextView) itemView.findViewById(R.id.address_mobile_lbl);
            mobileno.setTypeface(SplashActivity.opensans_regular);
            edit = (ImageView) itemView.findViewById(R.id.address_edit_image);
            delete = (ImageView) itemView.findViewById(R.id.address_delete_image);


        }
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.adapter_address, parent, false);

        return new RecyclerViewHolders(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {
        Address entity = arrayList.get(position);

//        holder.selectedRadioButton.setOnCheckedChangeListener(new CompoundButton
//                .OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                listner.onSelectionChange(position);
//                Utility.toastMessage(mContext,"Clicked "+position);
//            }
//        });

        holder.selectedRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onSelectionChange(position);
            }
        });




        if (!TextUtils.isEmpty(entity.getFull_name()))
            holder.alias.setText(entity.getFull_name());
        else
            holder.alias.setVisibility(View.GONE);


        holder.street.setText(entity.getAddress1());
        holder.landmark.setText(entity.getAddress2());
        holder.city.setText(entity.getCity());
        holder.state.setText(entity.getState());
        holder.pincode.setText(entity.getPostcode());
        holder.email.setText(entity.getEmail());


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onEditAddressClick(position);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(mContext);

                // set title
                alertDialogBuilder.setTitle(R.string.app_name);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to delete")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                listner.onDeleteAddressClick(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });


        if (showRadioSelection) {
           /* if (billing_shipping == 1) {
                if (entity.is_default_billing()) {
                    holder.selectedRadioButton.setChecked(true);
                } else {
                    holder.selectedRadioButton.setChecked(false);
                }
            } else {
                if (entity.is_default_shipping()) {
                    holder.selectedRadioButton.setChecked(true);
                } else {
                    holder.selectedRadioButton.setChecked(false);
                }
            }*/
        } else {
            holder.selectedRadioButton.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
