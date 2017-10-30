/*
 * Copyright 2015 Vikram Kakkar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mohit.shopebazardroid.activity.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Checkout.CartActivity;
import com.mohit.shopebazardroid.activity.login_registration.LoginActivity;
import com.mohit.shopebazardroid.activity.login_registration.RegistrationActivity;
import com.mohit.shopebazardroid.activity.login_registration.SplashActivity;
import com.mohit.shopebazardroid.activity.products.ProductGridActivity;
import com.mohit.shopebazardroid.activity.products.SearchResultActivity;
import com.mohit.shopebazardroid.activity.products.SubcategoryActivity;
import com.mohit.shopebazardroid.adapter.NavigationDrawerAdapter;
import com.mohit.shopebazardroid.dialog.LogoutDialog;
import com.mohit.shopebazardroid.fragment.AddressFragment;
import com.mohit.shopebazardroid.fragment.FeedbackFragment;
import com.mohit.shopebazardroid.fragment.HomeFragment;
import com.mohit.shopebazardroid.fragment.MyRewardsFragment;
import com.mohit.shopebazardroid.fragment.MyWishListFragment;
import com.mohit.shopebazardroid.fragment.NotificationFragment;
import com.mohit.shopebazardroid.fragment.OrderHistoryFragment;
import com.mohit.shopebazardroid.fragment.SettingFragment;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.listener.LogoutListner;
import com.mohit.shopebazardroid.model.request.UserDetailsRequest;
import com.mohit.shopebazardroid.model.response.CurrencyEntity;
import com.mohit.shopebazardroid.model.response.LoginResponse;
import com.mohit.shopebazardroid.model.response.ProductResponse;
import com.mohit.shopebazardroid.model.response.ProductResult;
import com.mohit.shopebazardroid.model.response.Result;
import com.mohit.shopebazardroid.model.response.Userinfo;
import com.mohit.shopebazardroid.models.BasicCMS;
import com.mohit.shopebazardroid.models.Category;
import com.mohit.shopebazardroid.models.Environment;
import com.mohit.shopebazardroid.models.PaymentInfo;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;
import com.mohit.shopebazardroid.utility.WebView_Detail;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample usage
 */
public class NavigationDrawerActivity extends BaseActivity implements
        /*OnNavigationMenuEventListener,*/ LogoutListner, View.OnClickListener, AdapterView
        .OnItemClickListener, ApiResponse {

    public static final String TAG = NavigationDrawerActivity.class.getSimpleName();
    Context mContext;

    boolean isdrawerclose = true;
    private int loadAgain = 0;
    Toolbar toolbar;
    DrawerLayout drawer;

    // Navigation menu
//    SublimeNavigationView snv;
    ListView navDrawerListview;
    LogoutDialog logoutDialog;
//    MaterialSearchView searchView;

    ImageView updateProfileImageView, closeImageView;
    public static TextView userName;
    NavigationDrawerAdapter adapter;
    List<Category> arrayList;
    List<Category> liveCategoryArraylist;
    String[] strings;
    List<BasicCMS> cmsArrayList;
    List<CurrencyEntity> currencyArrayList;
    String searchTag = "";
    public static int height, width;
    private FrameLayout profileFrameLayout;
    Menu menu;
    View cartBadget;
    public List<Category> getCategoryArraylist() {
        return liveCategoryArraylist;
    }

    public List<CurrencyEntity> getCurrencyArrayList() {
        return currencyArrayList;
    }

    boolean firstTime = true;
    TextView textView;
    boolean doubleBackToExitPressedOnce = false;

    String TITLE = "title", URL = "url";

    int themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 1);

    LinearLayout navigation_view;


    private RelativeLayout userDetailsRelativeLayout;
    private void setCurrentTheme() {
        int theme_code = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 1);
        setTheme(R.style.AppTheme);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCurrentTheme();
        setContentView(R.layout.activity_main);

        themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 0);

        mContext = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        profileFrameLayout = (FrameLayout) findViewById(R.id.profile_framelayout);
        userDetailsRelativeLayout = (RelativeLayout) findViewById(R.id.user_details_rleativelayout);
        userName = (TextView) findViewById(R.id.tvNamePlate);
        userName.setTypeface(SplashActivity.montserrat_Regular);
        userName.setText(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .NAME, ""));
        updateProfileImageView = (ImageView) findViewById(R.id.update_profile);
        closeImageView = (ImageView) findViewById(R.id.menu_close_icon);
