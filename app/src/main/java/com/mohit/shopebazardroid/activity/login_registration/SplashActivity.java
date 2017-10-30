package com.mohit.shopebazardroid.activity.login_registration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.RegistrationIntentService;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.models.Environment;
import com.mohit.shopebazardroid.models.basemodel.BaseResponse;
import com.mohit.shopebazardroid.network.HTTPWebRequest;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.lang.reflect.Type;

public class SplashActivity extends BaseActivity implements ApiResponse {

    private static final String TAG = SplashActivity.class.getSimpleName();
    Context context;

    private static int SPLASH_TIME_OUT = 3000;

    public static Typeface opensans_bold, opensans_bold_italic, opensans_extrabold,
            opensans_extrabold_italic,
            opensans_italic, opensans_light, opensans_light_italic, opensans_regular,
            opensans_semi_bold, opensans_semi_bold_italic, montserrat_Regular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        getSupportActionBar().hide();
        setFragmentTAG("");

        context = SplashActivity.this;



        montserrat_Regular = Typeface.createFromAsset(this.getAssets(), "Montserrat-Regular_0.otf");
        opensans_bold = Typeface.createFromAsset(this.getAssets(), "OPENSANS-BOLD.TTF");
        opensans_bold_italic = Typeface.createFromAsset(this.getAssets(), "OPENSANS-BOLDITALIC" + ".TTF");
        opensans_extrabold = Typeface.createFromAsset(this.getAssets(), "OPENSANS-EXTRABOLD.TTF");
        opensans_extrabold_italic = Typeface.createFromAsset(this.getAssets(), "OPENSANS-EXTRABOLDITALIC.TTF");
        opensans_italic = Typeface.createFromAsset(this.getAssets(), "OPENSANS-ITALIC.TTF");
        opensans_light = Typeface.createFromAsset(this.getAssets(), "OPENSANS-LIGHT.TTF");
        opensans_light_italic = Typeface.createFromAsset(this.getAssets(), "OPENSANS-LIGHTITALIC" + ".TTF");
        opensans_regular = Typeface.createFromAsset(this.getAssets(), "OPENSANS-REGULAR.TTF");
        opensans_semi_bold = Typeface.createFromAsset(this.getAssets(), "OPENSANS-SEMIBOLD.TTF");
        opensans_semi_bold_italic = Typeface.createFromAsset(this.getAssets(), "OPENSANS-SEMIBOLDITALIC.TTF");

        setIs_login_compulsory(false);

    }

    @Override
    protected void onStart() {
        super.onStart();


        initBasic();
    }

    private void  initBasic(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String firebaseid = FirebaseInstanceId.getInstance().getToken();

                Log.d(TAG, "firebaseID:- "+firebaseid);
                if(!TextUtils.isEmpty(firebaseid)){
                    Intent intent = new Intent(SplashActivity.this, RegistrationIntentService.class);
                    intent.putExtra("FirebaseToken", firebaseid);
                    startService(intent);
                    setFirebaseId(firebaseid);
                    HTTPWebRequest.Basic(SplashActivity.this, getFirebaseId(), AppConstants.APICode.BASIC, SplashActivity.this);

                } else {
                    initBasic();
                }
            }
        }, 1000);

    }

    private void init() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

//                if (getIntent().getExtras() != null) {
//
//                    if (getIntent().getStringExtra("isFrom").equalsIgnoreCase("notif")) {
//                        Intent intent = new Intent(context, OrderHistoryDetailActivity.class);
//                        intent.putExtra("notification", orderid);
//                        intent.putExtra("isFrom", "notif");
//                        startActivity(intent);
//                        finish();
//                    }
//
//                }else {
                Intent intent;
                String userid = getUserid();
                if(getIs_login_compulsory()
                        && TextUtils.isEmpty(userid)
                        && userid.equalsIgnoreCase("0")){
                    intent = new Intent(context, LoginActivity.class);
                } else{
                    intent = new Intent(context, NavigationDrawerActivity.class); // for guest login
                }
                startActivity(intent);
                finish();
//                }


            }


        }, SPLASH_TIME_OUT);
    }

    @Override
    public void apiResponsePostProcessing(String response, int apiCode) {if (response == null) {
            Utility.toastMessage(this, R.string.host_not_reachable);
            this.finish();
            return;
        }

        Gson gson = new GsonBuilder().serializeNulls().create();
        switch (apiCode) {
            case AppConstants.APICode.BASIC:

                Type type = new TypeToken<BaseResponse<Environment>>() {
                }.getType();
                BaseResponse<Environment> baseResponse = new Gson().fromJson(response, type);
                Environment environment = baseResponse.getInfo();
                setIs_login_compulsory(environment.isLoginCompalsory());
                init();
        }
    }

    @Override
    public void networkError(int apiCode) {

    }

    @Override
    public void responseError(int apiCode) {

    }
}
