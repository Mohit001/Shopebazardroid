package com.mohit.shopebazardroid.utility;

public class AppConstants {


    public static final String WEB_URL = "web_url";

    public class DialogMessage {
        public static final String LOGIN = "Logging in...";
        public static final String PLEASE_WAIT = "Please wait...";
        public static final String SYNC_DATA = "Syncing data...";
        public static final String UPDATING_CART = "Updating cart...";
        public static final String ADDING_PRODUCT_TO_CART = "Adding item to cart...";
        public static final String SENDING_COUPON_CODE = "Sending coupon code...";
        public static final String CREATING_ORDER = "Creating order...";
        public static final String ADD_ADDRESS_TO_CART = "Add addresses to cart...";
        public static final String GETTING_PAYMENT_LIST = "Receiving payment list...";
        public static final String GETTING_SHIPPING_METHOD = "Receiving shipping list...";
        public static final String SET_SHIPPING_METHOD = "Setting shipping method...";
        public static final String SET_PRODUCT_TO_CART = "Product adding to cart...";
        public static final String SET_PRODUCT_TO_WISHLIST = "Product adding to wishlist...";
        public static final String REMOVE_PRODUCT_FROM_WISHLIST = "Product removing from " +
                "wishlist...";
        public static final String SET_PRODUCT_TO_FAVOURITE = "Product adding to favourite...";
        public static final String REMOVE_PRODUCT_FROM_FAVOURITE = "Product removing from " +
                "favourite...";
        public static final String DELETING = "Deleting...";
        public static final String SEARCHING = "Searching...";
        public static final String PLACING_ORDER = "Placing Order...";

        public static final String OK = "Ok";
        public static final String YES = "Yes";
        public static final String NO = "No";
        public static final String TRY = "Try";

        public static final String TITLE_CONNECTIVITY = "Connectivity";
        public static final String CONNECTIVITY = "Please check the internet connectivity.";
        public static final String REQUEST_TIMEOUT = "Your request is timed out, please try again.";
    }

    public class BundleRequestDataKey {
        public static final String SUBCATLIST = "subcatlist";
    }

    public class RequestDataKey {
        // ----------- login request ----------
        public static final String ACTION = "action";
        public static final String FIRSTNAME = "firstname";
        public static final String MIDDLENAME = "middlename";
        public static final String LASTNAME = "lastname";
        public static final String EMAIL = "email";
        public static final String PASSWORD = "password";
        public static final String DEVICE_TYPE = "device_type";
        public static final String DEVICE_TOKEN = "device_token";
        public static final String NAME = "name";
        public static final String TITLE = "title";
        public static final String COMMENT = "comment";
        public static final String COUNTRY_CODE = "countrycode";
        public static final String CATID = "catid";
        public static final String PAGE_SIZE = "pagesize";
        public static final String PAGE = "page";
        public static final String USER_ID = "user_id";
        public static final String ADDRESS_ID = "addressId";
        public static final String STREET = "street";
        public static final String STREET2 = "street2";
        public static final String CITY = "city";
        public static final String COUNTRY_ID = "country_id";
        public static final String REGION = "region";
        public static final String POST_CODE = "postcode";
        public static final String TELEPHONE = "telephone";
        public static final String IS_DEFAULT_BILLING = "is_default_billing";
        public static final String IS_DEFAULT_SHIPPING = "is_default_shipping";
        public static final String REGION_ID = "region_id";
        public static final String ENTITY_ID = "entity_id";
        public static final String CUSTID = "custid";
        public static final String CUSTOMER_ID = "customer_id";
        public static final String SEARCH = "search";
        public static final String JSON_FILTER = "json_filter";
        //------------- add to cart request ---------
        public static final String ADDTOCART_PRODUCT_ID = "product_id";
        public static final String ADDTOCART_USER_ID = "user_id";
        public static final String ADDTOCART_SHOPPINGCART_ID = "shoppingcartid";
        public static final String ADDTOCART_QTY = "qty";
        public static final String ADDTOCART_OPTION_TYPE = "type";
        //------------ get cart items request---------
        public static final String SHOPPING_CART_ID = "shoppingcartid";
        public static final String MODE = "mode";
        public static final String DEFAULT_ADDRESS_ID = "address_id";
        public static final String CODE = "code";
        //----------- update and remove cart request --------------
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT = "product";
        public static final String JSON_REQUEST = "json_request";