//        searchView = (MaterialSearchView) findViewById(R.id.search_view);
//        setSearchListners();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string
                .navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navDrawerListview = (ListView) findViewById(R.id.nav_listview);

        arrayList = new ArrayList<>();
        Category childrens = new Category();
        childrens.setCat_name("Home");
        arrayList.add(childrens);
        /*adapter = new NavigationDrawerAdapter(mContext, arrayList);
        navDrawerListview.setAdapter(adapter);*/
        navDrawerListview.setOnItemClickListener(this);
        updateProfileImageView.setOnClickListener(this);
        closeImageView.setOnClickListener(this);
        profileFrameLayout.setOnClickListener(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;

        navigation_view = (LinearLayout) findViewById(R.id.navigation_view);


        setupEnvironment();

    }


    private void setupEnvironment(){
        HTTPWebRequest.Basic(mContext, getFirebaseId(),AppConstants.APICode.BASIC, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        cartBadget = inflater.inflate(R.layout.cart_badge_actionbar, null);
        cartBadget.setLayoutParams(new RelativeLayout.LayoutParams(item.getIcon()
                .getIntrinsicWidth() + 50, item.getIcon().getIntrinsicHeight() + 20));

        textView = (TextView) cartBadget.findViewById(R.id.counter);
        String itemQTY = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0");
        textView.setText(String.format("%.0f", Double.parseDouble(itemQTY)));
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
            case R.id.action_cart:
//                Utility.toastMessage(mContext, "Cart clicked");
                startActivity(new Intent(mContext, CartActivity.class));
//                startActivity(new Intent(mContext, ActivityStripePaymentGateway.class));
                return true;
        }
    }

    private void hideMenuItem(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    /**
     * Information about the library
     */

    public void updateCartCount(){
        if(textView != null){
            textView.setText(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        updateCartCount();

        if(isUserLogin()){
            userName.setText(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                    .NAME, "John doe"));
            userDetailsRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            userDetailsRelativeLayout.setVisibility(View.GONE);
        }


        if(adapter != null && arrayList != null){

            HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);

            if(fragment == null && !isUserLogin()){
//                toolbar.setTitle(R.string.app_name);

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new HomeFragment(),
                                HomeFragment.TAG)
                        .commit();
            } else{

            }

            if(isUserLogin()){
                Category childrens = new Category();
                childrens.setCat_name("Logout");
                arrayList.set(arrayList.size()-1, childrens);
                adapter.notifyDataSetChanged();
            } else {
                Category childrens = new Category();
                childrens.setCat_name("Login");
                arrayList.set(arrayList.size()-1, childrens);
                adapter.notifyDataSetChanged();
            }
        }

        String email = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                .EMAIL, "");
        UserDetailsRequest request = new UserDetailsRequest();
        request.setEmail(email);
        request.setStore_id(getStoreID());
        if (!TextUtils.isEmpty(email))
            HTTPWebRequest.UserDetail(mContext, request, AppConstants.APICode.USER_DETAILS,
                    this, getSupportFragmentManager());

    }

    @Override
    public void onLogoutCancel() {
        logoutDialog.dismiss();

        HomeFragment  fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);

    }

    @Override
    public void onLogoutSubmit() {
        logoutDialog.dismiss();
        setUserLoggedOut();
//        toolbar.setTitle(R.string.app_name);

        Category childrens = new Category();
        childrens.setCat_name("Login");
        arrayList.set(arrayList.size()-1, childrens);
        adapter.notifyDataSetChanged();

        userDetailsRelativeLayout.setVisibility(View.GONE);
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
        if(fragment ==  null){
            MenuItem item = menu.findItem(R.id.action_search);
            item.setVisible(true);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_frame, new HomeFragment(),
                            HomeFragment.TAG)
                    .commit();
        }

        MyApplication.clearPreference();
        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.GCM_TOKEN, FirebaseInstanceId.getInstance().getToken());
        textView.setText(MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
//        startActivity(new Intent(this, LoginActivity.class));
//        this.finish();

        if(getIs_login_compulsory()){
            startActivity(new Intent(this, LoginActivity.class));
//            this.finish();
        }

//        HTTPWebRequest.Basic(mContext, getFirebaseId(),AppConstants.APICode.BASIC, this);
        setupEnvironment();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent
                    .EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
//                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.update_profile:
//                Utility.toastMessage(mContext, "Update profile click");
                RegistrationActivity.isUpdate = true;
                startActivity(new Intent(mContext, RegistrationActivity.class));
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_close_icon:
                drawer.closeDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        MenuItem item = menu.findItem(R.id.action_search);


        Category childrens = arrayList.get(i);
        if (childrens.getSubCategory()!= null) {
            if (childrens.getSubCategory().size() > 0) {
                Intent intent = new Intent(mContext, SubcategoryActivity.class);
                intent.putExtra(Category.KEY_OBJECT, childrens);
                startActivity(intent);
            } else {
                Intent intent = new Intent(mContext, ProductGridActivity.class);
                intent.putExtra(Category.KEY_ID, childrens.getCat_id());
                intent.putExtra(Category.KEY_NAME, childrens.getCat_name());
                intent.putExtra(Category.KEY_TYPE, 0);
                startActivity(intent);
            }
        } else {
            item.setVisible(false);
            cartBadget.setVisibility(View.VISIBLE);

            if (childrens.getCat_name().equalsIgnoreCase("home")) {
                getSupportActionBar().setTitle(R.string.app_name);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new HomeFragment(), HomeFragment.TAG)
                        .commit();
            } else if (childrens.getCat_name().equalsIgnoreCase("order history")) {
                getSupportActionBar().setTitle("Order History");
                item.setVisible(false);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new OrderHistoryFragment(),
                                OrderHistoryFragment.TAG)
                        .commit();

                if(!isUserLogin()){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
            } else if (childrens.getCat_name().equalsIgnoreCase("Manage Address")) {
                getSupportActionBar().setTitle("Manage Address");
                item.setVisible(false);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new AddressFragment(), AddressFragment.TAG)
                        .commit();
                if(!isUserLogin()){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
            } else if (childrens.getCat_name().equalsIgnoreCase("My WishList")) {
                getSupportActionBar().setTitle(childrens.getCat_name());
                item.setVisible(false);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new MyWishListFragment(), MyWishListFragment.TAG)
                        .commit();
                if(!isUserLogin()){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
            } else if (childrens.getCat_name().equalsIgnoreCase("notification")) {
                getSupportActionBar().setTitle("Notification");
                item.setVisible(false);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new NotificationFragment(), NotificationFragment.TAG)
                        .commit();
            } else if (childrens.getCat_name().equalsIgnoreCase("setting")) {
                getSupportActionBar().setTitle("Setting");
                item.setVisible(false);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new SettingFragment(), SettingFragment.TAG)
                        .commit();
            } else if (childrens.getCat_name().equalsIgnoreCase("feedback")) {
                getSupportActionBar().setTitle("Feedback");
                item.setVisible(false);
//                cartBadget.setVisibility(View.GONE);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new FeedbackFragment(), FeedbackFragment.TAG)
                        .commit();
            }else if (childrens.getCat_name().equalsIgnoreCase("My Reward Points")) {
                toolbar.setTitle("My Reward Points");
                item.setVisible(false);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new MyRewardsFragment(), MyRewardsFragment.TAG)
                        .commit();
                if(!isUserLogin()){
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
            } else if (childrens.getCat_name().equalsIgnoreCase("rate and review")) {
                // redirect to google playstore
            } else if (childrens.getCat_name().equalsIgnoreCase("logout")) {
//                toolbar.setTitle(R.string.app_name);
                logoutDialog = new LogoutDialog();
                logoutDialog.setListner(this);
                logoutDialog.show(getSupportFragmentManager(), LogoutDialog.TAG);

                HomeFragment  fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);

            } else if (childrens.getCat_name().equalsIgnoreCase("Login")) {
//                toolbar.setTitle(R.string.app_name);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            } else {

                item.setVisible(false);
//                cartBadget.setVisibility(View.GONE);

                String clickedMenu = childrens.getCat_name();
                BasicCMS cms;
                for (int j = 0; j < cmsArrayList.size(); j++) {
                    cms = cmsArrayList.get(j);
                    if (clickedMenu.equalsIgnoreCase(cms.getPage_title())) {
                        Log.e("Urlll ", "--->>>> " + cms.getContenturl());


                        Bundle bundle = new Bundle();

                        String title = cms.getPage_title();
                        getSupportActionBar().setTitle(title);
//                        toolbar.setTitle(title);
                        String url = cms.getContenturl();

                        bundle.putString("url", url);

                        WebView_Detail webView_detail = new WebView_Detail();
                        webView_detail.setArguments(bundle);

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_frame, webView_detail, WebView_Detail.TAG)
                                .commit();


                    }
                }
            }


        }
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {

        if (response == null) {
            Utility.toastMessage(mContext, R.string.host_not_reachable);
            return;
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        switch (apiCode) {
            case AppConstants.APICode.BASIC:

                Type type = new TypeToken<BaseResponse<Environment>>(){}.getType();
                BaseResponse<Environment> baseResponse = new Gson().fromJson(response, type);
                Environment environment = baseResponse.getInfo();
                if(cmsArrayList == null){
                    cmsArrayList = new ArrayList<>();
                }

                cmsArrayList.addAll(environment.getBasicCMSPage());

                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, environment.getImagePrefix());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.DISPLAY_CURRENCY_CODE, environment.getCurrency_sign());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID, String.valueOf(environment.getCart_id()));
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOKEN, environment.getToken());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, String.valueOf(environment.getCartCount()));
                if(textView != null){
                    textView.setText(String.valueOf(environment.getCartCount()));
                }

                PaymentInfo paymentInfo = environment.getPaymentInfo();
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.MERCHANT_KEY, paymentInfo.getKey());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.MERCHANT_SALT, paymentInfo.getSalt());
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.MERCHANT_IS_LIVE_MODE, String.valueOf(paymentInfo.getIs_live_mode()));

                HTTPWebRequest.CategoryList(this, AppConstants.APICode.CATEGORYLIST, this, getSupportFragmentManager());

                break;
            case AppConstants.APICode.USER_DETAILS:
                LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);

                if (loginResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return;
                }


                if (loginResponse.getStatus().equalsIgnoreCase("success")) {
                    Result result = loginResponse.getResult();
                    Userinfo userinfo = result.getUserinfo();
                    String cartid = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0");
                    MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.SESSIONID, userinfo.getSessionId());
                    if((cartid.equalsIgnoreCase("0") && userinfo.getShoppingcartid() != 0)){
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.IS_NOTIFICATION, userinfo.getIs_notification());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID,"" + userinfo.getShoppingcartid());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "" + userinfo.getItems_count());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, "" + userinfo.getItems_qty());
                    }

                    String itemCount = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0");
                    String itemQTY = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, "0");
                    cartid = String.valueOf(userinfo.getShoppingcartid());
                    if(cartid.equalsIgnoreCase("0")){
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID,"" + cartid);
                        itemCount = "0";
                        itemQTY = "0";
                    }

                    if(!itemCount.equalsIgnoreCase(userinfo.getItems_count()) || !itemQTY.equalsIgnoreCase(userinfo.getItems_qty())){
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "" + userinfo.getItems_count());
                        MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, "" + userinfo.getItems_qty());
                    }

                    textView.setText("" + MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0"));
                }

                break;

            case AppConstants.APICode.CATEGORYLIST:
                loadAgain = 1;

                if(!TextUtils.isEmpty(response)){

                    Type basicType = new TypeToken<BaseResponse<List<Category>>>(){}.getType();
                    BaseResponse<List<Category>> categoryBaseResponse = new Gson().fromJson(response, basicType);
                    if(categoryBaseResponse.getStatus() == 1){
                        List<Category> categoryList = categoryBaseResponse.getInfo();

                        if (arrayList != null && arrayList.size() > 0)
                            arrayList.clear();

                            Category childrensHome = new Category();
                            childrensHome.setCat_name("Home");
                            arrayList.add(childrensHome);

                            arrayList.addAll(categoryList);
                            liveCategoryArraylist = categoryList;
                            String[] navItems = mContext.getResources().getStringArray(R.array
                                    .nav_menu_item);

                            Category childrens;
                        for (String navItem : navItems) {
                            childrens = new Category();
                            childrens.setCat_name(navItem);
                            arrayList.add(childrens);
                        }

                            BasicCMS cms;
                        if(cmsArrayList!= null){
                            for (int i = 0; i < cmsArrayList.size(); i++) {
                                cms = cmsArrayList.get(i);
                                childrens = new Category();
                                childrens.setCat_name(cms.getPage_title());
                                arrayList.add(childrens);
                            }
                        }


                            childrens = new Category();
                            if(isUserLogin())
                                childrens.setCat_name("Logout");
                            else
                                childrens.setCat_name("Login");
                            arrayList.add(childrens);

                            adapter = new NavigationDrawerAdapter(mContext, arrayList);
                            navDrawerListview.setAdapter(adapter);

                    } else {
                        Toast.makeText(mContext, categoryBaseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Utility.toastMessage(mContext, R.string.host_not_reachable);
                    this.finish();
                    return;
                }



                // loading home fragment
                firstTime = false;
                String email = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys
                        .EMAIL, "");

                UserDetailsRequest req = new UserDetailsRequest();
                req.setEmail(email);
                req.setStore_id(getStoreID());

                if (!TextUtils.isEmpty(email))
                    HTTPWebRequest.UserDetail(mContext, req, AppConstants.APICode.USER_DETAILS,
                            this, getSupportFragmentManager());
//                toolbar.setTitle("Sales Genie");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, new HomeFragment(), HomeFragment.TAG)
                        .commit();


                break;
            case AppConstants.APICode.SEARCH:
                ProductResponse searchResponse = new Gson().fromJson(response, ProductResponse
                        .class);
                if (searchResponse.getCheckCustomerSubscriptionStatusResult().equalsIgnoreCase("0")) {
                    Utility.toastMessage(mContext, R.string.subscription_over);
                    MyApplication.clearPreference();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
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
                    }
                } else {
                    Utility.toastMessage(mContext, searchResponse.getResult().getMessage());
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
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}