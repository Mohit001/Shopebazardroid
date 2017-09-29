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
import com.mohit.shopebazardroid.model.request.AddressRequest;
import com.mohit.shopebazardroid.model.request.ApplyRewardsRequest;
import com.mohit.shopebazardroid.model.request.AreaListRequest;
import com.mohit.shopebazardroid.model.request.BestSellingRequest;
import com.mohit.shopebazardroid.model.request.CartItemRequest;
import com.mohit.shopebazardroid.model.request.CityListRequest;
import com.mohit.shopebazardroid.model.request.CreateOrderRequest;
import com.mohit.shopebazardroid.model.request.CustomerReviewListRequest;
import com.mohit.shopebazardroid.model.request.FeatureProductRequest;
import com.mohit.shopebazardroid.model.request.FeedbackRequest;
import com.mohit.shopebazardroid.model.request.LoginRequest;
import com.mohit.shopebazardroid.model.request.Mobile_return;
import com.mohit.shopebazardroid.model.request.NotificationListRequest;
import com.mohit.shopebazardroid.model.request.NotificationRequest;
import com.mohit.shopebazardroid.model.request.OfferOfTheDayRequest;
import com.mohit.shopebazardroid.model.request.OrderHistoryRequest;
import com.mohit.shopebazardroid.model.request.PaymentRequest;
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
import com.mohit.shopebazardroid.model.response.CartItems;
import com.mohit.shopebazardroid.model.response.SearchRequest;
import com.mohit.shopebazardroid.utility.AppConstants;

import java.util.HashMap;


public class HTTPWebRequest {

    public static HashMap<String, String> postDataParams;

    public static void Login(Context context, LoginRequest req, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, req.getEmail());
        postDataParams.put(AppConstants.RequestDataKey.PASSWORD, req.getPassword());
        postDataParams.put(AppConstants.RequestDataKey.DEVICE_TYPE, req.getDevice_type());
        postDataParams.put(AppConstants.RequestDataKey.DEVICE_TOKEN, req.getDevice_id());
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, req.getShoppingCartID());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_LOGIN, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void Login(Context context, String request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_login);
        new OKHttpAsyncTask(context, url,request, context.getString(R.string.dialog_msg_authenticating),AppConstants.APICode.LOGIN, false, fragmentManager).execute(apiResponse);
    }


    public static void UserDetail(Context context, UserDetailsRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, request.getEmail());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_USER_DETAIL, apiCode, false).execute(apiResponse);
    }

    public static void ForgotPassword(Context context, String emailString, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_forgot_password, emailString);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void Registration(Context context, RegistrationRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        postDataParams.put(AppConstants.RequestDataKey.FIRSTNAME, request.getFirstname());
        postDataParams.put(AppConstants.RequestDataKey.MIDDLENAME, request.getMiddlename());
        postDataParams.put(AppConstants.RequestDataKey.LASTNAME, request.getLastname());
        postDataParams.put(AppConstants.RequestDataKey.EMAIL, request.getEmail());
        postDataParams.put(AppConstants.RequestDataKey.PASSWORD, request.getPassword());
        postDataParams.put(AppConstants.RequestDataKey.DEVICE_TYPE, request.getDevice_type());
        postDataParams.put(AppConstants.RequestDataKey.DEVICE_TOKEN, request.getDevice_token());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_REGISTRATION, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
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


    public static void GetCountryList(Context context, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        new BackgroundAsyncTask(context, AppConstants.APIURL.URL_COUNTRY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void CategoryList(Context context, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {
        String url = UrlFormetter.getURL(context, R.string.api_category);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);
    }

    public static void products(Context context, String categoryid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        String url = UrlFormetter.getURL(context, R.string.api_category_product_list, categoryid);
        new OKHttpAsyncTask(context, url, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, true, fragmentManager).execute(apiResponse);

    }

    public static void GetState(Context context, String countrycode, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.COUNTRY_CODE, countrycode);
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_COUNTRY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void AddressList(Context context, AddressRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.USER_ID, request.getUser_id());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, String.valueOf(1));
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADDRESS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void AddressDelete(Context context, AddressRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.USER_ID, request.getUser_id());
        postDataParams.put(AppConstants.RequestDataKey.ADDRESS_ID, request.getAddressId());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, String.valueOf(4));
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADDRESS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void AddressUpdateDefault(Context context, AddressRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.DEFAULT_ADDRESS_ID, String.valueOf(request.getAddressId()));
        postDataParams.put(AppConstants.RequestDataKey.MODE, String.valueOf(request.getMode()));
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getShoppingCartID());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADDRESS_UPDATE_DEFAULT, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void AddressAdd(Context context, AddressRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.USER_ID, request.getUser_id());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        postDataParams.put(AppConstants.RequestDataKey.FIRSTNAME, request.getFirstname());
        postDataParams.put(AppConstants.RequestDataKey.LASTNAME, request.getLastname());
        postDataParams.put(AppConstants.RequestDataKey.STREET, request.getStreet());
        postDataParams.put(AppConstants.RequestDataKey.STREET2, request.getStreet2());
