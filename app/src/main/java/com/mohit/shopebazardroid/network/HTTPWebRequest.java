package com.mohit.shopebazardroid.network;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.model.request.AddCommentInOrderRequest;
import com.mohit.shopebazardroid.model.request.AddRemoveWishListRequest;
import com.mohit.shopebazardroid.model.request.AddReviewRequest;
import com.mohit.shopebazardroid.model.request.ApplyRewardsRequest;
import com.mohit.shopebazardroid.model.request.AreaListRequest;
import com.mohit.shopebazardroid.model.request.BestSellingRequest;
import com.mohit.shopebazardroid.model.request.CityListRequest;
import com.mohit.shopebazardroid.model.request.CustomerReviewListRequest;
import com.mohit.shopebazardroid.model.request.FeatureProductRequest;
import com.mohit.shopebazardroid.model.request.FeedbackRequest;
import com.mohit.shopebazardroid.model.request.Mobile_return;
import com.mohit.shopebazardroid.model.request.NotificationListRequest;
import com.mohit.shopebazardroid.model.request.NotificationRequest;
import com.mohit.shopebazardroid.model.request.OfferOfTheDayRequest;
import com.mohit.shopebazardroid.model.request.PayuRequest;
import com.mohit.shopebazardroid.model.request.ProductDetailRequest;
import com.mohit.shopebazardroid.model.request.ProductRequest;
import com.mohit.shopebazardroid.model.request.RatingsRequest;
import com.mohit.shopebazardroid.model.request.RegistrationRequest;
import com.mohit.shopebazardroid.model.request.RelatedProductsRequest;
import com.mohit.shopebazardroid.model.request.ReorderRequest;
import com.mohit.shopebazardroid.model.request.SetCurrencyRequest;
import com.mohit.shopebazardroid.model.request.ShippingRequest;
import com.mohit.shopebazardroid.model.request.StoreListRequest;
import com.mohit.shopebazardroid.model.request.StripePayment;
import com.mohit.shopebazardroid.model.request.TrendingNowRequest;
import com.mohit.shopebazardroid.model.request.UserDetailsRequest;
import com.mohit.shopebazardroid.model.request.VerifyCouponRequest;
import com.mohit.shopebazardroid.model.response.SearchRequest;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.HashMap;


public class HTTPWebRequest {

    public static HashMap<String, String> postDataParams;

    public static void Basic(Context context, String user_id, int apiCode, ApiResponse apiResponse) {

        String url = UrlFormetter.getURL(context, R.string.api_environment, user_id);
        new OKHttpAsyncTask(context, url, null , apiCode, true).execute(apiResponse);
    }


