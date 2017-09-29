package com.mohit.shopebazardroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.activity.offer.TrendingSaleShowAllActivity;
import com.mohit.shopebazardroid.activity.products.ProductDetailActivity;
import com.mohit.shopebazardroid.activity.products.ProductGridActivity;
import com.mohit.shopebazardroid.activity.products.SearchResultActivity;
import com.mohit.shopebazardroid.activity.products.SubcategoryActivity;
import com.mohit.shopebazardroid.adapter.Home_BestSellersAdapter;
import com.mohit.shopebazardroid.adapter.Home_CategoriesAdapter;
import com.mohit.shopebazardroid.adapter.Home_FeatureProductsAdapter;
import com.mohit.shopebazardroid.adapter.Home_OfferOfTheDayAdapter;
import com.mohit.shopebazardroid.adapter.Home_TrendingNowAdapter;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.RecyclerItemclicklistner;
import com.mohit.shopebazardroid.model.request.BestSellingRequest;
import com.mohit.shopebazardroid.model.request.FeatureProductRequest;
import com.mohit.shopebazardroid.model.response.BannerEntity;
import com.mohit.shopebazardroid.model.response.BannerResponse;
import com.mohit.shopebazardroid.model.response.CategoryChildrens;
import com.mohit.shopebazardroid.model.response.LoginResponse;
import com.mohit.shopebazardroid.model.response.ProductEntity;
import com.mohit.shopebazardroid.model.response.ProductResponse;
import com.mohit.shopebazardroid.model.response.ProductResult;
import com.mohit.shopebazardroid.model.response.Result;
import com.mohit.shopebazardroid.model.response.SearchRequest;
import com.mohit.shopebazardroid.model.response.Userinfo;
import com.mohit.shopebazardroid.models.Category;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by msp on 21/7/16.
 */
public class HomeFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, ApiResponse, View.OnClickListener {
    public static String TAG = HomeFragment.class.getSimpleName();
    Context mContext;

    private SliderLayout mbannerSlider;
//    HListView oftd_hlistView, trendingNowHListView, categoriesHListview, best_selling_hlistview;

    private RecyclerView oftd_hlistView, trendingNowHListView, categoriesHListview, best_selling_hlistview, feature_product_hlistview;
    private RecyclerView.LayoutManager mLayoutManager_offer, mLayoutManager_trending, mLayoutManager_categories,
            mLayoutManager_best_selling, mLayoutManager_feature_product;

//     HomeOfferOFTheDayAdapter oftdAdapter;
//    TrendingNowAdapter trendingNowAdapter;
//    CategoriesAdapter categoriesAdapter;
//    BestSellingAdapter bestSellingAdapter;

    Home_OfferOfTheDayAdapter oftdAdapter;
    Home_CategoriesAdapter categoriesAdapter;
    Home_TrendingNowAdapter trendingNowAdapter;
    Home_BestSellersAdapter bestSellingAdapter;
    Home_FeatureProductsAdapter featureProductAdapter;

    ArrayList<ProductEntity> oftdArrayList;
    ArrayList<ProductEntity> trendingNowArrayList;
    ArrayList<ProductEntity> bestSellingArrayList;
    ArrayList<ProductEntity> featureProductArrayList;
    List<Category> categoriesArrayList;

    NavigationDrawerActivity activity;
    ArrayList<BannerEntity> bannerEntityArrayList;
    LinkedHashMap<String, String> bannerHashMap;
    ArrayList<ProductEntity> bannerProductArrayList;

    TextView oftdShowAllTextView, trendingShowAllTextView, bestSellingShowAllTextView, featureProductsShowAllTextView;

    int bannerCurrentPosition;
    private TextView offer_of_the_day_lbl, trending_now_lbl, categories_lbl, best_selling_lbl, feature_product_lbl;

    ImageView img_offer, img_trending_now, img_best_selling, img_feature_product;

    int themeCode;

    String storeid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.STORE_ID, "1");
    String customerid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "");
    String root_cat_id = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.ROOT_CAT_ID, "");

    RelativeLayout oftdMainRelativeLayout, trendingMainRelativeLayout, bestSellersMainRelativeLayout, featureProductMainRelativeLayout;

    private EditText searchEditText;
    private ImageView searchImageView;
    private String searchTag;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);
        mContext = getActivity();

        searchEditText = (EditText) rootView.findViewById(R.id.search_edittext);
        searchImageView = (ImageView) rootView.findViewById(R.id.search_icon);
        searchImageView.setOnClickListener(this);

        mbannerSlider = (SliderLayout) rootView.findViewById(R.id.slider);