//        postDataParams.put(AppConstants.RequestDataKey.AREA, request.getArea());
        postDataParams.put(AppConstants.RequestDataKey.CITY, request.getCity());
        postDataParams.put(AppConstants.RequestDataKey.COUNTRY_ID, request.getCountry_id());
        if(!TextUtils.isEmpty(request.getRegion()))
        {
            postDataParams.put(AppConstants.RequestDataKey.REGION, request.getRegion());
            postDataParams.put(AppConstants.RequestDataKey.REGION_ID, request.getRegion_id());
        }
        else
        {
            postDataParams.put(AppConstants.RequestDataKey.REGION, "");
            postDataParams.put(AppConstants.RequestDataKey.REGION_ID, "0");
        }
        postDataParams.put(AppConstants.RequestDataKey.POST_CODE, request.getPostcode());
        postDataParams.put(AppConstants.RequestDataKey.TELEPHONE, request.getTelephone());
        postDataParams.put(AppConstants.RequestDataKey.IS_DEFAULT_BILLING, String.valueOf(request.getDefaultBillingAddress()));
        postDataParams.put(AppConstants.RequestDataKey.IS_DEFAULT_SHIPPING, String.valueOf(request.getDefaultShippingAddress()));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADDRESS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void AddressEdit(Context context, AddressRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.USER_ID, request.getUser_id());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        postDataParams.put(AppConstants.RequestDataKey.FIRSTNAME, request.getFirstname());
        postDataParams.put(AppConstants.RequestDataKey.LASTNAME, request.getLastname());
        postDataParams.put(AppConstants.RequestDataKey.STREET, request.getStreet());
        postDataParams.put(AppConstants.RequestDataKey.STREET2, request.getStreet2());
