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
import android.widget.LinearLayout;
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
import com.mohit.shopebazardroid.adapter.ProductGridAdapter;
import com.mohit.shopebazardroid.dialog.ConfirmDialog;
import com.mohit.shopebazardroid.dialog.FilterDialog;
import com.mohit.shopebazardroid.dialog.SortDialog;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.listener.FilterListner;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.listener.SortListner;
import com.mohit.shopebazardroid.model.request.ProductRequest;
import com.mohit.shopebazardroid.model.response.CategoryChildrens;
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
 *  display all Products in pagination.
 */
public class ProductGridActivity extends BaseActivity implements ApiResponse, View
        .OnClickListener, SortListner, FilterListner, ConfirmDialogListner {

    public static String TAG = ProductGridActivity.class.getSimpleName();
    Context mContext;
    List<Product> arrayList;
    RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    RecyclerView.Adapter mAdapter;
    String subcategotyid;
    LinearLayout filterSortLinearLayout;
    //    AnimatedFloatingActionButtonTop sortFAB, filterFAB;
    int type;
    ProductResult productResult;
    SortDialog sortDialog;
    FilterDialog filterDialog;
    ArrayList<ProductFilterAttributes> filterAttributesArrayList;
    ConfirmDialog confirmDialog;
    private Menu menu;
    boolean isClearclicked = false;
    boolean noProductFound = false;

    View cartBadget;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "store_id");
    String root_cat_id = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.ROOT_CAT_ID, "root_cat_id");
    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
    TextView countertextView;
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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        subcategotyid = String.valueOf(getIntent().getIntExtra(CategoryChildrens.KEY_ID, 0));
        type = getIntent().getIntExtra(CategoryChildrens.KEY_TYPE, 0);

       loadMoreItems();


//        setupGrid();

        recyclerView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
                        intent.putExtra(AppConstants.RequestDataKey.PRODUCT, arrayList.get(position));
                        startActivity(intent);
                    }
                }));

        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);
//        sortFAB.setOnClickListener(this);
//        filterFAB.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        if(cartItemBadge!=null) {

        String cart_badge= MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.CART_TOTAL_ITEMS, "0");
        if(countertextView!=null)
        {
            countertextView.setText(""+cart_badge);
        }

        HTTPWebRequest.Products(mContext,getUserid(), subcategotyid, AppConstants.APICode.PRODUCTSLIST, this,
                getSupportFragmentManager());

    }

    private void setupGrid() {
        /*arrayList = new ArrayList<>();
        ProductEntity entity;
        for(int i=0;i<25; i++)
        {
            entity = new ProductEntity();
            entity.setImageurl("http://www.phtheme.com/androidimg/allimg/121004/2-121004202U40-L
            .jpg");
            arrayList.add(entity);
        }*/


        if(arrayList.size() <= pageSize){
            mAdapter = new ProductGridAdapter(this, arrayList);
            recyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
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
                        return true;
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
               /* if (filterAttributesArrayList == null || filterAttributesArrayList.size() == 0) {
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
                if (noProductFound) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;


        getMenuInflater().inflate(R.menu.filter_menu, menu);

        MenuItem item = menu.findItem(R.id.action_sorting);

        MenuItem itemFilter = menu.findItem(R.id.action_filter);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        cartBadget = inflater.inflate(R.layout.cart_badge_actionbar, null);
        cartBadget.setLayoutParams(new RelativeLayout.LayoutParams(item.getIcon()
                .getIntrinsicWidth() + 100, item.getIcon().getIntrinsicHeight() + 40));

         countertextView = (TextView) cartBadget.findViewById(R.id.counter);
//        textView.setText("0");
        countertextView.setText("" + MyApplication.preferenceGetString(AppConstants
                .SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
        MenuItem item2 = menu.findItem(R.id.action_cart);
        item2.setActionView(cartBadget);

        cartBadget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(mContext, CartActivity.class));
            }
        });

        return true;
    }


    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        isLoading = false;

        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        } else {
            switch (apiCode) {
                case AppConstants.APICode.BANNER_PRODUCTS:
                case AppConstants.APICode.PRODUCTSLIST:

                    if(!TextUtils.isEmpty(response)){

                        Type productListType = new TypeToken<BaseResponse<List<Product>>>(){}.getType();
                        BaseResponse<List<Product>> baseResponse = new Gson().fromJson(response, productListType);

                        if(baseResponse.getInfo().size() != 0){
                            if(arrayList == null)
                                arrayList = new ArrayList<>();
                            else
                                arrayList.clear();


                            arrayList.addAll(baseResponse.getInfo());
                            if(arrayList.size() == totalProducts)
                                isLastPage = true;

                            setupGrid();
                        } else {
//                        Utility.toastMessage(mContext, "No product Found");

                            confirmDialog = new ConfirmDialog();
                            confirmDialog.setListner(this, "Product Listing", "No product found",
                                    "OK", "RETRY");
                            noProductFound = true;
                            confirmDialog.show(getSupportFragmentManager(), ConfirmDialog.TAG);

                        }

                    } else {
                        Toast.makeText(mContext, R.string.host_not_reachable, Toast.LENGTH_SHORT).show();
                        this.finish();
                    }

                    break;
            }
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

        switch (view.getId()) {
            /*case R.id.sorting_fab:
                sortDialog = new SortDialog();
                sortDialog.setListner(this);
                sortDialog.show(getSupportFragmentManager(), SortDialog.TAG);
                break;
            case R.id.filter_fab:
                if (filterAttributesArrayList == null || filterAttributesArrayList.size() == 0) {
                    filterAttributesArrayList = new ArrayList<>();
                    filterAttributesArrayList.addAll(productResult.getFilter_attributes());
                }
                filterDialog = new FilterDialog();
                filterDialog.setListner(this);
                filterDialog.setupFilterData(filterAttributesArrayList);
                filterDialog.show(getSupportFragmentManager(), FilterDialog.TAG);
                break;*/
        }
    }

