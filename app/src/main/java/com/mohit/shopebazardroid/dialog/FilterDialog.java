package com.mohit.shopebazardroid.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ExpandableListView;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.FilterExpandableRecyclerAdapter;
import com.mohit.shopebazardroid.listener.FilterListner;
import com.mohit.shopebazardroid.model.response.FilterAttributesOption;
import com.mohit.shopebazardroid.model.response.ProductFilterAttributes;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.ArrayList;

/**
 * Created by msp on 21/7/16.
 */
public class FilterDialog extends DialogFragment implements View.OnClickListener {
    public static String TAG = FilterDialog.class.getSimpleName();
    private Context mContext;
    private AppCompatButton clearButton, cancelButton, submitButton;
    private RecyclerView mRecyclerView;
    private ExpandableListView filterExpandableListView;
    private FilterListner listner;
    private ArrayList<ProductFilterAttributes> filterArrayList;
    private FilterExpandableRecyclerAdapter mAdapter;

    int themeCode;

    public ArrayList<ProductFilterAttributes> getFilterArrayList() {
        return filterArrayList;
    }

    public void setupFilterData(ArrayList<ProductFilterAttributes> arrayList) {
        this.filterArrayList = new ArrayList<>();
        this.filterArrayList.addAll(arrayList);
        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

    }

    public void setListner(FilterListner listner) {
        this.listner = listner;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_filter, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        mContext = getContext();



        filterExpandableListView = (ExpandableListView) view.findViewById(R.id.filter_expandable_listview);

        clearButton = (AppCompatButton) view.findViewById(R.id.clear_btn);
        clearButton.setTypeface(SplashActivity.opensans_regular);
        cancelButton = (AppCompatButton) view.findViewById(R.id.cancel_btn);
        cancelButton.setTypeface(SplashActivity.opensans_regular);
        submitButton = (AppCompatButton) view.findViewById(R.id.submit_btn);
        submitButton.setTypeface(SplashActivity.opensans_regular);



        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ProductFilterAttributes attributes;
        for (int i = 0; i < filterArrayList.size(); i++) {
            attributes = filterArrayList.get(i);
            ArrayList<FilterAttributesOption> optionArrayList = attributes.getOptions();
            FilterAttributesOption option;
            for (int j = 0; j < optionArrayList.size(); j++) {
                option = optionArrayList.get(j);
                option.setParentIndex(i);
                option.setChildIndex(j);
                optionArrayList.set(j, option);
            }
            attributes.setOptions(optionArrayList);
            filterArrayList.set(i, attributes);
        }

        mAdapter = new FilterExpandableRecyclerAdapter(mContext, filterArrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        clearButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_btn:
                //listner.onFilterClearClick();
                for (int i = 0; i < filterArrayList.size(); i++) {
                    ProductFilterAttributes attributes = filterArrayList.get(i);
                    ArrayList<FilterAttributesOption> optionArrayList = attributes.getOptions();
                    FilterAttributesOption option;
                    for (int j = 0; j < optionArrayList.size(); j++) {
                        option =optionArrayList.get(j);
                        option.setChecked(false);
                        optionArrayList.set(j, option);
                    }
                    attributes.setOptions(optionArrayList);
                    filterArrayList.set(i, attributes);
                }
                mAdapter.notifyDataSetChanged();
                listner.onFilterClearClick();
                break;
            case R.id.cancel_btn:
                listner.onFilterCancelClick();
                break;
            case R.id.submit_btn:
                listner.onFilterSubmitClick(filterArrayList);
                break;
        }
    }
}