//        postDataParams.put(AppConstants.RequestDataKey.AREA, request.getArea());
        postDataParams.put(AppConstants.RequestDataKey.CITY, request.getCity());
        postDataParams.put(AppConstants.RequestDataKey.COUNTRY_ID, request.getCountry_id());
        if(!TextUtils.isEmpty(request.getRegion()))
            postDataParams.put(AppConstants.RequestDataKey.REGION, request.getRegion());
        else
            postDataParams.put(AppConstants.RequestDataKey.REGION, "");
        postDataParams.put(AppConstants.RequestDataKey.POST_CODE, request.getPostcode());
        postDataParams.put(AppConstants.RequestDataKey.TELEPHONE, request.getTelephone());
        postDataParams.put(AppConstants.RequestDataKey.REGION_ID, request.getRegion_id());
        postDataParams.put(AppConstants.RequestDataKey.ADDRESS_ID, String.valueOf(request.getAddressId()));
        postDataParams.put(AppConstants.RequestDataKey.IS_DEFAULT_BILLING, String.valueOf(request.getDefaultBillingAddress()));
        postDataParams.put(AppConstants.RequestDataKey.IS_DEFAULT_SHIPPING, String.valueOf(request.getDefaultShippingAddress()));

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADDRESS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
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

    public static void AddToCart(Context context, CartItemRequest cartItemRequest, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, cartItemRequest.getStore_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, cartItemRequest.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_USER_ID, MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.USER_ID, "0"));
        postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_SHOPPINGCART_ID, MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0"));
        postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_PRODUCT_ID, cartItemRequest.getProductId());
        postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_QTY, String.valueOf(cartItemRequest.getProductQty()));
        postDataParams.put(AppConstants.RequestDataKey.CODE, cartItemRequest.getCode());
        postDataParams.put(AppConstants.RequestDataKey.FINAL_PRICE, cartItemRequest.getFinal_price());

        if (!cartItemRequest.isConfigurable()) {
            postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_OPTION_TYPE, "simple");
            for (int i = 0; i < cartItemRequest.getCustomOptions().size(); i++) {
                postDataParams.put("option_id[" + i + "]", String.valueOf(cartItemRequest.getCustomOptions().get(i).getCustom_title_id()));
                postDataParams.put("value_id[" + i + "]", String.valueOf(cartItemRequest.getCustomOptions().get(i).getCustom_title_value_id()));
            }
        } else {
            postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_OPTION_TYPE, "configurable");
            for (int i = 0; i < cartItemRequest.getCustomOptions().size(); i++) {
                postDataParams.put("super_attribute_id[" + i + "]", String.valueOf(cartItemRequest.getCustomOptions().get(i).getCustom_title_id()));
                postDataParams.put("super_value_id[" + i + "]", String.valueOf(cartItemRequest.getCustomOptions().get(i).getCustom_title_value_id()));
            }
        }
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADD_TO_CART, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void GetCartItems(Context context, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0"));
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_GET_CART_ITEMS, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void SetShippingMethodToCart(Context context, ShippingRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getCartid());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        postDataParams.put(AppConstants.RequestDataKey.CODE, request.getCode());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_SHIPPING_METHOD, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void GetPaymentMethodList(Context context, PaymentRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getCartid());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_PAYMENT_METHOD, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void SetPaymentMethodToCart(Context context, PaymentRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getCartid());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, request.getAction());
        postDataParams.put(AppConstants.RequestDataKey.CODE, request.getCode());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_PAYMENT_METHOD, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


    public static void GetCartDetails(Context context, String cartid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, cartid);
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_CART, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void RemoveProductFromCart(Context context, CartItems cartItems, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.CART_ID, "0"));
        postDataParams.put(AppConstants.RequestDataKey.PRODUCT_ID, cartItems.getProduct_id());
        postDataParams.put(AppConstants.RequestDataKey.ACTION, "" + 2);
        if (cartItems.getProduct_type().equalsIgnoreCase("simple")) {
            postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_OPTION_TYPE, "simple");

            for (int i = 0; i < cartItems.getCustom_option_id().size(); i++) {
                postDataParams.put("option_id[" + i + "]", String.valueOf(cartItems.getCustom_option_id().get(i).getOption_id()));
                postDataParams.put("value_id[" + i + "]", String.valueOf(cartItems.getCustom_option_id().get(i).getValue_id()));
            }
        } else {
            postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_OPTION_TYPE, "configurable");
            for (int i = 0; i < cartItems.getSupper_attribute_id().size(); i++) {
                postDataParams.put("super_attribute_id[" + i + "]", String.valueOf(cartItems.getSupper_attribute_id().get(i).getOption_id()));
                postDataParams.put("super_value_id[" + i + "]", String.valueOf(cartItems.getSupper_attribute_id().get(i).getValue_id()));
            }
        }
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_MANAGE_CART, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
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

    public static void PlaceOrder(Context context, CreateOrderRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.SHOPPING_CART_ID, request.getShoppingcartid());
        postDataParams.put(AppConstants.RequestDataKey.IS_HIDE_PRICE, request.getIshideprice());
        postDataParams.put(AppConstants.RequestDataKey.USER_ID, request.getUser_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_PLACEORDER, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void OrderHistory(Context context, OrderHistoryRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_HISTORY, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void OrderDetails(Context context, String orderid, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<>();
        postDataParams.put(AppConstants.RequestDataKey.ORDER_ID, orderid);
        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_HISTORY_ORDER_DETAIL, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
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



    public static void ProductDetail(Context context, ProductDetailRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.PRODUCT_ID, request.getProduct_id());
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_Id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_PRODUCT_DETAIL, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
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

    public static void MyWishList(Context context, String customer_id, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, customer_id);


        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_MY_WISHLIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void AddToWishList(Context context, AddRemoveWishListRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_PRODUCT_ID, request.getProduct_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_ADD_TO_WISHLIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void RemoveFromWishList(Context context, AddRemoveWishListRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.ADDTOCART_PRODUCT_ID, request.getProduct_id());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_REMOVE_FROM_WISHLIST, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }

    public static void Reorder(Context context, ReorderRequest request, int apiCode, ApiResponse apiResponse, FragmentManager fragmentManager) {

        postDataParams = new HashMap<String, String>();
        postDataParams.put(AppConstants.RequestDataKey.CUSTOMER_ID, request.getCustomer_id());
        postDataParams.put(AppConstants.RequestDataKey.STORE_ID, request.getStore_id());
        postDataParams.put("orderid", request.getOrderid());

        new BackgroundAsyncTask(context, postDataParams, AppConstants.APIURL.URL_REORDER, AppConstants.DialogMessage.PLEASE_WAIT, apiCode, false, fragmentManager).execute(apiResponse);
    }


}