//        oftd_hlistView = (HListView) rootView.findViewById(R.id.offer_of_the_day_hlistview);

        oftd_hlistView = (RecyclerView) rootView.findViewById(R.id.offer_of_the_day_hlistview);
        mLayoutManager_offer = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        oftd_hlistView.setLayoutManager(mLayoutManager_offer);

        best_selling_hlistview = (RecyclerView) rootView.findViewById(R.id.best_selling_hlistview);
        mLayoutManager_best_selling = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        best_selling_hlistview.setLayoutManager(mLayoutManager_best_selling);

        feature_product_hlistview = (RecyclerView) rootView.findViewById(R.id.feature_product_hlistview);
        mLayoutManager_feature_product = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        feature_product_hlistview.setLayoutManager(mLayoutManager_feature_product);


        trendingNowHListView = (RecyclerView) rootView.findViewById(R.id.trending_now_hlistview);
        mLayoutManager_trending = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        trendingNowHListView.setLayoutManager(mLayoutManager_trending);

        categoriesHListview = (RecyclerView) rootView.findViewById(R.id.categories_hlistview);
        mLayoutManager_categories = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        categoriesHListview.setLayoutManager(mLayoutManager_categories);

        offer_of_the_day_lbl = (TextView) rootView.findViewById(R.id.offer_of_the_day_lbl);
        offer_of_the_day_lbl.setTypeface(SplashActivity.opensans_regular);

        img_offer = (ImageView) rootView.findViewById(R.id.img_offer);
        img_trending_now = (ImageView) rootView.findViewById(R.id.img_trending_now);
        img_best_selling = (ImageView) rootView.findViewById(R.id.img_best_selling);

        trending_now_lbl = (TextView) rootView.findViewById(R.id.trending_now_lbl);
        trending_now_lbl.setTypeface(SplashActivity.opensans_regular);


        best_selling_lbl = (TextView) rootView.findViewById(R.id.best_selling_lbl);
        best_selling_lbl.setTypeface(SplashActivity.opensans_regular);

        feature_product_lbl = (TextView) rootView.findViewById(R.id.feature_product_lbl);
        feature_product_lbl.setTypeface(SplashActivity.opensans_regular);

        categories_lbl = (TextView) rootView.findViewById(R.id.categories_lbl);
        categories_lbl.setTypeface(SplashActivity.opensans_regular);


        oftdShowAllTextView = (TextView) rootView.findViewById(R.id.offer_of_the_day_showall_lbl);
        oftdShowAllTextView.setTypeface(SplashActivity.opensans_regular);
        trendingShowAllTextView = (TextView) rootView.findViewById(R.id.trending_now_showall_lbl);
        trendingShowAllTextView.setTypeface(SplashActivity.opensans_regular);
        bestSellingShowAllTextView = (TextView) rootView.findViewById(R.id.best_selling_showall_lbl);
        bestSellingShowAllTextView.setTypeface(SplashActivity.opensans_regular);
        featureProductsShowAllTextView = (TextView) rootView.findViewById(R.id.feature_product_showall_lbl);
        featureProductsShowAllTextView.setTypeface(SplashActivity.opensans_regular);


//        oftdShowAllTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
//        trendingShowAllTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
//        bestSellingShowAllTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
//        featureProductsShowAllTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        oftdMainRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.rel_offer);
        trendingMainRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.rel_trending);
        bestSellersMainRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.rel_bestselling);
        featureProductMainRelativeLayout = (RelativeLayout) rootView.findViewById(R.id.rel_featureproduct);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        activity = (NavigationDrawerActivity) getActivity();
        categoriesArrayList = activity.getCategoryArraylist();

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });


        //setup banner and advertisement slider
//        setupBannerSlider();


//        HTTPWebRequest.BannerHome(mContext, storeid, AppConstants.APICode.BANNERHOME, this);

        setupCategoriesHListview();

        oftd_hlistView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
