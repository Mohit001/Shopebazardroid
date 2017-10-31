package com.mohit.shopebazardroid.activity.offer;

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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Checkout.CartActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.products.ProductDetailActivity;
import com.mohit.shopebazardroid.adapter.ProductGridAdapter;
import com.mohit.shopebazardroid.dialog.FilterDialog;
import com.mohit.shopebazardroid.dialog.SortDialog;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.FilterListner;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.listener.SortListner;
import com.mohit.shopebazardroid.model.request.BestSellingRequest;
import com.mohit.shopebazardroid.model.request.FeatureProductRequest;
import com.mohit.shopebazardroid.model.request.OfferOfTheDayRequest;
import com.mohit.shopebazardroid.model.request.ProductRequest;
import com.mohit.shopebazardroid.model.request.TrendingNowRequest;
import com.mohit.shopebazardroid.model.response.FilterAttributesOption;
import com.mohit.shopebazardroid.model.response.ProductFilterAttributes;
import com.mohit.shopebazardroid.model.response.ProductResult;
import com.mohit.shopebazardroid.models.Product;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by msp on 23/7/16.
 */
public class TrendingSaleShowAllActivity extends BaseActivity implements ApiResponse, View.OnClickListener, SortListner, FilterListner {

    public static String TAG = TrendingSaleShowAllActivity.class.getSimpleName();
    Context mContext;
    ArrayList<Product> arrayList;
    RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    ProductGridAdapter mAdapter;
    //    AnimatedFloatingActionButtonTop sortFAB, filterFAB;
    int type; // 0= offer of the, 1= trending now , 2 = Best Selling

    SortDialog sortDialog;
    FilterDialog filterDialog;

    ProductResult productResult;
    ArrayList<ProductFilterAttributes> filterAttributesArrayList;
    private Menu menu;

    boolean isClearclicked = false;
    boolean noProductFound = false;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "0");
    String customerid = getUserid();
    String root_cat_id = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.ROOT_CAT_ID, "0");
    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");

    private int totalProducts;
    private int pageCount = 0;
    private int filterPageCount = 0;
    private final int pageSize= 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getSupportActionBar().setTitle("Products");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        sortFAB = (AnimatedFloatingActionButtonTop) findViewById(R.id.sorting_fab);
//        filterFAB = (AnimatedFloatingActionButtonTop) findViewById(R.id.filter_fab);

//        sortFAB.setVisibility(View.VISIBLE);
//        filterFAB.setVisibility(View.VISIBLE);
//        sortFAB.attachToRecyclerView(recyclerView);
//        filterFAB.attachToRecyclerView(recyclerView);

//        sortFAB.setOnClickListener(this);
//        filterFAB.setOnClickListener(this);

        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);

        type = getIntent().getIntExtra(AppConstants.INTENTDATA.TYPE, 0);

        recyclerView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new RecyclerItemclicklistner.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ProductDetailActivity.class);
                intent.putExtra(AppConstants.RequestDataKey.PRODUCT, arrayList.get(position));
                startActivity(intent);
            }
        }));

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        loadMoreItems();
    }

    private void setupGrid() {
        /*arrayList = new ArrayList<>();
        Product entity;
        for(int i=0;i<10; i++)
        {
            entity = new Product();
            entity.setImageurl("http://graphicdesignjunction.com/wp-content/uploads/2012/07/vectorgraphic-5.jpg");
            arrayList.add(entity);
        }*/

        if(mAdapter == null || arrayList.size() < pageSize){
            mAdapter = new ProductGridAdapter(this, arrayList);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
                        Utility.toastMessage(mContext, getResources().getString(R.string.no_filter));
                        return false;
                    }
                }


                filterDialog = new FilterDialog();
                filterDialog.setListner(this);
                filterDialog.setupFilterData(filterAttributesArrayList);
                filterDialog.show(getSupportFragmentManager(), FilterDialog.TAG);
                return true;
            case R.id.action_sorting:

                if (noProductFound){
                    Utility.toastMessage(mContext, getResources().getString(R.string.no_sorting));
                    return false;
                }
                sortDialog = new SortDialog();
                sortDialog.setListner(this);
                sortDialog.show(getSupportFragmentManager(), SortDialog.TAG);
                return true;

            case R.id.action_cart:
//                Utility.toastMessage(mContext, "Cart clicked");
                startActivity(new Intent(mContext, CartActivity.class));