        public static final String STATUS = "status";
        public static final String COUPON_CODE = "coupon_code";
        public static final String ORDER_ID = "order_id";
        public static final String INCREMENTID = "incrementid";
        public static final String CURRENCY_SYMBOL = "currency_symbol";

        public static final String IS_NOTIFICATION = "is_notification";
        public static final String COUPONCODE_ORDER_REVIEW = "couponcode";

        //--------------Stripee-----------------------
        public static final String STRIPE_OWNER = "cc_owner";
        public static final String STRIPE_NUMBER = "cc_number";
        public static final String STRIPE_EXP_MONTH = "cc_exp_month";
        public static final String STRIPE_EXP_YEAR = "cc_exp_year";
        public static final String STRIPE_CID = "cc_cid";
        public static final String STRIPE_TYPE = "cc_type";

        //-------------Mobile_return-------------------
        public static final String CUSTOM_SHIPMENT_ID = "custom_shipment_id";
        public static final String SHIPMENT_STATUS = "shipment_status";

        //------------Store_Listing--------------------
        public static final String STORE_ID = "store_id";
        public static final String ROOT_CATEGORY_ID = "root_category_id";

        //-------------RelatedProduct-------------------
        public static final String PRODUCTID = "productid";

        //-------------Add Review-------------------
        public static final String NICKNAME = "nickname";
        public static final String DETAIL = "detail";
        public static final String OPTION_ID = "option_id";

        //-------------AddComment--------------------
        public static final String INCREMENT_ID = "increment_id";
        public static final String ORDER_STATUS = "order_status";
        public static final String IS_HIDE_PRICE = "0";

        public static final String FINAL_PRICE = "final_price";

        public static final String REGIONID = "regionId";

        //--------------AreaList----------------------
        public static final String CITYNAME = "cityname";
        public static final String TINTERVALID = "tinterval_id";
        public static final String DATE = "date";
        public static final String AREA = "area";

        //-------------Reward Points ------------
        public static final String REWARD_POINTS = "rewardpoints";

        public static final String image_url = "http://202.131.107.107/above70/media/catalog/product";


    }

    public class DialogTitle {
    }

    public class DialogIdentifier {
        public static final int CONNECTIVITY = 1;

    }

    public class APICode {
        public static final int LOGIN = 1;
        public static final int REGISTRATION = 2;
        public static final int CATEGORYLIST = 3;
        public static final int PRODUCTSLIST = 4;
        public static final int FORGOT_PASSWORD = 5;
        public static final int FEEDBACK = 6;
        public static final int COUNTRY = 7;
        public static final int STATE = 8;
        public static final int ADDRESS_LIST = 9;
        public static final int ADDRESS_DELETE = 10;
        public static final int ADDRESS_ADD_EDIT = 11;
        public static final int GET_PROFILE = 12;
        public static final int UPDATE_PROFILE = 13;
        public static final int SEARCH = 14;
        public static final int ADDTOCART = 15;
        public static final int GETCARTITEMS = 16;
        public static final int ADDRESS_UPDATE_DEFAULT = 17;
        public static final int SHIPPING_METHOD_LIST = 18;
        public static final int SHIPPING_METHOD_SELECTION = 19;
        public static final int PAYMENT_METHOD_LIST = 20;
        public static final int PAYMENT_METHOD_SELECTION = 21;
        public static final int GET_CART = 22;
        public static final int GET_PLACEORDER = 23;
        public static final int UPDATE_CART = 24;
        public static final int REMOVE_CART = 25;
        public static final int HISTORY = 26;
        public static final int ORDER_DETAIL = 27;
        public static final int BANNERHOME = 28;
        public static final int BANNER_PRODUCTS = 29;
        public static final int OFFER_OFTHE_DAY = 30;
        public static final int TRENDING_NOW = 31;
        public static final int NOTIFICATION = 32;
        public static final int BASIC = 33;
        public static final int COUPON_CODE = 34;
        public static final int USER_DETAILS = 35;
        public static final int SET_CURRENCY = 36;
        public static final int STRIPE_PAYMENT = 37;
        public static final int MOBILE_RETURN = 38;
        public static final int GET_SETTINGS = 39;
        public static final int BEST_SELLER = 40;
        public static final int RELATED_PRODUCT = 41;
        public static final int PRODUCT_DETAIL = 42;
        public static final int RATINGS = 43;
        public static final int ADD_REVIEW = 44;
        public static final int REVIEW_LIST = 45;
        public static final int NOTIFICATION_LIST = 46;
        public static final int ADD_COMMENT = 47;
        public static final int CITY_LIST = 48;
        public static final int AREA_LIST = 49;
        public static final int GET_INTERVALS = 50;
        public static final int TOTAL_REWARD_POINTS = 51;
        public static final int APPLY_REWARDS = 52;
        public static final int REWARD_HISTORY = 53;
        public static final int PAYU_MONEY_PLACE_ORDER = 54;
        public static final int ALSO_PURCHASED = 55;
        public static final int RECENTLY_VIEWED= 56;
        public static final int MY_WISHLIST = 57;
        public static final int ADD_TO_WISHLIST = 58;
        public static final int REMOVE_FROM_WISHLIST = 59;
        public static final int FEATURE_PRODUCT = 60;
        public static final int REORDER= 61;

    }


