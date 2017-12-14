package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.Countrylist;

import java.util.ArrayList;

/**
 * Created by msp on 13/5/17.
 */

public class SpinnerCountryAdapter extends ArrayAdapter<Countrylist> {

    private ArrayList<Countrylist> list;
    private Context context;
    private LayoutInflater lInflater;

    public SpinnerCountryAdapter(Context context, ArrayList<Countrylist> list) {

        super(context, R.layout.custom_spinner, list);
        this.list = list;
        this.context = context;
        lInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {


        View view = convertView;

        if (view == null) {
            view = lInflater.inflate(R.layout.custom_spinner, parent, false);
        }

        TextView txt = (TextView) view.findViewById(R.id.txt_spn_title);
        txt.setTypeface(SplashActivity.opensans_regular);
        txt.setText(list.get(position).getValue());

        if (position == 0) {
            txt.setVisibility(View.GONE);
        } else {
            txt.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public View getView(int position, View v, ViewGroup viewgroup) {

        View view = v;
        if (view == null) {
            view = lInflater.inflate(R.layout.custom_spinner, viewgroup, false);
        }

        TextView txt = (TextView) view.findViewById(R.id.txt_spn_title);
        txt.setTypeface(SplashActivity.opensans_regular);
        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.dropdown, 0);
        txt.setText(list.get(position).getValue());

        return view;
    }

}