/*    @Override
    public void onAscendingSortSelect() {
        sortDialog.dismiss();
        Log.d(TAG, "onClick: sort");
        Collections.sort(arrayList, new Comparator<ProductEntity>() {
            @Override
            public int compare(ProductEntity lhs, ProductEntity rhs) {
                return Double.compare(Double.parseDouble(lhs.getPrice()), Double.parseDouble
 (rhs.getPrice()));
            }
        });

        mAdapter.notifyDataSetChanged();
        Log.d(TAG, "onClick: sort end");
    }

    @Override
    public void onDescendingSortSelect() {

        sortDialog.dismiss();

        Log.d(TAG, "onClick: sort");
        Collections.sort(arrayList, new Comparator<ProductEntity>() {
            @Override
            public int compare(ProductEntity lhs, ProductEntity rhs) {
                return Double.compare(Double.parseDouble(rhs.getPrice()), Double.parseDouble
 (lhs.getPrice()));
            }
        });

//        Collections.reverse(arrayList);
        Log.d(TAG, "onClick: "+new Gson().toJson(arrayList));
        mAdapter.notifyDataSetChanged();
        Log.d(TAG, "onClick: sort end");
    }*/

    @Override
    public void onAscendingSortSelect() {
        sortDialog.dismiss();
        Log.d(TAG, "onClick: sort");
        Collections.sort(arrayList, new Comparator<Product>() {
            @Override
            public int compare(Product lhs, Product rhs) {
                if (lhs.getDiscount_price() == 0) {
                    if (rhs.getDiscount_price() == 0)
                        return Double.compare(Double.parseDouble(lhs.getPro_price()), Double
                                .parseDouble(rhs.getPro_price()));
                    else
                        return Double.compare(Double.parseDouble(lhs.getPro_price()), Double
                                .parseDouble(""+rhs.getDiscount_price()));
                } else {
                    if (rhs.getDiscount_price() == 0)
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
        /*ProductRequest request = new ProductRequest();
        request.setCatid(subcategotyid);
        request.setPage(String.valueOf(1));
        request.setPagesize(String.valueOf(10000));*/

        isClearclicked = true;
        /*if (type == 0)
            HTTPWebRequest.Products(mContext, request, AppConstants.APICode.PRODUCTSLIST, this,
                    getSupportFragmentManager());
        else
            HTTPWebRequest.BannerProducts(mContext, request, AppConstants.APICode
                    .BANNER_PRODUCTS, this, getSupportFragmentManager());*/

    }

    @Override
    public void onFilterCancelClick() {
        filterDialog.dismiss();
    }

    @Override
    public void onFilterSubmitClick(ArrayList<ProductFilterAttributes> filterArrayList) {
        filterPageCount = 0;
        pageCount = 0;
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
            isClearclicked = false;
        }

        if (jsonArray.size() > 0) {
            request.setJson_filter(jsonArray.toString());
        }

        if (type == 0)
            HTTPWebRequest.Products(mContext, getUserid(), subcategotyid, AppConstants.APICode.PRODUCTSLIST, this, getSupportFragmentManager());
        else if (type == 1){
//            HTTPWebRequest.BannerProducts(mContext, request, AppConstants.APICode.BANNER_PRODUCTS, this, getSupportFragmentManager());
        }
        else
            Utility.toastMessage(mContext, "Invalid arguments.");


    }

    @Override
    public void onOptionCheckchanged() {


    }

    @Override
    public void onPositiveButtonClick() {
        confirmDialog.dismiss();
    }

    @Override
    public void onNegativeButtonClick() {

        if (type == 0)
            HTTPWebRequest.Products(mContext, getUserid(), subcategotyid, AppConstants.APICode.PRODUCTSLIST, this,
                    getSupportFragmentManager());
        else{
//            HTTPWebRequest.BannerProducts(mContext, request, AppConstants.APICode.BANNER_PRODUCTS, this, getSupportFragmentManager());
        }

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

        if (type == 0) {
            getSupportActionBar().setTitle(getIntent().getStringExtra(CategoryChildrens.KEY_NAME));

        } else {
            /*getSupportActionBar().setTitle("Products");
            HTTPWebRequest.BannerProducts(mContext, request, AppConstants.APICode
                    .BANNER_PRODUCTS, this, getSupportFragmentManager());*/
        }
    }

}