    public class INTENTDATA {
        public static final String TYPE = "type";
        public static final String Show_cart = "showcart";
        public static final String PAYMENT_METHOD = "payment_method";

    }

    public class SharedPreferenceKeys {


        public static final String EMAIL_Address = "email_id";
        public static final String PASSWORD = "password";


        public static final String EMAIL = "email";
        public static final String NAME = "name";
        public static final String SESSIONID = "SessionId";
        public static final String CART_ID = "shoppingcartid";
        public static final String CART_TOKEN= "cart_token";
        public static final String IS_LOGGED_IN = "is_logged_in";

        public static final String IS_REMEMBER_ME = "is_remember_me";
        public static final String USER_ID = "user_id";
        public static final String GCM_TOKEN = "gcm_token";
        public static final String IS_NOTIFICATION = "is_notification";
        public static final String CART_TOTAL_ITEMS = "cart_total_items";
        public static final String CART_TOTAL_QTY = "cart_total_qty";
        public static final String BASECURRENCY_CODE = "currencycode";
        public static final String BASECURRENCY_SYMBOL = "basecurrencysymbol";
        public static final String BASECURRENCY_VALUE = "basecurrencyvalue";
        public static final String ADD_TO_CART = "addtocart";
        public static final String THEME_CODE = "theme_code";

        public static final String STORE_ID = "store_id";
        public static final String STORE_NAME = "store_name";
        public static final String ROOT_CAT_ID = "root_cat_id";
        public static final String IS_DEFAULT = "is_default";
        public static final String IMAGE_PREFIX = "image_prefix";


        public static final String DISPLAY_CURRENCY = "displaycurrency";
        public static final String DISPLAY_CURRENCY_CODE = "displaycurrencycode";
        public static final String DISPLAY_CURRENCY_RATE = "rate";
        public static final String SHOPPING_CART_ID = "shoppingcartid";

        public static final String STORE_LIST = "store_list";
        public static final String IS_HIDE_PRICE = "0";
        public static final String CONFIGURABLE_SWATCHES = "configurable_swatches";
        public static final String PRODUCT_LIST_ATTRIBUTE = "product_list_attribute";
        public static final String ATTRIBUTES = "attributes";
        public static final String SHIPPING_INTERVAL_ID = "shipping_interval_id";
        public static final String SHIPPING_DATE = "shipping_date";
        public static final String SHIPPING_COMMENT = "shipping_comment";
        public static final String PRODUCT_MEDIA_URL = "Product_media_URL";

        public static final String MERCHANT_KEY = "merchant_key";
        public static final String MERCHANT_SALT = "merchant_salt";
        public static final String MERCHANT_IS_LIVE_MODE= "merchant_is_live_mode";



    }


    public class APIURL {