//                intent.putExtra(ProductEntity.KEY_OBJECT, oftdArrayList.get(i));
                        intent.putExtra("product", oftdArrayList.get(position).getProduct_id());
                        startActivity(intent);
                    }
                }));

//        oftd_hlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                startActivity(new Intent(mContext, TrendingSaleShowAllActivity.class));
//                Intent intent = new Intent(mContext, ProductDetailActivity.class);
////                intent.putExtra(ProductEntity.KEY_OBJECT, oftdArrayList.get(i));
//                intent.putExtra("product", oftdArrayList.get(i).getProduct_id());
//                startActivity(intent);
//
//            }
//        });

        trendingNowHListView.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

//                startActivity(new Intent(mContext, ProductDetailActivity.class));
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
//                intent.putExtra(ProductEntity.KEY_OBJECT, trendingNowArrayList.get(i));
                        intent.putExtra("product", trendingNowArrayList.get(position).getProduct_id());
                        startActivity(intent);
                    }
                }));


        best_selling_hlistview.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                startActivity(new Intent(mContext, ProductDetailActivity.class));
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
//                intent.putExtra(ProductEntity.KEY_OBJECT, bestSellingArrayList.get(i));
                        intent.putExtra("product", bestSellingArrayList.get(position).getProduct_id());
                        startActivity(intent);
                    }
                }));


        feature_product_hlistview.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
//                startActivity(new Intent(mContext, ProductDetailActivity.class));
                        Intent intent = new Intent(mContext, ProductDetailActivity.class);