    public static void Login(Context context, String request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_login);
        new OKHttpAsyncTask(context, url,request, context.getString(R.string.dialog_msg_authenticating),AppConstants.APICode.LOGIN, false, fragmentManager).execute(apiResponse);
    }


    public static void ForgotPassword(Context context, String emailString, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_forgot_password, emailString);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }


    public static void Registration(Context context, String jsonRequest, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_user_register);
        new OKHttpAsyncTask(context, url, jsonRequest,  AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }



    public static void Feedback(Context context, FeedbackRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.NAME, request.getName());
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, request.getEmail());
        postDataParams.put(AppConstants.RequestDataKey.TITLE, request.getSubject());
        postDataParams.put(AppConstants.RequestDataKey.COMMENT, request.getMessage());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_FEEDBACK, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void CategoryList(Context context, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_category);
        new OKHttpAsyncTask(context, url, null, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void Products(Context context, String user_id, String categoryid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_category_product_list, user_id, categoryid);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);

    }

    public static void GetState(Context context, String countrycode, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.COUNTRY_CODE, countrycode);
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_COUNTRY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void AddressList(Context context, String userid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_get_address_list, userid);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void AddressDelete(Context context, String addressid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_delete_address, addressid);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }


    public static void setCartShippingAddress(Context context, String cart_id, String address_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_cart_set_shipping_address, cart_id, address_id);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void setCartBillingAddress(Context context, String cart_id, String address_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_cart_set_billing_address, cart_id, address_id);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }



    public static void AddressAdd(Context context, String request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url =  UrlFormetter.getURL(context, R.string.api_add_new_address);
        new OKHttpAsyncTask(context, url, request, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void AddressEdit(Context context, String request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_update_address);
        new OKHttpAsyncTask(context, url, request, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    /*public static void GetProfile(Context context, String userid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, userid);
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_GET_PROFILE, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }*/

    public static void GetProfile(Context context, String userid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_user_profile, userid);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void UpdateProfile(Context context, RegistrationRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTID, request.getUserid());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        postDataParams.put(AppConstants.RequestDataKey.FIRSTNAME, request.getFirstname());
        postDataParams.put(AppConstants.RequestDataKey.MIDDLENAME, request.getMiddlename());
        postDataParams.put(AppConstants.RequestDataKey.LASTNAME, request.getLastname());
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, request.getEmail());
        postDataParams.put(AppConstants.RequestDataKey.DEVICE_TYPE, request.getDevice_type());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_UPDATE_PROFILE, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void UpdateProfile(Context context, String jsonRequest, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_update_profie);
        new OKHttpAsyncTask(context, url, jsonRequest, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }



    public static void Search(Context context, SearchRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.SEARCH, request.getSearch());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_SEARCH, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void AddUpdateCart(Context context, String jsonRequest, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_add_update_product);
        new OKHttpAsyncTask(context, url, jsonRequest, AppConstants.DialogMessage.ADDING_PRODUCT_TO_CART, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void UpdateCart(Context context, String jsonRequest, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_update_cart);
        new OKHttpAsyncTask(context, url, jsonRequest, AppConstants.DialogMessage.UPDATING_CART, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void GetCartItems(Context context, String cart_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_get_cart_item, cart_id);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void SetShippingMethodToCart(Context context, ShippingRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getCartid());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        postDataParams.put(AppConstants.RequestDataKey.CODE, request.getCode());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_SHIPPING_METHOD, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void GetPaymentMethodList(Context context, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_cart_get_payment_method);
       new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void SetPaymentMethodToCart(Context context, String cart_id, String payment_method_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_cart_set_payment_method, cart_id, payment_method_id);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }


    public static void GetCartDetails(Context context, String cart_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_cart_order_review, cart_id);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void RemoveProductFromCart(Context context, int cart_id, int product_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_remove_cart_item, String.valueOf(cart_id), String.valueOf(product_id));
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void UpdateProductFromCart(Context context, String jsonRequest, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0"));
        postDataParams.put(AppConstants.RequestDataKey.ACTION, "" + 1);
        postDataParams.put(AppConstants.RequestDataKey.JSON_REQUEST, jsonRequest);
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_CART_UPDATE_ALL, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void GetShippingMethodList(Context context, ShippingRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getCartid());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_SHIPPING_METHOD, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void PlaceOrder(Context context, String requestJson, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_place_order);
        new OKHttpAsyncTask(context, url, requestJson,AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void OrderHistory(Context context, String user_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

       String url = UrlFormetter.getURL(context, R.string.api_order_history, user_id);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void OrderDetails(Context context, String orderid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_order_history_details, orderid);
        new OKHttpAsyncTask(context, url, AppConstants.APIURL.URL_HISTORY_ORDER_DETAIL, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void BannerHome(Context context, String store_id, int apiCode, ApiResponse apiResponse) {
        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, store_id);
//        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_BANNERHOME, apiCode, true).execute(apiResponse);
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_BANNERHOME, apiCode, false).execute(apiResponse);

    }

    public static void BannerProducts(Context context, ProductRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.CATID, request.getCatid());
        postDataParams.put(AppConstants.RequestDataKey.PAGE, request.getPage());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, request.getPagesize());

        if (request.getJson_filter() != null && request.getJson_filter().length() > 0)
            postDataParams.put(AppConstants.RequestDataKey.JSON_FILTER, request.getJson_filter());


        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_BANNER_PRODUCTS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void FeatureProducts(Context context, FeatureProductRequest offerOfTheDayRequest, int apiCode, ApiResponse apiResponse) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, offerOfTheDayRequest.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, offerOfTheDayRequest.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, String.valueOf(10));
        postDataParams.put(AppConstants.RequestDataKey.PAGE, String.valueOf(1));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_FEATURE_PRODUCTS, apiCode, false).execute(apiResponse);
    }

    public static void OfferOfTheDayHome(Context context, OfferOfTheDayRequest offerOfTheDayRequest, int apiCode, ApiResponse apiResponse) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.ROOT_CATEGORY_ID, offerOfTheDayRequest.getRoot_category_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, offerOfTheDayRequest.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, offerOfTheDayRequest.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, String.valueOf(10));
        postDataParams.put(AppConstants.RequestDataKey.PAGE, String.valueOf(1));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_OFFER_OFTHE_DAY, apiCode, false).execute(apiResponse);
    }


    public static void OfferOfTheDayShowAll(Context context, OfferOfTheDayRequest offerOfTheDayRequest, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.ROOT_CATEGORY_ID, offerOfTheDayRequest.getRoot_category_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, offerOfTheDayRequest.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, offerOfTheDayRequest.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, offerOfTheDayRequest.getPagesize());
        postDataParams.put(AppConstants.RequestDataKey.PAGE, offerOfTheDayRequest.getPage());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_OFFER_OFTHE_DAY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void OfferOfTheDayFilter(Context context, ProductRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, request.getPagesize());
        postDataParams.put(AppConstants.RequestDataKey.PAGE, request.getPage());
        if (request.getJson_filter() != null && request.getJson_filter().length() > 2)
            postDataParams.put(AppConstants.RequestDataKey.JSON_FILTER, request.getJson_filter());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_OFFER_OFTHE_DAY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void TrendingnowHome(Context context, TrendingNowRequest request, int apiCode, ApiResponse apiResponse) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.ROOT_CATEGORY_ID, request.getRoot_category_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, String.valueOf(10));
        postDataParams.put(AppConstants.RequestDataKey.PAGE, String.valueOf(1));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_TRENDINGNOW, apiCode, false).execute(apiResponse);
    }

    public static void TrendingnowShowAll(Context context, TrendingNowRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.ROOT_CATEGORY_ID, request.getRoot_category_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, request.getPagesize());
        postDataParams.put(AppConstants.RequestDataKey.PAGE, request.getPage());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_TRENDINGNOW, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void TrendingnowFilter(Context context, ProductRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, request.getPagesize());
        postDataParams.put(AppConstants.RequestDataKey.PAGE, request.getPage());
        if (request.getJson_filter() != null && request.getJson_filter().length() > 2)
            postDataParams.put(AppConstants.RequestDataKey.JSON_FILTER, request.getJson_filter());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_TRENDINGNOW, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void BestSellingHome(Context context, BestSellingRequest request, int apiCode, ApiResponse apiResponse) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, String.valueOf(10));
        postDataParams.put(AppConstants.RequestDataKey.PAGE, String.valueOf(1));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_BEST_SELLING, apiCode, false).execute(apiResponse);
    }

    public static void BestSellingShowAll(Context context, BestSellingRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, request.getPagesize());
        postDataParams.put(AppConstants.RequestDataKey.PAGE, request.getPage());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_BEST_SELLING, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void FeatureProductsShowAll(Context context, FeatureProductRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, request.getPagesize());
        postDataParams.put(AppConstants.RequestDataKey.PAGE, request.getPage());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_FEATURE_PRODUCTS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void BestSellingFilter(Context context, ProductRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, String.valueOf(10000));
        postDataParams.put(AppConstants.RequestDataKey.PAGE, String.valueOf(1));
        if (request.getJson_filter() != null && request.getJson_filter().length() > 2)
            postDataParams.put(AppConstants.RequestDataKey.JSON_FILTER, request.getJson_filter());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_BEST_SELLING, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void Notification(Context context, NotificationRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.USER_ID, request.getUserid());
        postDataParams.put(AppConstants.RequestDataKey.IS_NOTIFICATION, request.getStatus());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_NOTIFICATION, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }



    public static void VerifyCoupon(Context context, VerifyCouponRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getShoppingcartid());
        postDataParams.put(AppConstants.RequestDataKey.COUPONCODE_ORDER_REVIEW, request.getCouponcode());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_COUPON, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void SetCurrency(Context context, SetCurrencyRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getShoppingcartid());
        postDataParams.put(AppConstants.RequestDataKey.CODE, request.getCode());
        postDataParams.put(AppConstants.RequestDataKey.USER_ID, request.getUser_id());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_SET_CURRENCY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void StripePayment(Context context, StripePayment req, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, req.getShoppingcartid());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, req.getAction());
        postDataParams.put(AppConstants.RequestDataKey.CODE, req.getCode());
        postDataParams.put(AppConstants.RequestDataKey.STRIPE_OWNER, req.getCc_owner());
        postDataParams.put(AppConstants.RequestDataKey.STRIPE_TYPE, req.getCc_type());
        postDataParams.put(AppConstants.RequestDataKey.STRIPE_NUMBER, req.getCc_number());
        postDataParams.put(AppConstants.RequestDataKey.STRIPE_EXP_MONTH, req.getCc_exp_month());
        postDataParams.put(AppConstants.RequestDataKey.STRIPE_EXP_YEAR, req.getCc_exp_year());
        postDataParams.put(AppConstants.RequestDataKey.STRIPE_CID, req.getCc_cid());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_STRIPE, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void Mobile_return(Context context, Mobile_return req, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOM_SHIPMENT_ID, req.getCustom_shipment_id());
        postDataParams.put(AppConstants.RequestDataKey.SHIPMENT_STATUS, req.getShipment_status());
        postDataParams.put(AppConstants.RequestDataKey.ORDER_ID, req.getOrder_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_MOBILE_RETURN, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void Get_Setting(Context context, StoreListRequest req, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, req.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, req.getCustomer_Id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_GET_SETTINGS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void RelatedProduct(Context context, RelatedProductsRequest request, int apiCode, ApiResponse apiResponse) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.PRODUCTID, request.getProductid());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_RELATED_PRODUCT, apiCode, false).execute(apiResponse);
    }

    public static void AlsoPurchased(Context context, RelatedProductsRequest request, int apiCode, ApiResponse apiResponse) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.PRODUCTID, request.getProductid());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ALSO_PURCHASED, apiCode, false).execute(apiResponse);
    }

    public static void RecentlyViewed(Context context, RelatedProductsRequest request, int apiCode, ApiResponse apiResponse) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.PRODUCTID, request.getProductid());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_RECENTLY_VIEWED, apiCode, false).execute(apiResponse);
    }



    public static void ProductDetail(Context context, String user_id, String product_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_product_details, user_id, product_id);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void Ratings(Context context, RatingsRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_RATINGS, apiCode, false).execute(apiResponse);
    }

    public static void AddReview(Context context, AddReviewRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.NICKNAME, request.getNickname());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.TITLE, request.getTitle());
        postDataParams.put(AppConstants.RequestDataKey.DETAIL, request.getDetail());
        postDataParams.put(AppConstants.RequestDataKey.PRODUCT_ID, request.getProduct_id());
        postDataParams.put(AppConstants.RequestDataKey.OPTION_ID, request.getOption_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADD_REVIEW, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void ReviewList(Context context, CustomerReviewListRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.PRODUCT_ID, request.getProduct_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, String.valueOf(2));
        postDataParams.put(AppConstants.RequestDataKey.PAGE, String.valueOf(1));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_REVIEW_LIST, apiCode, false).execute(apiResponse);

    }

    public static void ReviewListShowAll(Context context, CustomerReviewListRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.PRODUCT_ID, request.getProduct_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.PAGE_SIZE, String.valueOf(10000));
        postDataParams.put(AppConstants.RequestDataKey.PAGE, String.valueOf(1));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_REVIEW_LIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void NotificationList(Context context, NotificationListRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_NOTIFICATION_LIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void AddCommentOrder(Context context, AddCommentInOrderRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.INCREMENT_ID, request.getIncrement_id());
        postDataParams.put(AppConstants.RequestDataKey.COMMENT, request.getComment());
        postDataParams.put(AppConstants.RequestDataKey.ORDER_STATUS, request.getOrder_status());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADD_COMMENT, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void CityList(Context context, CityListRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.COUNTRY_CODE, request.getCountrycode());

        if (request.getRegionId() != null) {
            postDataParams.put(AppConstants.RequestDataKey.REGIONID, request.getRegionId());
        }

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_CITY_LIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void AreaList(Context context, AreaListRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CITYNAME, request.getCityname());

        if (request.getCountrycode() != null) {
            postDataParams.put(AppConstants.RequestDataKey.COUNTRY_CODE, request.getCountrycode());
        }

        if (request.getRegionId() != null) {
            postDataParams.put(AppConstants.RequestDataKey.REGIONID, request.getRegionId());
        }

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_AREA_LIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void GetIntervals(Context context, String store_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, store_id);

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_GET_INTERVALS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void TotalRewardPoints(Context context, String email, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, email);

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_TOTAL_REWARD_POINTS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void ApplyRewards(Context context, ApplyRewardsRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getShoppingcartid());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.REWARD_POINTS, request.getRewardpoints());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());


        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_APPLY_REWARDS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void RewardHistory(Context context, String customer_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, customer_id);

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_REWARD_HISTORY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void PayuMoneyUpdateOrderStatus(Context context, PayuRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put("status", request.getStatus());
        postDataParams.put("txnid", String.valueOf(request.getTxnid()));
        postDataParams.put("amount", request.getAmount());
        postDataParams.put("productinfo", request.getProductinfo());
        postDataParams.put("firstname", request.getFirstname());
        postDataParams.put("email", request.getEmail());
        postDataParams.put("udf1", TextUtils.isEmpty(request.getUdf1())?"udf1":request.getUdf1());
//        postDataParams.put("udf2", request.getTxnid().split("_")[0]);
        postDataParams.put("udf2", request.getUdf2());
        postDataParams.put("udf3", TextUtils.isEmpty(request.getUdf3())?"udf3":request.getUdf3());
        postDataParams.put("udf4", TextUtils.isEmpty(request.getUdf4())?"udf4":request.getUdf4());
        postDataParams.put("udf5", TextUtils.isEmpty(request.getUdf5())?"udf5":request.getUdf5());
        postDataParams.put("udf6", "udf6");
        postDataParams.put("udf7", "udf7");
        postDataParams.put("udf8", "udf8");
        postDataParams.put("udf9", "udf9");
        postDataParams.put("udf10", "udf10");
        postDataParams.put("hash", request.getHash());


        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_PAYU_UPDATE_ORDER_STATUS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }
    public static void PayuMoneyCancelOrder(Context context, PayuRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put("status", request.getStatus());
        postDataParams.put("udf2", request.getUdf2());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_PAYU_UPDATE_ORDER_STATUS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);

    }

    public static void MyWishList(Context context, String user_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_my_wishlist, user_id);
        new OKHttpAsyncTask(context, url, AppConstants.APIURL.URL_MY_WISHLIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void AddToWishList(Context context, String user_id, String product_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_add_to_wishlist, user_id, product_id);
        new OKHttpAsyncTask(context, url, AppConstants.APIURL.URL_ADD_TO_WISHLIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void RemoveFromWishList(Context context, String wishlist_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_remove_from_wishlist, wishlist_id);
        new OKHttpAsyncTask(context, url, AppConstants.APIURL.URL_REMOVE_FROM_WISHLIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void Reorder(Context context, ReorderRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put("orderid", request.getOrderid());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_REORDER, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


}
