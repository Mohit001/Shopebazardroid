package com.mohit.shopebazardroid.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.model.response.CategoryChildrens;

import java.util.ArrayList;

/**
 * Created by msp on 1/8/16.
 */
public class NavigationDrawerAdapter extends BaseAdapter {

    public static String TAG = NavigationDrawerAdapter.class.getSimpleName();

    Context mContext;
    ArrayList<CategoryChildrens> arrayList;

    public NavigationDrawerAdapter(Context mContext, ArrayList<CategoryChildrens> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public CategoryChildrens getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CategoryChildrens navDrawer = getItem(i);
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_navdrawer, viewGroup, false);
        AppCompatTextView navItemName = (AppCompatTextView) view.findViewById(R.id.adapter_navdrawer_item_lbl);
        navItemName.setTypeface(SplashActivity.montserrat_Regular);
        navItemName.setText(navDrawer.getName());
        return view;
    }
}