//                startActivity(new Intent(mContext, ActivityStripePaymentGateway.class));
                return true;
        }
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {
            case AppConstants.APICode.TRENDING_NOW:
            case AppConstants.APICode.OFFER_OFTHE_DAY:
            case AppConstants.APICode.BEST_SELLER:
            case AppConstants.APICode.FEATURE_PRODUCT:

                isLoading = false;

                if(!TextUtils.isEmpty(response)){

                    Type productListType = new TypeToken<BaseResponse<List<Product>>>(){}.getType();
                    BaseResponse<List<Product>> baseResponse = new Gson().fromJson(response, productListType);

                    if(baseResponse.getInfo().size() == 0){
                        if(arrayList == null)
                            arrayList = new ArrayList<>();

                        arrayList.addAll(baseResponse.getInfo());
                        if(arrayList.size() == totalProducts)
                            isLastPage = true;

                        setupGrid();
                    } else {
//                        Utility.toastMessage(mContext, "No product Found");

                    }

                } else {
                    Toast.makeText(mContext, R.string.host_not_reachable, Toast.LENGTH_SHORT).show();
                    this.finish();
                }
                break;
        }
    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(int apiCode) {

    }

    @Override
    public void onClick(View view) {
      /*  switch (view.getId())
        {
            case R.id.sorting_fab:
                sortDialog = new SortDialog();
                sortDialog.setListner(this);
                sortDialog.show(getSupportFragmentManager(), SortDialog.TAG);
                break;
            case R.id.filter_fab:
                if(filterAttributesArrayList == null || filterAttributesArrayList.size() == 0)
                {
                    filterAttributesArrayList = new ArrayList<>();
                    filterAttributesArrayList.addAll(productResult.getFilter_attributes());
                }
                filterDialog = new FilterDialog();
                filterDialog.setListner(this);
                filterDialog.setupFilterData(filterAttributesArrayList);
                filterDialog.show(getSupportFragmentManager(), FilterDialog.TAG);
                break;
        }*/
    }

    @Override
    public void onAscendingSortSelect() {
        sortDialog.dismiss();
        Log.d(TAG, "onClick: sort");
        Collections.sort(arrayList, new Comparator<Product>() {
            @Override
            public int compare(Product lhs, Product rhs) {
                if (TextUtils.isEmpty(""+lhs.getDiscount_price())) {
                    if (TextUtils.isEmpty(""+rhs.getDiscount_price()))
                        return Double.compare(Double.parseDouble(lhs.getPro_price()), Double
                                .parseDouble(rhs.getPro_price()));
                    else
                        return Double.compare(Double.parseDouble(lhs.getPro_price()), Double
                                .parseDouble(""+rhs.getDiscount_price()));
                } else {
                    if (TextUtils.isEmpty(""+rhs.getDiscount_price()))
                        return Double.compare(Double.parseDouble(""+lhs.getDiscount_price()), Double
                                .parseDouble(rhs.getPro_price()));
                    else
                        return Double.compare(Double.parseDouble(""+lhs.getDiscount_price()), Double
                                .parseDouble(""+rhs.getDiscount_price()));
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
        Collections.sort(arrayList, new Comparator<Product>() {
            @Override
            public int compare(Product lhs, Product rhs) {
                if (TextUtils.isEmpty(""+rhs.getDiscount_price())) {
                    if (TextUtils.isEmpty(""+lhs.getDiscount_price()))
                        return Double.compare(Double.parseDouble(rhs.getPro_price()), Double
                                .parseDouble(lhs.getPro_price()));
                    else
                        return Double.compare(Double.parseDouble(rhs.getPro_price()), Double
                                .parseDouble(""+lhs.getDiscount_price()));
                } else {
                    if (TextUtils.isEmpty(""+lhs.getDiscount_price()))
                        return Double.compare(Double.parseDouble(""+rhs.getDiscount_price()), Double
                                .parseDouble(lhs.getPro_price()));
                    else
                        return Double.compare(Double.parseDouble(""+rhs.getDiscount_price()), Double
                                .parseDouble(""+lhs.getDiscount_price()));
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
        pageCount = 0;
        filterPageCount = 0;

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

        request.setPage(String.valueOf(pageCount));
        request.setPagesize(String.valueOf(pageCount));


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

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = gridLayoutManager.getChildCount();
            int totalItemCount = gridLayoutManager.getItemCount();
            int firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= pageSize) {
                    loadMoreItems();
                }
            }
        }
    };

    private void loadMoreItems() {
        isLoading = true;
        ++pageCount;

        OfferOfTheDayRequest offerOfTheDayRequest = new OfferOfTheDayRequest();
        offerOfTheDayRequest.setStore_id(storeid);
        offerOfTheDayRequest.setCustomer_id(customerid);
        offerOfTheDayRequest.setRoot_category_id(root_cat_id);
        offerOfTheDayRequest.setPage(String.valueOf(pageCount));
        offerOfTheDayRequest.setPagesize(String.valueOf(pageSize));

        TrendingNowRequest trandingRequest = new TrendingNowRequest();
        trandingRequest.setRoot_category_id(root_cat_id);
        trandingRequest.setStore_id(storeid);
        trandingRequest.setCustomer_id(customerid);
        trandingRequest.setPage(String.valueOf(pageCount));
        trandingRequest.setPagesize(String.valueOf(pageSize));

        BestSellingRequest bestSellingRequest = new BestSellingRequest();
        bestSellingRequest.setStore_id(storeid);
        bestSellingRequest.setCustomer_id(customerid);
        bestSellingRequest.setPage(String.valueOf(pageCount));
        bestSellingRequest.setPagesize(String.valueOf(pageSize));

        FeatureProductRequest featureProductRequest = new FeatureProductRequest();
        featureProductRequest.setStore_id(storeid);
        featureProductRequest.setCustomer_id(customerid);
        featureProductRequest.setPage(String.valueOf(pageCount));
        featureProductRequest.setPagesize(String.valueOf(pageSize));




        if (type == 0)
            HTTPWebRequest.OfferOfTheDayShowAll(mContext, offerOfTheDayRequest, AppConstants.APICode.OFFER_OFTHE_DAY, this, getSupportFragmentManager());
        else if (type == 1)
            HTTPWebRequest.TrendingnowShowAll(mContext, trandingRequest, AppConstants.APICode.TRENDING_NOW, this, getSupportFragmentManager());
        else if (type == 2)
            HTTPWebRequest.BestSellingShowAll(mContext, bestSellingRequest, AppConstants.APICode.BEST_SELLER, this, getSupportFragmentManager());
        else if (type == 3)
            HTTPWebRequest.FeatureProductsShowAll(mContext, featureProductRequest, AppConstants.APICode.FEATURE_PRODUCT, this, getSupportFragmentManager());
        else
            Utility.toastMessage(mContext, "Invalid arguments.");
    }
}
