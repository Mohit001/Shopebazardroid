package com.mohit.shopebazardroid.activity.login_registration;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.BaseActivity;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;

public class SplashActivity extends BaseActivity  {

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

        setIs_login_compulsory(1);
        /*String firebaseid = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "firebaseID:- "+firebaseid);
        if(!TextUtils.isEmpty(firebaseid)){
            Intent intent = new Intent(this, RegistrationIntentService.class);
            intent.putExtra("FirebaseToken", firebaseid);
            startService(intent);
        }*/

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

        init();
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
                if(getIs_login_compulsory() == 1){
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

}
