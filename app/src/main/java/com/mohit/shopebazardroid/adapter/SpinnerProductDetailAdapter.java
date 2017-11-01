package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;

import java.util.ArrayList;

/**
 * Created by msp on 13/5/17.
 */

public class SpinnerProductDetailAdapter extends ArrayAdapter<String> {

    private ArrayList<String> arrayList;
    private Context context;
    private LayoutInflater lInflater;

    public SpinnerProductDetailAdapter(Context context, ArrayList<String> arrayList) {

        super(context, R.layout.custom_spinner_product_detail, arrayList);
        try {

            this.arrayList = arrayList;
            this.context = context;
            lInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {


        View view = convertView;

        if (view == null) {
            view = lInflater.inflate(R.layout.custom_spinner_product_detail, parent, false);
        }

        ImageView img_dropdown = (ImageView) view.findViewById(R.id.img_dropdown);
        img_dropdown.setVisibility(View.GONE);

        TextView txt = (TextView) view.findViewById(R.id.txt_spn_title);
        txt.setTypeface(SplashActivity.opensans_regular);
        txt.setText(arrayList.get(position));
        txt.setTextColor(context.getResources().getColor(R.color.black));

        if (position == 0) {
            txt.setVisibility(View.GONE);
        } else {
            txt.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public View getView(int position, View v, ViewGroup viewgroup) {

        //------------- Set DropDown Custom OR Configurable --------------
        View view = v;
        if (view == null) {
            view = lInflater.inflate(R.layout.custom_spinner_product_detail, viewgroup, false);
        }

        ImageView img_dropdown = (ImageView) view.findViewById(R.id.img_dropdown);
        img_dropdown.setVisibility(View.VISIBLE);

        TextView txt = (TextView) view.findViewById(R.id.txt_spn_title);
        txt.setTypeface(SplashActivity.opensans_regular);
        txt.setTextColor(context.getResources().getColor(R.color.white));
//        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdown, 0);
        txt.setText(arrayList.get(position));

        return view;
    }

}
