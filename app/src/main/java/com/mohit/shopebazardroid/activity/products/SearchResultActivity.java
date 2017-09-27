package com.mohit.shopebazardroid.activity.products;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Checkout.CartActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.adapter.ProductGridAdapter;
import com.mohit.shopebazardroid.dialog.FilterDialog;
import com.mohit.shopebazardroid.dialog.SortDialog;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.FilterListner;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.listener.SortListner;
import com.mohit.shopebazardroid.model.request.ProductRequest;
import com.mohit.shopebazardroid.model.response.FilterAttributesOption;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.model.response.ProductFilterAttributes;
import com.mohit.shopebazardroid.model.response.ProductResult;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.R.attr.type;

/**
 * Created by msp on 23/7/16.
 */
public class SearchResultActivity extends BaseActivity implements ApiResponse, SortListner, FilterListner {

    public static String TAG = SearchResultActivity.class.getSimpleName();
    public static String KEY_SEARCH_TITLE = "search_tag";
    public static String KEY_SEARCH_RESULT = "searcg_result";

    Context mContext;
    ArrayList<ProductEntity> arrayList;
    RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    RecyclerView.Adapter mAdapter;

    View cartBadget;

    ProductResult productResult;
    ArrayList<ProductFilterAttributes> filterAttributesArrayList;
    private Menu menu;

    SortDialog sortDialog;
    FilterDialog filterDialog;

    boolean isClearclicked = false;
    boolean noProductFound = false;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "store_id");
    String customerid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "user_id");
    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle(getIntent().getStringExtra(KEY_SEARCH_TITLE));
        productResult = (ProductResult) getIntent().getSerializableExtra(KEY_SEARCH_RESULT);
        arrayList = productResult.getProductlist();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new ProductGridAdapter(mContext, arrayList);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new RecyclerItemclicklistner.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Utility.toastMessage(mContext, "Position : "+position);
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
//                        intent.putExtra(ProductEntity.KEY_OBJECT, arrayList.get(position));
                intent.putExtra("product", arrayList.get(position).getProduct_id());
                startActivity(intent);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.filter_menu, menu);

        MenuItem item = menu.findItem(R.id.action_sorting);

        MenuItem itemFilter = menu.findItem(R.id.action_filter);

        if (type == 2)
            itemFilter.setVisible(false);


        if (ishideprice.equalsIgnoreCase("0")) {
            item.setVisible(true);
            itemFilter.setVisible(true);
        } else {
            item.setVisible(false);
            itemFilter.setVisible(false);
        }

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View cartBadget = inflater.inflate(R.layout.cart_badge_actionbar, null);
        cartBadget.setLayoutParams(new RelativeLayout.LayoutParams(item.getIcon()
                .getIntrinsicWidth() + 100, item.getIcon().getIntrinsicHeight() + 40));

        TextView textView = (TextView) cartBadget.findViewById(R.id.counter);
