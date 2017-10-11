package com.mohit.shopebazardroid.activity.products;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.adapter.RelatedProductListAdapter;
import com.mohit.shopebazardroid.adapter.ReviewListAdapter;
import com.mohit.shopebazardroid.adapter.SpinnerProductDetailAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.model.request.CustomOptionRequest;
import com.mohit.shopebazardroid.model.request.CustomerReviewListRequest;
import com.mohit.shopebazardroid.model.request.RelatedProductsRequest;
import com.mohit.shopebazardroid.model.response.CustomerReviewList;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.model.response.RelatedProductList;
import com.mohit.shopebazardroid.model.response.RelatedProductResponse;
import com.mohit.shopebazardroid.model.response.ReviewList;
import com.mohit.shopebazardroid.model.response.WishListResponse;
import com.mohit.shopebazardroid.models.Product;
import com.mohit.shopebazardroid.models.ProductImage;
import com.mohit.shopebazardroid.models.UserCart;
import com.mohit.shopebazardroid.models.UserCartProduct;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.ImageSlider_DescriptionAnimation;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by msp on 22/7/16.
 */

public class ProductDetailActivity extends BaseActivity implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener, View.OnClickListener, ApiResponse {
    public static String TAG = ProductDetailActivity.class.getSimpleName();
    Context mContext;
    int screenWidth;
    int screenHeight;
    private SliderLayout sliderLayout;
    private TextView quentityTextView;
    private AppCompatSpinner sizeSpinner;
    private ImageView minusImageView, additionImageView;
    //    private ProductEntity productEntity;
    private TextView nameTextView;
    private TextView latestPriceTextView, oldpriceTextView;
    private WebView descriptionShortTextView;
    //------- custom and configurable option -----
    private LinearLayout option_layout;
    private ArrayList<ArrayList<String>> optionDropDownValueArray = new ArrayList<>();
    boolean isCustomOption = true;
    //------------------------------
    AppCompatButton addToCartBtn;
    ArrayList<CustomOptionRequest> customOptionRequestsArray = new ArrayList<>();
    float baseCurrencyRate;
    String baseCurrencyCode = "";
    String addTocart = "1";
    private Context c;
    private TextView pd_description_lbl;
    private TextView pd_quentity_lbl;

    int themeCode;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "1");
    String customerid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "");
    String ishideprice = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_HIDE_PRICE, "0");
//    String product_list_attribute = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.PRODUCT_LIST_ATTRIBUTE, "product_list_attribute");

    RelativeLayout rel_related_product;
    RelativeLayout alsoPurchasedRelativeLayout, recentlyViewedRelativeLayout;

//    HListView related_product_hlistview;

    private RecyclerView related_product_hlistview;
    private RecyclerView alsoPurchased_hlistview;
    private RecyclerView recentlyViewed_hlistview;
    private RecyclerView.LayoutManager mLayoutManager_related_product;


    RelatedProductListAdapter adapter;
    RelatedProductListAdapter alsoPurchasedAdapter;
    RelatedProductListAdapter recentlyViewedAdapter;
    ArrayList<RelatedProductList> related_product_arrayList;
    ArrayList<RelatedProductList> alsopurchased_arrayList;
    ArrayList<RelatedProductList> recently_viewed_arrayList;
    //    RelatedProductList related_product_arrayList;
    ImageView img_rel_product;
    TextView txt_product_lbl;
    ProductEntity entity;
    ArrayList<ReviewList> arr_review_list;
    ReviewListAdapter reviewListAdapter;
    String Getpro_attribute;
    String url_img;
    String json;

    String imagePrefix = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");
    private Menu menu;
//    private ExpandableRelativeLayout specificationLayout, descriptionLayout;

    ArrayList<AppCompatSpinner> appCompatSpinnerArrayList;

    private TextView txt_wishlist;
    private String cartid = "";
    private Product product = null;

    public boolean isAddToWishlistCallback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.btn_back);
        getScreenResolution(mContext);

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

//        productEntity = (ProductEntity) getIntent().getSerializableExtra(ProductEntity.KEY_OBJECT);

        baseCurrencyRate = MyApplication.preferenceGetFloat(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_RATE, 1);
        baseCurrencyCode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, getString(R.string.rupee_sign));
        addToCartBtn = (AppCompatButton) findViewById(R.id.pd_addtocart_btn);
        addToCartBtn.setTypeface(SplashActivity.opensans_regular);

        pd_description_lbl = (TextView) findViewById(R.id.pd_description_lbl);
        pd_description_lbl.setTypeface(SplashActivity.opensans_semi_bold);
        pd_description_lbl.setOnClickListener(this);

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(mContext.getApplicationContext());
        Gson gson = new Gson();
        json = appSharedPrefs.getString("user", "");

        Log.e(TAG, "Attributes: " + json);