//                intent.putExtra(ProductEntity.KEY_OBJECT, bestSellingArrayList.get(i));
                        intent.putExtra("product", featureProductArrayList.get(position).getProduct_id());
                        startActivity(intent);
                    }
                }));


        categoriesHListview.addOnItemTouchListener(new RecyclerItemclicklistner(mContext, new
                RecyclerItemclicklistner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        Category childrens = categoriesArrayList.get(position);
                        if (childrens.getSubCategory().size() > 0) {
                            Intent intent = new Intent(mContext, SubcategoryActivity.class);
                            intent.putExtra(CategoryChildrens.KEY_OBJECT, childrens);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(mContext, ProductGridActivity.class);
                            intent.putExtra(CategoryChildrens.KEY_ID, childrens.getCat_id());
                            intent.putExtra(CategoryChildrens.KEY_NAME, childrens.getCat_name());
                            intent.putExtra(CategoryChildrens.KEY_TYPE, 0);
                            startActivity(intent);
                        }
                    }
                }));

        oftdShowAllTextView.setOnClickListener(this);
        trendingShowAllTextView.setOnClickListener(this);
        bestSellingShowAllTextView.setOnClickListener(this);
        featureProductsShowAllTextView.setOnClickListener(this);

    }

    private void performSearch() {
        searchTag = searchEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(searchTag)){
            SearchRequest request = new SearchRequest();
            request.setStore_id(storeid);
            request.setCustomer_id(customerid);
            request.setSearch(searchTag);
            HTTPWebRequest.Search(mContext, request, AppConstants.APICode.SEARCH,this, getFragmentManager());
        }
        else
            Utility.toastMessage(mContext, "Search tag cannot be empty");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setupCategoriesHListview() {

        /*categoriesArrayList = new ArrayList<>();
        CategoryChildrens entity;
        for(int i=0; i<5; i++)
        {
            entity = new CategoryChildrens();
            entity.setId(String.valueOf(i));
            entity.setThumb("http://www.adweek.com/files/imagecache/node-detail/news_article/android-kitkat-hed3-2013.jpg");
            categoriesArrayList.add(entity);
        }*/

//        categoriesAdapter = new Home_CategoriesAdapter(mContext, categoriesArrayList);
//        categoriesHListview.setAdapter(categoriesAdapter);

    }

    private void setupTrendingNowHListview() {
//        https://fscl01.fonpit.de/userfiles/4800583/image/Android_lollipop-w628.jpg

        /*trendingNowArrayList = new ArrayList<>();
        ProductEntity entity;
        for(int i=0; i<5; i++)
        {
            entity = new ProductEntity();
            entity.setProduct_id(String.valueOf(i));
            entity.setImageurl("https://fscl01.fonpit.de/userfiles/4800583/image/Android_lollipop-w628.jpg");
            trendingNowArrayList.add(entity);
        }*/
        trendingNowAdapter = new Home_TrendingNowAdapter(mContext, trendingNowArrayList);
        trendingNowHListView.setAdapter(trendingNowAdapter);

    }

    private void setupOfferOfTheDayHListview() {

        /*oftdArrayList = new ArrayList<>();
        ProductEntity entity;
        for(int i=0; i<5; i++)
        {
            entity = new ProductEntity();
            entity.setProduct_id(String.valueOf(i));
            entity.setImageurl("http://www.androiddata-recovery.com/blog/wp-content/uploads/2015/08/podcast_featured_3.jpg");
            oftdArrayList.add(entity);
        }*/

        oftdAdapter = new Home_OfferOfTheDayAdapter(mContext, oftdArrayList);
        oftd_hlistView.setAdapter(oftdAdapter);
    }

    private void setupBestSellingHListview() {
//        https://fscl01.fonpit.de/userfiles/4800583/image/Android_lollipop-w628.jpg

        /*trendingNowArrayList = new ArrayList<>();
        ProductEntity entity;
        for(int i=0; i<5; i++)
        {
            entity = new ProductEntity();
            entity.setProduct_id(String.valueOf(i));
            entity.setImageurl("https://fscl01.fonpit.de/userfiles/4800583/image/Android_lollipop-w628.jpg");
            trendingNowArrayList.add(entity);
        }*/
        bestSellingAdapter = new Home_BestSellersAdapter(mContext, bestSellingArrayList);
        best_selling_hlistview.setAdapter(bestSellingAdapter);

    }

    private void setupFeatureProductHListview() {
        featureProductAdapter = new Home_FeatureProductsAdapter(mContext, featureProductArrayList);
        feature_product_hlistview.setAdapter(featureProductAdapter);
        feature_product_hlistview.setVisibility(View.VISIBLE);
    }


    private void setupBannerSlider() {

        /*HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("jellybean", "http://samsung-updates.com/wp-content/uploads/2013/11/jellybean43.jpg");
        url_maps.put("kitket", "http://cdn.thedroidguy.com/wp-content/uploads/2013/10/Android-KitKat-4.4.jpg");
        url_maps.put("lollypop", "http://www.notebookcheck.net/fileadmin/Notebooks/News/_nc2/Google_Android_5.0.1_Lollipop_update_now_available.jpg");
        url_maps.put("Marshmallow", "http://androidmarshmallow.androidmarshmall.netdna-cdn.com/wp-content/uploads/2016/01/Marshmallow_0.33392800_1450263759__thumb.jpg");
        url_maps.put("All version", "http://ungaldrona.com/wp-content/uploads/2015/08/android_os_version1.png");*/

        for (String name : bannerHashMap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(mContext);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(bannerHashMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mbannerSlider.addSlider(textSliderView);

            mbannerSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mbannerSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mbannerSlider.setCustomAnimation(new DescriptionAnimation());
//            mbannerSlider.setCustomAnimation(new ImageSlider_DescriptionAnimation());
            mbannerSlider.setDuration(5000);
            mbannerSlider.addOnPageChangeListener(this);
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
//        Utility.toastMessage(mContext, "Slider click : "+bannerCurrentPosition);
        String comasepratedCategoryId = "";
        BannerEntity bannerEntity = bannerEntityArrayList.get(bannerCurrentPosition);
        ArrayList<String> arrayList = bannerEntity.getCategories();
        String tempCatid = "";
        for (int i = 0; i < arrayList.size(); i++) {
            if (i != 0)
                comasepratedCategoryId = comasepratedCategoryId + ",";

            comasepratedCategoryId = comasepratedCategoryId + arrayList.get(i);
        }
        Log.d(TAG, comasepratedCategoryId);
        Intent intent = new Intent(mContext, ProductGridActivity.class);
        intent.putExtra(CategoryChildrens.KEY_ID, comasepratedCategoryId);
        intent.putExtra(CategoryChildrens.KEY_NAME, bannerEntity.getTitle());
        intent.putExtra(CategoryChildrens.KEY_TYPE, 1);
        startActivity(intent);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bannerCurrentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {
        if (TextUtils.isEmpty(response)) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }
        Gson gson = new GsonBuilder().serializeNulls().create();
        switch (apiCode) {
            case AppConstants.APICode.USER_DETAILS:
                LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);

                if (loginResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (loginResponse.getStatus().equalsIgnoreCase("success")) {
                    Result result = loginResponse.getResult();
                    Userinfo userinfo = result.getUserinfo();
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.SESSIONID, userinfo.getSessionId());
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID, "" + userinfo.getShoppingcartid());
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.IS_NOTIFICATION, userinfo.getIs_notification());
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "" + userinfo.getItems_count());
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, "" + userinfo.getItems_qty());

                }

                break;

            case AppConstants.APICode.BANNERHOME:
                BannerResponse bannerResponse = new Gson().fromJson(response, BannerResponse.class);
                if (bannerResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (bannerResponse.getStatus().equalsIgnoreCase("success")) {
                    try {
                        if (bannerEntityArrayList != null && bannerEntityArrayList.size() > 0)
                            bannerEntityArrayList.clear();

                        bannerEntityArrayList = bannerResponse.getResult().getBanner();
                        bannerHashMap = new LinkedHashMap<>(bannerEntityArrayList.size());
                        for (BannerEntity bannerEntity : bannerEntityArrayList) {
                            Log.d(TAG, bannerEntity.toString());
                            bannerHashMap.put(bannerEntity.getTitle(), bannerEntity.getImage());
                        }
                        setupBannerSlider();
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                }

                FeatureProductRequest featureProductRequest = new FeatureProductRequest();
                featureProductRequest.setStore_id(storeid);
                featureProductRequest.setCustomer_id(customerid);
                featureProductRequest.setPage(String.valueOf(1));
                featureProductRequest.setPagesize(String.valueOf(10));

                HTTPWebRequest.FeatureProducts(mContext, featureProductRequest, AppConstants.APICode.FEATURE_PRODUCT, this);


                /*OfferOfTheDayRequest offerOfTheDayRequest = new OfferOfTheDayRequest();
                offerOfTheDayRequest.setStore_id(storeid);
                offerOfTheDayRequest.setCustomer_id(customerid);
                offerOfTheDayRequest.setRoot_category_id(root_cat_id);
                offerOfTheDayRequest.setPage(String.valueOf(1));
                offerOfTheDayRequest.setPagesize(String.valueOf(10000));

                HTTPWebRequest.OfferOfTheDayHome(mContext, offerOfTheDayRequest, AppConstants.APICode.OFFER_OFTHE_DAY, this);*/

                break;
            /*case AppConstants.APICode.OFFER_OFTHE_DAY:
                ProductResponse oftdResponse = new Gson().fromJson(response, ProductResponse.class);
                if (oftdResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (oftdResponse.getStatus().equalsIgnoreCase("success")
                        && oftdResponse.getResult().getProductlist() != null
                        && oftdResponse.getResult().getProductlist().size() > 0) {
                    oftdMainRelativeLayout.setVisibility(View.VISIBLE);
                    oftdArrayList = oftdResponse.getResult().getProductlist();
                    setupOfferOfTheDayHListview();
                } else {
//                    Utility.toastMessage(mContext, "No product Found");
                    oftdMainRelativeLayout.setVisibility(View.GONE);
                }

                TrendingNowRequest trandingRequest = new TrendingNowRequest();
                trandingRequest.setRoot_category_id(root_cat_id);
                trandingRequest.setStore_id(storeid);
                trandingRequest.setCustomer_id(customerid);
                trandingRequest.setPage(String.valueOf(1));
                trandingRequest.setPagesize(String.valueOf(10000));

                HTTPWebRequest.TrendingnowHome(mContext, trandingRequest, AppConstants.APICode.TRENDING_NOW, this);

                break;*/
            /*case AppConstants.APICode.TRENDING_NOW:
                ProductResponse trendingNowResponse = new Gson().fromJson(response, ProductResponse.class);
                if (trendingNowResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (trendingNowResponse.getStatus().equalsIgnoreCase("success")
                        && trendingNowResponse.getResult().getProductlist() != null
                        && trendingNowResponse.getResult().getProductlist().size() > 0) {
                    trendingMainRelativeLayout.setVisibility(View.VISIBLE);
                    trendingNowArrayList = trendingNowResponse.getResult().getProductlist();
                    setupTrendingNowHListview();
                } else {
//                    Utility.toastMessage(mContext, "No product Found");
                    trendingMainRelativeLayout.setVisibility(View.GONE);
                }



                break;*/

            case AppConstants.APICode.FEATURE_PRODUCT:
                ProductResponse featureProductResponse = new Gson().fromJson(response, ProductResponse.class);
                if (featureProductResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (featureProductResponse.getStatus().equalsIgnoreCase("success")
                        && featureProductResponse.getResult().getProductlist() != null
                        && featureProductResponse.getResult().getProductlist().size() > 0) {
                    featureProductMainRelativeLayout.setVisibility(View.VISIBLE);
                    featureProductArrayList = featureProductResponse.getResult().getProductlist();
                    setupFeatureProductHListview();
                } else {
//                    Utility.toastMessage(mContext, "No product Found");
                    featureProductMainRelativeLayout.setVisibility(View.GONE);
                }

                BestSellingRequest bestSellingRequest = new BestSellingRequest();
                bestSellingRequest.setStore_id(storeid);
                bestSellingRequest.setCustomer_id(customerid);
                bestSellingRequest.setPage(String.valueOf(1));
                bestSellingRequest.setPagesize(String.valueOf(10000));

                HTTPWebRequest.BestSellingHome(mContext, bestSellingRequest, AppConstants.APICode.BEST_SELLER, this);

                break;

            case AppConstants.APICode.BEST_SELLER:
                ProductResponse bestSellingResponse = new Gson().fromJson(response, ProductResponse.class);
                if (bestSellingResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(mContext, LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (bestSellingResponse.getStatus().equalsIgnoreCase("success")
                        && bestSellingResponse.getResult().getProductlist() != null
                        && bestSellingResponse.getResult().getProductlist().size() > 0) {
                    bestSellersMainRelativeLayout.setVisibility(View.VISIBLE);
                    bestSellingArrayList = bestSellingResponse.getResult().getProductlist();
                    setupBestSellingHListview();
                } else {
//                    Utility.toastMessage(mContext, "No product Found");
                    bestSellersMainRelativeLayout.setVisibility(View.GONE);
                }

                break;

            case AppConstants.APICode.SEARCH:
                ProductResponse searchResponse = new Gson().fromJson(response, ProductResponse
                        .class);
                if (searchResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                    return;
                }

                if (searchResponse.getStatus().equalsIgnoreCase("success")) {
                    ProductResult productResult = searchResponse.getResult();
                    if (productResult.getProductlist() != null && productResult.getProductlist()
                            .size() > 0) {
                        Intent intent = new Intent(mContext, SearchResultActivity.class);
                        intent.putExtra(SearchResultActivity.KEY_SEARCH_TITLE, searchTag);
                        intent.putExtra(SearchResultActivity.KEY_SEARCH_RESULT, productResult);
                        startActivity(intent);
                        searchTag = "";
                        searchEditText.setText("");

                    }
                } else {
                    Utility.toastMessage(mContext, searchResponse.getResult().getMessage());
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
        switch (view.getId()) {
            case R.id.search_icon:
                performSearch();
                break;
            case R.id.offer_of_the_day_showall_lbl:
                Intent oftdIntent = new Intent(mContext, TrendingSaleShowAllActivity.class);
                oftdIntent.putExtra(AppConstants.INTENTDATA.TYPE, 0);
                startActivity(oftdIntent);
                break;
            case R.id.trending_now_showall_lbl:
                Intent trendingIntent = new Intent(mContext, TrendingSaleShowAllActivity.class);
                trendingIntent.putExtra(AppConstants.INTENTDATA.TYPE, 1);
                startActivity(trendingIntent);
                break;
            case R.id.best_selling_showall_lbl:
                Intent bestSellingIntent = new Intent(mContext, TrendingSaleShowAllActivity.class);
                bestSellingIntent.putExtra(AppConstants.INTENTDATA.TYPE, 2);
                startActivity(bestSellingIntent);
                break;
            case R.id.feature_product_showall_lbl:
                Intent featureProductIntent = new Intent(mContext, TrendingSaleShowAllActivity.class);
                featureProductIntent.putExtra(AppConstants.INTENTDATA.TYPE, 3);
                startActivity(featureProductIntent);
                break;
        }
    }
}