        //                public static final String BASE = "http://192.168.0.145/ecommerceapp/MobileAPI/";
//        public static final String BASE = "http://202.131.107.107/60cart/MobileAPI/";
        public static final String BASE = "http://www.60cart.com/MobileAPI/";
        public static final String URL_LOGIN = BASE + "login.php";
        public static final String URL_USER_DETAIL = BASE + "cutomer_details.php";
        public static final String URL_REGISTRATION = BASE + "signup.php";
        public static final String URL_CATEGORYLIST = BASE + "category.php";
        public static final String URL_PRODUCTLIST = BASE + "categoryproducts.php";
        public static final String URL_FORGOT_PASSWORD = BASE + "forgot.php";
        public static final String URL_FEEDBACK = BASE + "feedback.php";
        public static final String URL_COUNTRY = BASE + "country.php";
        public static final String URL_ADDRESS = BASE + "customer_address.php";
        public static final String URL_ADDRESS_UPDATE_DEFAULT = BASE + "cart_customer_addresses.php";
        public static final String URL_GET_PROFILE = BASE + "cutomer_details.php";
        public static final String URL_UPDATE_PROFILE = BASE + "signup.php";
        public static final String URL_SEARCH = BASE + "search.php";
        public static final String URL_ADD_TO_CART = BASE + "addtocart.php";
        public static final String URL_GET_CART_ITEMS = BASE + "cart.php";
        public static final String URL_SHIPPING_METHOD = BASE + "shipping.php";
        public static final String URL_PAYMENT_METHOD = BASE + "payment.php";
        public static final String URL_PLACEORDER = BASE + "create_order.php";
        public static final String URL_HISTORY = BASE + "order_list.php";
        public static final String URL_HISTORY_ORDER_DETAIL = BASE + "order_detail.php";
        public static final String URL_CART = BASE + "cart.php";
        public static final String URL_MANAGE_CART = BASE + "update_remove_cart.php";
        public static final String URL_CART_UPDATE_ALL = BASE + "update_all.php";
        public static final String URL_BANNERHOME = BASE + "banner.php";
        //        public static final String URL_BANNER_PRODUCTS = BASE + "categoriesproducts.php";
        public static final String URL_BANNER_PRODUCTS = BASE + "categoryproducts.php";
        public static final String URL_OFFER_OFTHE_DAY = BASE + "offer.php";
        public static final String URL_TRENDINGNOW = BASE + "trending.php";
        public static final String URL_NOTIFICATION = BASE + "notification_update.php";
        public static final String URL_BASIC = BASE + "basic.php";
        public static final String URL_COUPON = BASE + "cart_coupon.php";
        public static final String URL_SET_CURRENCY = BASE + "set_currency.php";
        public static final String URL_STRIPE = BASE + "payment.php";
        public static final String URL_MOBILE_RETURN = BASE + "mobile_return.php";
        public static final String URL_GET_SETTINGS = BASE + "get_setting.php";
        public static final String URL_BEST_SELLING = BASE + "bestseller.php";
        public static final String URL_RELATED_PRODUCT = BASE + "relatedpro.php";
        public static final String URL_ALSO_PURCHASED = BASE + "upsell.php";
        public static final String URL_RECENTLY_VIEWED = BASE + "recently_viewed.php";
        public static final String URL_PRODUCT_DETAIL = BASE + "product_detail.php";

        public static final String URL_RATINGS = BASE + "ratings.php";
        public static final String URL_ADD_REVIEW = BASE + "addreview.php";
        public static final String URL_REVIEW_LIST = BASE + "reviewlist.php";
        public static final String URL_NOTIFICATION_LIST = BASE + "notification_list.php";
        public static final String URL_ADD_COMMENT = BASE + "addcomment.php";
        public static final String URL_CITY_LIST = BASE + "get_city.php";
        public static final String URL_AREA_LIST = BASE + "get_area.php";
        public static final String URL_GET_INTERVALS = BASE + "time_interval.php";
        public static final String URL_TOTAL_REWARD_POINTS = BASE + "rewardpoints.php";
        public static final String URL_APPLY_REWARDS = BASE + "applyreward.php";
        public static final String URL_REWARD_HISTORY = BASE + "rewardhistry.php";
        public static final String URL_PAYU_UPDATE_ORDER_STATUS = BASE + "payumoney_response.php";
        public static final String URL_MY_WISHLIST = BASE + "wishlist.php";
        public static final String URL_ADD_TO_WISHLIST = BASE + "add_wishlist.php";
        public static final String URL_REMOVE_FROM_WISHLIST = BASE + "remove_wishlist.php";
        public static final String URL_FEATURE_PRODUCTS = BASE + "featured.php";
        public static final String URL_REORDER = BASE + "reorder.php";
    }
}
