package com.mohit.shopebazardroid;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.mohit.shopebazardroid.payu.AppEnvironment;
import com.mohit.shopebazardroid.utility.AppConstants;


/**
 * Created by msp on 6/7/16.
 */
public class MyApplication extends Application {
    private static MyApplication appInstance;
    private static SharedPreferences sharedPreferences, sharedPreferencesRemeber;
    private static SharedPreferences.Editor sharedPreferencesEditor, sharedPreferencesEditorRemember;
    private static Context mContext;

    private static final String KEY_REMEBER = "REMEMBER";
    private static final String KEY = "SalesGenie";
    public AppEnvironment appEnvironment;
    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        sharedPreferences = getAppInstance().getSharedPreferences(KEY, MODE_PRIVATE);
        sharedPreferencesRemeber = getAppInstance().getSharedPreferences(KEY_REMEBER, MODE_PRIVATE);

        sharedPreferencesEditor = sharedPreferences.edit();

        sharedPreferencesEditorRemember = sharedPreferencesRemeber.edit();

        appEnvironment = AppEnvironment.SANDBOX;
    }

    public AppEnvironment getAppEnvironment() {
        return appEnvironment;
    }

    public void setAppEnvironment(AppEnvironment appEnvironment) {
        this.appEnvironment = appEnvironment;
    }

    public static boolean isConnectivityAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static Context getContext() {
        return mContext;
    }

    public static void setContext(Context mctx) {
        mContext = mctx;
    }

    public static MyApplication getAppInstance() {
        if (appInstance == null)
            throw new IllegalStateException("The application is not created yet!");
        return appInstance;
    }

    /**
     * Application level preference work.
     */
    public static void preferencePutInteger(String key, int value) {
        sharedPreferencesEditor.putInt(key, value);
        sharedPreferencesEditor.commit();
    }

    public static int preferenceGetInteger(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    public static void preferencePutBoolean(String key, boolean value) {
        sharedPreferencesEditor.putBoolean(key, value);
        sharedPreferencesEditor.commit();
    }

    public static boolean preferenceGetBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    public static void preferencePutBooleanRemember(String key, boolean value) {
        sharedPreferencesEditorRemember.putBoolean(key, value);
        sharedPreferencesEditorRemember.commit();
    }

    public static boolean preferenceGetBooleanRemember(String key, boolean defaultValue) {
        return sharedPreferencesRemeber.getBoolean(key, defaultValue);
    }


    public static void preferencePutString(String key, String value) {
        sharedPreferencesEditor.putString(key, value);
        sharedPreferencesEditor.commit();
    }

    public static String preferenceGetString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }


    public static void preferencePutStringRemember(String key, String value) {
        sharedPreferencesEditorRemember.putString(key, value);
        sharedPreferencesEditorRemember.commit();
    }

    public static String preferenceGetStringRemember(String key, String defaultValue) {
        return sharedPreferencesRemeber.getString(key, defaultValue);
    }

    public static void preferencePutLong(String key, long value) {
        sharedPreferencesEditor.putLong(key, value);
        sharedPreferencesEditor.commit();
    }

    public static long preferenceGetLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    public static void preferencePutFloat(String key, float value) {
        sharedPreferencesEditor.putFloat(key, value);
        sharedPreferencesEditor.commit();
    }

    public static float preferenceGetFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public static void preferenceRemoveKey(String key) {
        sharedPreferencesEditor.remove(key);
        sharedPreferencesEditor.commit();
    }

    public static void clearPreference() {
        String baseImageURL = sharedPreferences.getString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, "");
        String merchantkey = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.MERCHANT_KEY, "");
        String merchantsalt = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.MERCHANT_SALT, "");
        String isLiveMode = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.MERCHANT_IS_LIVE_MODE, "0");

//        String cartID = sharedPreferences.getString(AppConstants.SharedPreferenceKeys.CART_ID, "");
//        String cartItem = sharedPreferences.getString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "");
//        String cartQTY = sharedPreferences.getString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, "");

        sharedPreferencesEditor.clear();
        sharedPreferencesEditor.commit();
        preferencePutString(AppConstants.SharedPreferenceKeys.CART_ID, "0");
        preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_ITEMS, "0");
        preferencePutString(AppConstants.SharedPreferenceKeys.CART_TOTAL_QTY, "0");
        preferencePutString(AppConstants.SharedPreferenceKeys.IMAGE_PREFIX, baseImageURL);
        preferencePutString(AppConstants.SharedPreferenceKeys.MERCHANT_KEY, merchantkey);
        preferencePutString(AppConstants.SharedPreferenceKeys.MERCHANT_SALT, merchantsalt);
        preferencePutString(AppConstants.SharedPreferenceKeys.MERCHANT_IS_LIVE_MODE, isLiveMode);


    }
}