//        related_product_hlistview = (HListView) findViewById(R.id.related_product_hlistview);

        related_product_hlistview = (RecyclerView) findViewById(R.id.related_product_hlistview);
        mLayoutManager_related_product = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        related_product_hlistview.setLayoutManager(mLayoutManager_related_product);

        alsoPurchasedRelativeLayout = (RelativeLayout) findViewById(R.id.alsopurchased_main_layout);
        alsoPurchased_hlistview = (RecyclerView) findViewById(R.id.alsopurchased_hlistview);
        alsoPurchased_hlistview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        recentlyViewedRelativeLayout = (RelativeLayout) findViewById(R.id.recently_viewed_main_layout);
        recentlyViewed_hlistview = (RecyclerView) findViewById(R.id.recently_viewed_hlistview);
        recentlyViewed_hlistview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));


        txt_product_lbl = (TextView) findViewById(R.id.txt_product_lbl);
        txt_product_lbl.setTypeface(SplashActivity.opensans_regular);

        rel_related_product = (RelativeLayout) findViewById(R.id.rel_related_product);

        txt_wishlist = (TextView) findViewById(R.id.txt_wishlist);
        txt_wishlist.setTypeface(SplashActivity.opensans_regular);

        option_layout = (LinearLayout) findViewById(R.id.option_layout);
        nameTextView = (TextView) findViewById(R.id.pd_productname_lbl);
        latestPriceTextView = (TextView) findViewById(R.id.pd_latest_price_lbl);
        oldpriceTextView = (TextView) findViewById(R.id.pd_old_price_lbl);

        if (ishideprice.equalsIgnoreCase("0")) {
            latestPriceTextView.setVisibility(View.VISIBLE);
            oldpriceTextView.setVisibility(View.VISIBLE);
        } else {
            latestPriceTextView.setVisibility(View.GONE);
            oldpriceTextView.setVisibility(View.GONE);

        }

        pd_quentity_lbl = (TextView) findViewById(R.id.pd_quentity_lbl);
        pd_quentity_lbl.setTypeface(SplashActivity.opensans_regular);

        descriptionShortTextView = (WebView) findViewById(R.id.pd_description_content);
//        descriptionShortTextView.setTypeface(SplashActivity.opensans_regular);
//        descriptionLayout = (ExpandableRelativeLayout) findViewById(R.id.pd_description_content_layout);



        sliderLayout = (SliderLayout) findViewById(R.id.product_detail_slider);
        sizeSpinner = (AppCompatSpinner) findViewById(R.id.pd_size_spinner);
        quentityTextView = (TextView) findViewById(R.id.pd_quentity_content_lbl);


        minusImageView = (ImageView) findViewById(R.id.pd_quentity_minus_image);
        additionImageView = (ImageView) findViewById(R.id.pd_quentity_addition_image);

        // network call
//        HTTPWebRequest.ProductDetail(mContext, request, AppConstants.APICode.PRODUCT_DETAIL, this, getSupportFragmentManager());

        product = (Product) getIntent().getSerializableExtra(AppConstants.RequestDataKey.PRODUCT);
        if(product != null){
            setupUI();
        }

        // set banner height-width
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 1);
        sliderLayout.setLayoutParams(layoutParams);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_spinnerlayout, Arrays.asList(getResources().getStringArray(R.array.size_array)));
        sizeSpinner.setAdapter(adapter);

        minusImageView.setOnClickListener(this);
        additionImageView.setOnClickListener(this);
        addToCartBtn.setOnClickListener(this);
        addTocart = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.ADD_TO_CART, "1");
        if (addTocart.equalsIgnoreCase("0"))
            addToCartBtn.setVisibility(View.GONE);


        related_product_hlistview.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

//                Toast.makeText(mContext, "Product " + i, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
                        intent.putExtra("product", related_product_arrayList.get(position).getProduct_id());
                        startActivity(intent);
                    }
                }));

        alsoPurchased_hlistview.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

//                Toast.makeText(mContext, "Product " + i, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
                        intent.putExtra("product", alsopurchased_arrayList.get(position).getProduct_id());
                        startActivity(intent);
                    }
                }));

        recentlyViewed_hlistview.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