//        textView.setText("0");
        textView.setText("" + MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
        MenuItem item2 = menu.findItem(R.id.action_cart);
        item2.setActionView(cartBadget);

        cartBadget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MyApplication.preferenceGetBoolean(AppConstants.SharedPreferenceKeys
                        .IS_LOGGED_IN, false)) {
                    startActivity(new Intent(mContext, CartActivity.class));
                } else {
                    finish();
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.action_filter:
                if (filterAttributesArrayList == null || filterAttributesArrayList.size() == 0) {
                    filterAttributesArrayList = new ArrayList<>();
                    if (productResult.getFilter_attributes() != null &&
                            productResult.getFilter_attributes().size() > 0) {

                        filterAttributesArrayList.addAll(productResult.getFilter_attributes());
                    } else {
                        Utility.toastMessage(mContext,  getResources().getString(R.string.no_filter));
                        return false;
                    }
                }


                filterDialog = new FilterDialog();
                filterDialog.setListner(this);
                filterDialog.setupFilterData(filterAttributesArrayList);
                filterDialog.show(getSupportFragmentManager(), FilterDialog.TAG);
                return true;
            case R.id.action_sorting:
                /*if (filterAttributesArrayList == null || filterAttributesArrayList.size() == 0) {
                    filterAttributesArrayList = new ArrayList<>();
                    if (productResult.getFilter_attributes() != null &&
                            productResult.getFilter_attributes().size() > 0) {

                        filterAttributesArrayList.addAll(productResult.getFilter_attributes());
                        return true;
                    } else {
                        Utility.toastMessage(mContext, getResources().getString(R.string.no_sorting));
                        return false;
                    }

                }*/
                if (noProductFound){
                    Utility.toastMessage(mContext, getResources().getString(R.string.no_sorting));
                    return false;
                }
//                if (arrayList.size() > 0) {
                sortDialog = new SortDialog();
                sortDialog.setListner(this);
                sortDialog.show(getSupportFragmentManager(), SortDialog.TAG);
                return true;
//                } else {
//                    Utility.toastMessage(mContext, "no shorting data");
//                    return false;
//                }
            case R.id.action_cart:
//                Utility.toastMessage(mContext, "Cart clicked");
                startActivity(new Intent(mContext, CartActivity.class));
//                startActivity(new Intent(mContext, ActivityStripePaymentGateway.class));
                return true;
        }
    }

    @Override
    public void onAscendingSortSelect() {
        sortDialog.dismiss();
        Log.d(TAG, "onClick: sort");
        Collections.sort(arrayList, new Comparator<ProductEntity>() {
            @Override
            public int compare(ProductEntity lhs, ProductEntity rhs) {
                if (TextUtils.isEmpty(lhs.getSpecial_price())) {
                    if (TextUtils.isEmpty(rhs.getSpecial_price()))
                        return Double.compare(Double.parseDouble(lhs.getPrice()), Double
                                .parseDouble(rhs.getPrice()));
                    else
                        return Double.compare(Double.parseDouble(lhs.getPrice()), Double
                                .parseDouble(rhs.getSpecial_price()));
                } else {
                    if (TextUtils.isEmpty(rhs.getSpecial_price()))
                        return Double.compare(Double.parseDouble(lhs.getSpecial_price()), Double
                                .parseDouble(rhs.getPrice()));
                    else
                        return Double.compare(Double.parseDouble(lhs.getSpecial_price()), Double
                                .parseDouble(rhs.getSpecial_price()));
                }
            }
        });

//        mAdapter.notifyDataSetChanged();

        recyclerView.invalidate();
        mAdapter = new ProductGridAdapter(mContext, arrayList);
        recyclerView.setAdapter(mAdapter);

        Log.d(TAG, "onClick: sort end");
    }

    @Override
    public void onDescendingSortSelect() {

        sortDialog.dismiss();

        Log.d(TAG, "onClick: sort");
        Collections.sort(arrayList, new Comparator<ProductEntity>() {
            @Override
            public int compare(ProductEntity lhs, ProductEntity rhs) {
                if (TextUtils.isEmpty(rhs.getSpecial_price())) {
                    if (TextUtils.isEmpty(lhs.getSpecial_price()))
                        return Double.compare(Double.parseDouble(rhs.getPrice()), Double
                                .parseDouble(lhs.getPrice()));
                    else
                        return Double.compare(Double.parseDouble(rhs.getPrice()), Double
                                .parseDouble(lhs.getSpecial_price()));
                } else {
                    if (TextUtils.isEmpty(lhs.getSpecial_price()))
                        return Double.compare(Double.parseDouble(rhs.getSpecial_price()), Double
                                .parseDouble(lhs.getPrice()));
                    else
                        return Double.compare(Double.parseDouble(rhs.getSpecial_price()), Double
                                .parseDouble(lhs.getSpecial_price()));
                }

            }
        });

//        Collections.reverse(arrayList);
        Log.d(TAG, "onClick: " + new Gson().toJson(arrayList));
//        mAdapter.notifyDataSetChanged();
        recyclerView.invalidate();
        mAdapter = new ProductGridAdapter(mContext, arrayList);
        recyclerView.setAdapter(mAdapter);
        Log.d(TAG, "onClick: sort end");
    }

    @Override
    public void onFilterClearClick() {

//        filterDialog.dismiss();
        filterAttributesArrayList.clear();

        isClearclicked = true;
        /*if (type == 0)
            HTTPWebRequest.OfferOfTheDayShowAll(mContext, AppConstants.APICode.OFFER_OFTHE_DAY,this, getSupportFragmentManager());
        else if (type == 1)
            HTTPWebRequest.TrendingnowShowAll(mContext, AppConstants.APICode.TRENDING_NOW,this, getSupportFragmentManager());
        else
            Utility.toastMessage(mContext, "Invalid arguments.");*/

    }

    @Override
    public void onFilterCancelClick() {
        filterDialog.dismiss();
    }

    @Override
    public void onFilterSubmitClick(ArrayList<ProductFilterAttributes> filterArrayList) {
        filterAttributesArrayList.clear();
        filterAttributesArrayList.addAll(filterArrayList);
        Log.d(TAG, "onFilterSubmitClick: " + filterAttributesArrayList.toString());
        filterDialog.dismiss();

        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject;

        ProductFilterAttributes attributes;
        for (int i = 0; i < filterAttributesArrayList.size(); i++) {
            attributes = filterAttributesArrayList.get(i);
            jsonObject = new JsonObject();

            ArrayList<FilterAttributesOption> optionArrayList = attributes.getOptions();
            FilterAttributesOption option;
            String valueString = "";
            for (int j = 0; j < optionArrayList.size(); j++) {
                option = optionArrayList.get(j);
                if (option.isChecked()) {
                    if (j != 0 && valueString.length() != 0)
                        valueString = valueString + ",";
                    valueString = valueString + option.getValue();
                }
            }


            if (valueString.length() > 0) {
                jsonObject.addProperty("code", attributes.getCode());
                jsonObject.addProperty("value", valueString);
                jsonArray.add(jsonObject);
            }
        }


        ProductRequest request = new ProductRequest();
        if (!isClearclicked) {
            if (jsonArray.size() == 0) {
                Utility.toastMessage(mContext, "Please select filter attributes first");
                return;
            }

            isClearclicked = !isClearclicked;
        }

        if (jsonArray.size() > 0)
            request.setJson_filter(jsonArray.toString());

        request.setPage(String.valueOf(1));
        request.setPagesize(String.valueOf(10000));


        if (type == 0)
            HTTPWebRequest.OfferOfTheDayFilter(mContext, request, AppConstants.APICode.OFFER_OFTHE_DAY, this, getSupportFragmentManager());
        else if (type == 1)
            HTTPWebRequest.TrendingnowFilter(mContext, request, AppConstants.APICode.TRENDING_NOW, this, getSupportFragmentManager());
        else if (type == 2)
            HTTPWebRequest.BestSellingFilter(mContext, request, AppConstants.APICode.BEST_SELLER, this, getSupportFragmentManager());
        else
            Utility.toastMessage(mContext, "Invalid arguments.");

    }

    @Override
    public void onOptionCheckchanged() {

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(int apiCode) {

    }
}