//                Toast.makeText(mContext, "Product " + i, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
                        intent.putExtra("product", recently_viewed_arrayList.get(position).getProduct_id());
                        startActivity(intent);
                    }
                }));




    }

    private void setupUI(){

        descriptionShortTextView.loadData(Html.fromHtml(product.getPro_description()).toString(), "text/html", "UTF-8");
        nameTextView.setText(product.getPro_name());

        String rupeePrefix = getString(R.string.rupee_sign);
        if(product.getDiscount_price() > 0){
            latestPriceTextView.setText(rupeePrefix+String.valueOf(product.getDiscount_price()));
            oldpriceTextView.setText(rupeePrefix+product.getPro_price());
            oldpriceTextView.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else{
            latestPriceTextView.setText(rupeePrefix+product.getPro_price());
            oldpriceTextView.setVisibility(View.GONE);
        }

        setupBannerSlider();
    }

    @Override
    protected void onResume() {
        super.onResume();

        cartid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "");
        String itemCount = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0");

        if(!TextUtils.isEmpty(itemCount) && !itemCount.equalsIgnoreCase("0")){
            MyApplication.preferencePutString(AppConstants
                    .SharedPreferenceKeys.CART_TOTAL_ITEMS, "" + itemCount);
            if(cartItemBadge != null){
                cartItemBadge.setText("" + itemCount);
            }
        }

        Log.d(TAG, "isAddToWishlistCallback: "+isAddToWishlistCallback);
        Log.d(TAG, "isUserLogin: "+isUserLogin());

        if(isAddToWishlistCallback && isUserLogin()){
            callAddToWishlistApi();
            isAddToWishlistCallback = false;
        } else {
            isAddToWishlistCallback = false;
        }

    }

    private void setLabelAndDropDown(final ArrayList<ArrayList<String>> optionDropDownValueArray) {
        appCompatSpinnerArrayList = new ArrayList<>();

        for (int i = 0; i < optionDropDownValueArray.size(); i++) {

            LinearLayout horLL = new LinearLayout(this);
            horLL.setGravity(Gravity.CENTER_VERTICAL);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            horLL.setLayoutParams(layoutParams);
            horLL.setOrientation(LinearLayout.HORIZONTAL);
            TextView titleTv = new TextView(this);
            if (isCustomOption) {

                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                titleTv.setLayoutParams(layoutParams);
                titleTv.setText(entity.getCustom_option().get(i).getTitle());
                titleTv.setPadding(10, 0, 10, 0);
//                titleTv.setBackgroundColor(getResources().getColor(R.color.bg_red));
                titleTv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                titleTv.setTextColor(getResources().getColor(R.color.white));
                titleTv.setTypeface(SplashActivity.opensans_regular);

                titleTv.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

            } else {
                layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.gravity = Gravity.CENTER_VERTICAL;
                titleTv.setLayoutParams(layoutParams);
                titleTv.setText(entity.getConfigurable_option().get(i).getLabel());
                titleTv.setPadding(10, 0, 10, 0);
//                titleTv.setBackgroundColor(getResources().getColor(R.color.bg_red));
                titleTv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                titleTv.setTextColor(getResources().getColor(R.color.white));
                titleTv.setTypeface(SplashActivity.opensans_regular);

                titleTv.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

            }
            final AppCompatSpinner dropdown = new AppCompatSpinner(this);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_spinnerlayout, optionDropDownValueArray.get(i));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdown.setTag(i);
            dropdown.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            dropdown.setGravity(Gravity.CENTER);
            dropdown.setAdapter(adapter);
            dropdown.setPadding(10, 0, 10, 0);

            dropdown.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                           long l) {

                    AppCompatSpinner dropdown = (AppCompatSpinner) adapterView;
                    if (dropdown.getTag() == adapterView.getTag()) {
                        int indexofSpinner = Integer.parseInt("" + dropdown.getTag());

                        Log.d("tag", "tag==" + dropdown.getTag() + "position==" + position);
                        CustomOptionRequest customOptionRequest = customOptionRequestsArray.get
                                (Integer.parseInt("" + dropdown.getTag()));
                        if (isCustomOption) /*custom option*/ {

                               /* customOptionRequest.setCustom_title_value(entity.getCustom_option().get(Integer.parseInt("" + dropdown.getTag())).getValue_option().get(position).getTitle());
                                customOptionRequest.setCustom_title_value_id(entity.getCustom_option().get(Integer.parseInt("" + dropdown.getTag
                                        ())).getValue_option().get(position).getValue_id());*/
                            Log.d("tag", "getCustom_title_value==" + optionDropDownValueArray.get(Integer.parseInt("" + dropdown.getTag())).get(position));

                            if (position == 0) {
                                customOptionRequest.setCustom_title_value(optionDropDownValueArray.get(Integer.parseInt("" + dropdown.getTag())).get(position));
                                customOptionRequest.setCustom_title_value_id(0);
                                customOptionRequest.setPrice(0);
                            } else {
                                customOptionRequest.setCustom_title_value(entity.getCustom_option().get(Integer.parseInt("" + dropdown.getTag())).getValue_option().get(position - 1).getTitle());
                                customOptionRequest.setCustom_title_value_id(entity.getCustom_option().get(Integer.parseInt("" + dropdown.getTag
                                        ())).getValue_option().get(position - 1).getOption_type_id());
                                customOptionRequest.setPrice(entity.getCustom_option().get(Integer.parseInt("" + dropdown.getTag
                                        ())).getValue_option().get(position - 1).getPrice());

                                Float price = entity.getCustom_option().get(Integer.parseInt("" + dropdown.getTag
                                        ())).getValue_option().get(position - 1).getPrice();

                                Log.e(TAG, "Price of item: " + price);

                            }

                        } else /*configurable*/ {

                            Log.d("tag", "getCustom_title_value==" + optionDropDownValueArray.get(Integer.parseInt("" + dropdown.getTag())).get(position));
                            if (position == 0) //----select-----
                            {
                                customOptionRequest.setCustom_title_value(optionDropDownValueArray.get(Integer.parseInt("" + dropdown.getTag())).get(position));
                                customOptionRequest.setCustom_title_value_id(0);
                                customOptionRequest.setPrice(0);
                                sliderLayout.setVisibility(View.VISIBLE);
                                setupBannerSlider();

                            } else {

                                customOptionRequest.setCustom_title_value(entity.getConfigurable_option().get(Integer.parseInt("" + dropdown.getTag())).getValues().get(position - 1).getLabel());
                                customOptionRequest.setCustom_title_value_id(entity.getConfigurable_option().get(Integer.parseInt("" + dropdown.getTag
                                        ())).getValues().get(position - 1).getValue_index());
                                customOptionRequest.setPrice(entity.getConfigurable_option().get(Integer.parseInt("" + dropdown.getTag
                                        ())).getValues().get(position - 1).getPricing_value());

                                if (json.contains(Getpro_attribute)) {
                                    url_img = entity.getConfigurable_option().get(Integer.parseInt("" + dropdown.getTag())).getValues().get(position - 1).getImage();

                                    Log.e(TAG, "Value Image " + url_img);

                                    if (!TextUtils.isEmpty(url_img)) {
                                        sliderLayout.setVisibility(View.VISIBLE);
                                        setupImageSlider();
                                    } else {
//                                        Toast.makeText(mContext, "Image not available", Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "Image not available -> " + url_img);
                                    }

                                }

                                //--------------reset spinner value depend top to bottom----------
                                // int indexofSpinner= Integer.parseInt("" + dropdown.getTag());
                                ArrayList<Integer> checkvalues = new ArrayList<>();
                                for (int first = 0; first < entity.getConfigurable_option().get(indexofSpinner).getValues().get(position - 1).getProducts().size(); first++) {
                                    checkvalues.add(Integer.parseInt("" + entity.getConfigurable_option().get(indexofSpinner).getValues().get(position - 1).getProducts().get(first).getVal()));
                                }
                                //-----------find common value from selected to up list-------
                                if (indexofSpinner > 0) {
                                    //if(entity.getConfigurable_option().get(indexofSpinner).getValues().get(position-1).getProducts().size() > 1)
                                    //{
                                    List<List<Integer>> lists = new ArrayList<List<Integer>>();
                                    //lists.add(new ArrayList<Integer>(Arrays.asList(1, 3, 5)));
                                    //lists.add(new ArrayList<Integer>(Arrays.asList(1, 6, 7, 9, 3)));
                                    //lists.add(new ArrayList<Integer>(Arrays.asList(1, 3, 10, 11)));

                                    for (int temp1 = indexofSpinner; temp1 >= 0; temp1--)  // rverse spinner
                                    {
                                        int index = 0;
                                        for (int temp2 = 0; temp2 < entity.getConfigurable_option().get(temp1).getValues().size(); temp2++) {
                                            if (entity.getConfigurable_option().get(temp1).getValues().get(temp2).getLabel().equalsIgnoreCase(appCompatSpinnerArrayList.get(temp1).getSelectedItem().toString())) {
                                                index = temp2;
                                                break;
                                            }
                                        }
                                        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
                                        for (int temp3 = 0; temp3 < entity.getConfigurable_option().get(temp1).getValues().get(index).getProducts().size(); temp3++) {
                                            integerArrayList.add(Integer.parseInt("" + entity.getConfigurable_option().get(temp1).getValues().get(index).getProducts().get(temp3).getVal()));
                                        }
                                        lists.add(integerArrayList);
                                        System.out.println(lists);

                                    }

                                    List<Integer> commons = new ArrayList<Integer>();
                                    commons.addAll(lists.get(0));
                                    for (ListIterator<List<Integer>> iter = lists.listIterator(1); iter.hasNext(); ) {
                                        commons.retainAll(iter.next());
                                    }
                                    System.out.println(commons);
                                    checkvalues.clear();
                                    checkvalues.addAll(commons);
                                    //}
                                }
                                //-------------end of find common value from selected to up list--------------
                                //Log.d("value", "size value===" + checkvalues.size());
                                ArrayList<ArrayList<String>> updatedSpinnerValues = new ArrayList<ArrayList<String>>();

                                for (int i = indexofSpinner + 1; i < entity.getConfigurable_option().size(); i++)//first spinner main values
                                {
                                    ArrayList<String> values = new ArrayList<String>();
                                    values.add("Select");
                                    for (int j = 0; j < entity.getConfigurable_option().get(i).getValues().size(); j++) {
                                        boolean outerloop = false;
                                        for (int fourth = 0; fourth < entity.getConfigurable_option().get(i).getValues().get(j).getProducts().size(); fourth++) {
                                            for (int third = 0; third < checkvalues.size(); third++) {
                                                if (entity.getConfigurable_option().get(i).getValues().get(j).getProducts().get(fourth).getVal().equals("" + checkvalues.get(third))) {
                                                    Log.d("value222", i + "> value===" + entity.getConfigurable_option().get(i).getValues().get(j).getLabel());
                                                    values.add(entity.getConfigurable_option().get(i).getValues().get(j).getLabel());
                                                    outerloop = true;
                                                    break;
                                                } else {
                                                    outerloop = false;
                                                }

                                            }
                                            if (outerloop) {
                                                break;
                                            }
                                        }

                                    }
                                    //Log.d("value","3333value size=="+values.size());
                                    updatedSpinnerValues.add(values);

                                }

                                //----------------update spinner depend on top to bottom---------------
                                for (int i = 0, j = indexofSpinner + 1; i < updatedSpinnerValues.size(); i++, j++) {
                                    Log.d("spin", i + ">spin size==" + updatedSpinnerValues.get(i).size());
                                    SpinnerProductDetailAdapter adapter = new SpinnerProductDetailAdapter(ProductDetailActivity.this, updatedSpinnerValues.get(i));
                                    AppCompatSpinner appCompatSpinner = appCompatSpinnerArrayList.get(j);
                                    appCompatSpinner.setAdapter(adapter);
                                }
                                //--------------end of reset spinner value depend top to bottom----------
                            }

                            //------------set customOptionRequest  value after select spinner for correct find value --------------
                            for (int temp2 = 0; temp2 < entity.getConfigurable_option().get(indexofSpinner).getValues().size(); temp2++) {
                                if (entity.getConfigurable_option().get(indexofSpinner).getValues().get(temp2).getLabel().equalsIgnoreCase(appCompatSpinnerArrayList.get(indexofSpinner).getSelectedItem().toString())) {
                                    customOptionRequest.setCustom_title_value(entity.getConfigurable_option().get(indexofSpinner).getValues().get(temp2).getLabel());
                                    Log.d("title_value", "title_value=====" + entity.getConfigurable_option().get(indexofSpinner).getValues().get(temp2).getLabel());
                                    customOptionRequest.setCustom_title_value_id(entity.getConfigurable_option().get(indexofSpinner).getValues().get(temp2).getValue_index());
                                    Log.d("title_value_id", "title_value_id=====" + entity.getConfigurable_option().get(indexofSpinner).getValues().get(temp2).getValue_index());
                                    customOptionRequest.setPrice(entity.getConfigurable_option().get(indexofSpinner).getValues().get(temp2).getPricing_value());
                                    Log.d("Price", "Price=====" + entity.getConfigurable_option().get(indexofSpinner).getValues().get(temp2).getPricing_value());
                                    break;
                                }
                            }

                        }
                        customOptionRequestsArray.set(Integer.parseInt("" + dropdown.getTag()), customOptionRequest);

                        float optionPrice = 0;
                        for (int i = 0; i < customOptionRequestsArray.size(); i++) {
                            CustomOptionRequest customOptionRequest1 = customOptionRequestsArray.get(i);
                            Log.d("Price", "Price==" + customOptionRequest1.getPrice() * baseCurrencyRate);
                            optionPrice = optionPrice + (customOptionRequest1.getPrice() * baseCurrencyRate);
                            Log.e(TAG, "getCustom_title: " + customOptionRequest1.getCustom_title());
                            Log.e(TAG, "getCustom_title_value: " + customOptionRequest1.getCustom_title_value());
                        }

                        Log.e(TAG, "Option Price: " + optionPrice);

                        if (TextUtils.isEmpty(entity.getSpecial_price())) {
                            Float tempLatestPrice = Float.parseFloat(entity.getPrice()) * baseCurrencyRate;
                            latestPriceTextView.setText(baseCurrencyCode + (String.format("%.2f", tempLatestPrice + optionPrice)));

                            oldpriceTextView.setVisibility(View.GONE);
                        } else {

                            Float tempLatestPrice = Float.parseFloat(entity.getSpecial_price()) * baseCurrencyRate;
                            latestPriceTextView.setText(baseCurrencyCode + (String.format("%.2f", tempLatestPrice + optionPrice)));

                            Float tempOldPrice = Float.parseFloat(entity.getPrice()) * baseCurrencyRate;
                            oldpriceTextView.setText(baseCurrencyCode + (String.format("%.2f", tempOldPrice + optionPrice)));
                            oldpriceTextView.setPaintFlags(oldpriceTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            horLL.addView(titleTv);
            horLL.addView(dropdown);
            appCompatSpinnerArrayList.add(dropdown);
            LinearLayout temp = new LinearLayout(this);
            temp.setOrientation(LinearLayout.VERTICAL);
            temp.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 12));
            option_layout.addView(temp);
            option_layout.addView(horLL);
        }
    }

    private void setupImageSlider() {

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ((int) NavigationDrawerActivity.height / 2));
        sliderLayout.setLayoutParams(layoutParams);
//        HashMap<String,String> url_maps = new HashMap<String, String>();

        if (!url_img.equals("")) {
            sliderLayout.setVisibility(View.VISIBLE);
            sliderLayout.removeAllSliders();

            TextSliderView textSliderView = new TextSliderView(mContext);
            textSliderView
                    .image(url_img)
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(this);
//                        .setPicasso(picasso);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", url_img);
            sliderLayout.addSlider(textSliderView);

            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setCustomAnimation(new ImageSlider_DescriptionAnimation());
            sliderLayout.setDuration(4000);
            sliderLayout.addOnPageChangeListener(this);
        } else {
            sliderLayout.setVisibility(View.GONE);
        }
//        }
    }

    private void setupBannerSlider() {

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ((int) NavigationDrawerActivity.height / 2));
        sliderLayout.setLayoutParams(layoutParams);
//        HashMap<String,String> url_maps = new HashMap<String, String>();

        if (product.getProductImage().size() > 0) {
            sliderLayout.setVisibility(View.VISIBLE);
            for (ProductImage banner : product.getProductImage()) {

                TextSliderView textSliderView = new TextSliderView(mContext);

                textSliderView
                        .description(banner.getImage_name())
//                        .image(imagePrefix+banner.getImage_path())
                        .image("http://demo.ajax-cart.com/photos/product/4/176/4.jpg")
                        .setScaleType(BaseSliderView.ScaleType.CenterInside)
                        .setOnSliderClickListener(this);
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle().putString("extra", banner.getImage_name());
                sliderLayout.addSlider(textSliderView);

                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
                sliderLayout.setCustomAnimation(new ImageSlider_DescriptionAnimation());
                sliderLayout.setDuration(4000);
                sliderLayout.addOnPageChangeListener(this);
            }
        } else {
            sliderLayout.setVisibility(View.GONE);
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
            case R.id.action_share:
//                Toast.makeText(mContext, "Share Click : ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@readapp.co.uk"});
                i.putExtra(Intent.EXTRA_SUBJECT, "New Product");
                i.putExtra(Intent.EXTRA_TEXT, "Your friend wants to share this product with you: " + entity.getName() + " " + entity.getProduct_url());
                try {
                    startActivity(Intent.createChooser(i, "Share via.."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(mContext, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                return true;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    private void callAddToWishlistApi(){

        /*HTTPWebRequest.AddToWishList(mContext, request, AppConstants.APICode
                .ADD_TO_WISHLIST, this, getSupportFragmentManager());*/
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pd_quentity_minus_image:
                int qtyMinus = Integer.parseInt(quentityTextView.getText().toString().trim());
                if (qtyMinus != 1) {
                    qtyMinus -= 1;
                    quentityTextView.setText(String.valueOf(qtyMinus));
                }
                break;
            case R.id.pd_quentity_addition_image:
                int qtyAddition = Integer.parseInt(quentityTextView.getText().toString().trim());
                quentityTextView.setText(String.valueOf(++qtyAddition));
                break;
            case R.id.pd_addtocart_btn:


                for (int i = 0; i < customOptionRequestsArray.size(); i++) {
                    Log.d(TAG, "onClick: " + customOptionRequestsArray.get(i).getCustom_title_value());
                    if (customOptionRequestsArray.get(i).getCustom_title_value().equalsIgnoreCase("Select")) {
                        Toast.makeText(mContext, "Please select " + customOptionRequestsArray.get(i).getCustom_title(), Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                UserCart userCart = new UserCart();
                String cartid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0");

                userCart.setCart_id(Integer.parseInt(cartid));
                userCart.setToken(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOKEN, FirebaseInstanceId.getInstance().getToken()));
                userCart.setUser_id(Integer.parseInt(getUserid()));

                UserCartProduct userCartProduct = new UserCartProduct();
                userCartProduct.setCart_id(Integer.parseInt(cartid));
                userCartProduct.setProduct_id(product.getPro_mst_id());
                userCartProduct.setProduct_name(product.getPro_name());
                userCartProduct.setDescription(product.getPro_description());
                userCartProduct.setProduct_qty(Integer.parseInt(quentityTextView.getText().toString().trim()));
                userCartProduct.setProduct_price(product.getPro_price());
                userCartProduct.setProduct_code(product.getPro_code());
                userCartProduct.setShipping_charge(30);
                userCartProduct.setGst(product.getGst());
                userCartProduct.setGst_type(product.getGst_type());
                userCartProduct.setCat_id(product.getCat_id());
                userCartProduct.setCategory_name(product.getCat_name());
                userCartProduct.setBrand_id(product.getBrand_id());
                userCartProduct.setBrand_name(product.getBrand_name());
                userCartProduct.setDiscount_price(String.valueOf(product.getDiscount_price()));
                userCartProduct.setImage_name(product.getPro_image());

                double subtotal = userCartProduct.getProduct_qty() * userCartProduct.getShipping_charge();
                userCartProduct.setSubtotal(String.valueOf(subtotal));

                List<UserCartProduct> list = new ArrayList<>();
                list.add(userCartProduct);
                userCart.setUserCartProduct(list);

                String jsonRequest = new Gson().toJson(userCart);
                HTTPWebRequest.AddUpdateCart(mContext, jsonRequest, AppConstants.APICode.ADDTOCART, this, getSupportFragmentManager());


                break;

        }
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        isAddToWishlistCallback = false;
        Log.d("response", "response==" + response);
        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        switch (apiCode) {

            case AppConstants.APICode.ADDTOCART:

                Type addToCartType = new TypeToken<BaseResponse<UserCart>>(){}.getType();
                BaseResponse<UserCart> baseResponse = new Gson().fromJson(response, addToCartType);
                Toast.makeText(mContext, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                if (baseResponse.getStatus() == 1) {
                    UserCart userCart = baseResponse.getInfo();
                    cartid = String.valueOf(userCart.getCart_id());
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID,cartid);
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOKEN,userCart.getToken());
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, String.valueOf(userCart.getCartCount()));
                    cartItemBadge.setText(String.valueOf(userCart.getCartCount()));
                }

                break;
            case AppConstants.APICode.PRODUCT_DETAIL:

                RelatedProductsRequest request = new RelatedProductsRequest();
//                request.setProductid(product_id);
                request.setCustomer_id(customerid);
                request.setStore_id(storeid);

                // network call
                HTTPWebRequest.RelatedProduct(mContext, request, AppConstants.APICode.RELATED_PRODUCT, this);

                break;

            case AppConstants.APICode.RELATED_PRODUCT:
                RelatedProductResponse rel_response = new Gson().fromJson(response, RelatedProductResponse.class);
                if (rel_response.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                if (rel_response.getStatus().equalsIgnoreCase("success")
                        && rel_response.getResult().getProductlist() != null
                        && rel_response.getResult().getProductlist().size() > 0) {

                    rel_related_product.setVisibility(View.VISIBLE);
                    related_product_hlistview.setVisibility(View.VISIBLE);

                    related_product_arrayList = rel_response.getResult().getProductlist();
                    setupRelatedProductHListview();
                } else {
//                    Utility.toastMessage(mContext, "No product Found");
                    rel_related_product.setVisibility(View.GONE);

                }

                RelatedProductsRequest alsoPurchasedRequest = new RelatedProductsRequest();
//                alsoPurchasedRequest.setProductid(product_id);
                alsoPurchasedRequest.setCustomer_id(customerid);
                alsoPurchasedRequest.setStore_id(storeid);

                // network call
                HTTPWebRequest.AlsoPurchased(mContext, alsoPurchasedRequest, AppConstants.APICode.ALSO_PURCHASED, this);

                break;

            case AppConstants.APICode.ALSO_PURCHASED:
                RelatedProductResponse alsoPurchased = new Gson().fromJson(response, RelatedProductResponse.class);
                if (alsoPurchased.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                if (alsoPurchased.getStatus().equalsIgnoreCase("success")
                        && alsoPurchased.getResult().getProductlist() != null
                        && alsoPurchased.getResult().getProductlist().size() > 0) {

                    alsoPurchasedRelativeLayout.setVisibility(View.VISIBLE);
                    alsoPurchased_hlistview.setVisibility(View.VISIBLE);

                    alsopurchased_arrayList = alsoPurchased.getResult().getProductlist();
                    setupAlsopurchasedHListview();
                } else {
                    alsoPurchasedRelativeLayout.setVisibility(View.GONE);

                }

                RelatedProductsRequest recentlyViewedRequest = new RelatedProductsRequest();
//                recentlyViewedRequest.setProductid(product_id);
                recentlyViewedRequest.setCustomer_id(customerid);
                recentlyViewedRequest.setStore_id(storeid);

                // network call
                HTTPWebRequest.RecentlyViewed(mContext, recentlyViewedRequest, AppConstants.APICode.RECENTLY_VIEWED, this);


                break;

            case AppConstants.APICode.RECENTLY_VIEWED:
                RelatedProductResponse recentlyViwed= new Gson().fromJson(response, RelatedProductResponse.class);
                if (recentlyViwed.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                if (recentlyViwed.getStatus().equalsIgnoreCase("success")
                        && recentlyViwed.getResult().getProductlist() != null
                        && recentlyViwed.getResult().getProductlist().size() > 0) {

                    recentlyViewedRelativeLayout.setVisibility(View.VISIBLE);
                    recentlyViewed_hlistview.setVisibility(View.VISIBLE);

                    recently_viewed_arrayList = recentlyViwed.getResult().getProductlist();
                    setupRecentlyViwedHListview();
                } else {
                    alsoPurchasedRelativeLayout.setVisibility(View.GONE);

                }

                CustomerReviewListRequest cust_review_list = new CustomerReviewListRequest();
                cust_review_list.setStore_id(storeid);
//                cust_review_list.setProduct_id(product_id);
                cust_review_list.setPage(String.valueOf(1));
                cust_review_list.setPagesize(String.valueOf(2));

                // network call
                HTTPWebRequest.ReviewList(mContext, cust_review_list, AppConstants.APICode.REVIEW_LIST, this, getSupportFragmentManager());

                break;

            case AppConstants.APICode.REVIEW_LIST:
                CustomerReviewList review_response = new Gson().fromJson(response, CustomerReviewList.class);
                if (review_response.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                break;
            case AppConstants.APICode.ADD_TO_WISHLIST:
                WishListResponse wishListResponse = new Gson().fromJson(response, WishListResponse.class);
                if (wishListResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    finish();
                    return;
                }

                if (wishListResponse.getStatus().equalsIgnoreCase("success")) {
                    Utility.toastMessage(mContext, wishListResponse.getResult().getMessage());
//                    img_add_wishlist.setImageResource(R.drawable.like_wishlist);
                } else {
                    Utility.toastMessage(mContext, wishListResponse.getResult().getMessage());
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

    private void getScreenResolution(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenHeight = (int) (displayMetrics.heightPixels / displayMetrics.density);
        screenWidth = (int) (displayMetrics.widthPixels / displayMetrics.density);

//        return "{" + screenWidth + "," + screenHeight + "}";
    }

    private void setupRelatedProductHListview() {

        adapter = new RelatedProductListAdapter(mContext, related_product_arrayList);
        related_product_hlistview.setAdapter(adapter);

    }

    private void setupAlsopurchasedHListview() {

        alsoPurchasedAdapter = new RelatedProductListAdapter(mContext, alsopurchased_arrayList);
        alsoPurchased_hlistview.setAdapter(alsoPurchasedAdapter);

    }

    private void setupRecentlyViwedHListview() {

        recentlyViewedAdapter = new RelatedProductListAdapter(mContext, recently_viewed_arrayList);
        recentlyViewed_hlistview.setAdapter(recentlyViewedAdapter);


    }

    public void toggle_contents(View v){

        if(v.isShown()){
            Utility.slide_up(this, v);
            v.setVisibility(View.GONE);
        }
        else{
            v.setVisibility(View.VISIBLE);
            Utility.slide_down(this, v);
        }
    }

    private void showLocationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.return_policy);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(entity.getReturn_content(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            builder.setMessage(Html.fromHtml(entity.getReturn_content()));
        }

        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            callAddToWishlistApi();
        }
    }
}