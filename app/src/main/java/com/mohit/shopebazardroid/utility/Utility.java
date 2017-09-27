package com.mohit.shopebazardroid.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.dialog.ConfirmDialog;
import com.mohit.shopebazardroid.listener.ConfirmDialogListner;
import com.mohit.shopebazardroid.listener.DialogButtonClickListener;
import com.mohit.shopebazardroid.network.BackgroundAsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Utility {
    public static String TAG = Utility.class.getSimpleName();
    Context _context;
    public static ProgressDialog dialog;

    public Utility(Context c) {
        this._context = c;
    }

    public static ConfirmDialog confirmDialog;

    public static String getResponseString(String requestURL) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(35000);
            conn.setConnectTimeout(35000);
            /*conn.setReadTimeout(100);
			conn.setConnectTimeout(100);*/
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String getResponseStringUsingPostMethod(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(55000);
            conn.setConnectTimeout(55000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (SocketTimeoutException e) {
            Log.d(TAG, "in catch SocketTimeoutException");
            e.printStackTrace();
            response = null;
        } catch (NullPointerException e) {
            Log.d(TAG, "in catch NullPointerException");
            e.printStackTrace();
            response = null;
        } catch (UnsupportedEncodingException e) {

            Log.d(TAG, "in catch UnsupportedEncodingException");
            e.printStackTrace();
            response = null;

        } catch (ProtocolException e) {
            Log.d(TAG, "in catch ProtocolException");
            e.printStackTrace();
            response = null;

        } catch (MalformedURLException e) {
            Log.d(TAG, "in catch MalformedURLException");
            e.printStackTrace();
            response = null;

        } catch (IOException e) {
            Log.d(TAG, "in catch IOException");
            e.printStackTrace();
            response = null;

        }
        return response;
    }

    public static void showCustomNetworkErrorDialog(final Context context,
                                                    String dialogTitle, final String dialogMessage, String textPositiveButton,
                                                    String textNegativeButton,
                                                    final DialogButtonClickListener buttonClickListener,
                                                    final int dialogIdentifier, final String dialogMessage2, final String url, final int code, final boolean isPOST) {

        final Activity activity = (Activity) context;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(dialogMessage);

        alertDialogBuilder.setPositiveButton(textPositiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                if (buttonClickListener == null) {

//                    dialog.dismiss();
                    activity.finish();
                } else {
                    buttonClickListener.onPositiveButtonClicked(dialogIdentifier);
                }
            }
        });

        alertDialogBuilder.setNegativeButton(textNegativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (buttonClickListener == null) {

                    dialog.dismiss();
                    new BackgroundAsyncTask(context, url, dialogMessage2, code, isPOST).execute(context);

                } else {
                    buttonClickListener.onNegativeButtonClicked(dialogIdentifier);
                }
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        StringBuilder request = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
                request.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));

            request.append(entry.getKey());
            request.append("=");
            request.append(entry.getValue());
        }
        Log.d("Request", "RequestParams == " + request);
        return result.toString();
    }

    public static void RedirectToActivity(Activity yourActivity, Class SecondActivity,
                                          boolean isfinish, Bundle b) {
        Intent intent = new Intent(yourActivity, SecondActivity);

        if (b != null)
            intent.putExtras(b);

        yourActivity.startActivity(intent);

        yourActivity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        if (isfinish) {
            yourActivity.finish();
        }

    }

    public static ProgressDialog setDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setContentView(R.layout.dialog_loading);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    public static void showdialog() {
        Log.d("dismiss", "show");
        dialog.show();
    }

    public static void hideDialog() {
        Log.d("dismiss", "dismiss" + dialog.isShowing());
        dialog.dismiss();
        dialog.hide();
    }


    public static void toastMessage(Context mContext, int resid) {
        Toast.makeText(mContext, mContext.getString(resid), Toast.LENGTH_SHORT).show();
    }

    public static void toastMessage(Context mContext, String messageString) {
        Toast.makeText(mContext, messageString, Toast.LENGTH_SHORT).show();
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else if (target.length() == 0) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static String ArraytoStringForInOperator(ArrayList<Object> params) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            if (i == 0) {
                builder.append("(");
            }
            builder.append("'" + params.get(i) + "'");
            if (i != params.size() - 1)
                builder.append(",");
            if (i == params.size() - 1) {
                builder.append(")");
            }
        }
        return builder.toString();

    }

    public static String replaceEmprtyToNullString(String response) {
        if (!TextUtils.isEmpty(response)) {
            response = response.replace("\"\"", "null");
            return response;
        } else {
            return null;
        }
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static void showConfirmDialog(Context context, ConfirmDialogListner listner, String title, String message, String posbtn_name, String negbtn_name, FragmentManager fragmentManager) {
        confirmDialog = new ConfirmDialog();
        confirmDialog.setListner(listner, title, message, posbtn_name, negbtn_name);
        confirmDialog.show(fragmentManager, ((Activity) context).getClass().getSimpleName());
    }

    public static void dismissConfirmDialog() {
        if (confirmDialog != null) {
            confirmDialog.dismiss();
        }
    }

    public static void showAlertDialog(Context context, String message) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("Sales Genie");
        builder.setMessage(message);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    public static void showAlertDialogDelete(final Context context, String message) {

        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle(R.string.app_name);

        // set dialog message
        alertDialogBuilder
                .setMessage("Are yo sure do you want to delete ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utility.showAlertDialog(context, "User Deleted Successfully");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public static String convertToAPIFormat(String date) {
        String finaldate = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = fmt.parse(date);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MM/dd/yyyy");
            finaldate = fmtOut.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaldate;

    }

    public static String shippingDate(String date) {
        String finaldate = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = fmt.parse(date);
            SimpleDateFormat fmtOut = new SimpleDateFormat("yyyy-MM-dd");
            finaldate = fmtOut.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaldate;

    }

    public static String convertToCommentFormat(String date) {
        String finaldate = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date1 = fmt.parse(date);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM dd,yyyy hh:mm:ss a");
            finaldate = fmtOut.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaldate;

    }

    public static String convertToNotDateFormat(String date) {
        String finaldate = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date1 = fmt.parse(date);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM dd,yyyy");
            finaldate = fmtOut.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finaldate;

    }


    public static void slide_down(Context ctx, View v){

        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_down);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }


    public static void slide_up(Context ctx, View v){

        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slide_up);
        if(a != null){
            a.reset();
            if(v != null){
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

//    public static TextView bottomTextview(Context mContext, View view)
//    {
////        TextView powerby = (TextView) view.findViewById(R.id.powerby_textview);
//        int themeCode = MyApplication.preferenceGetInteger(AppConstants.SharedPreferenceKeys.THEME_CODE, 1);
//        switch (themeCode)
//        {
//            case 1:
//                powerby.setBackgroundColor(mContext.getResources().getColor(R.color.theme1_color));
//                break;
//            case 2:
//                powerby.setBackgroundColor(mContext.getResources().getColor(R.color.theme2_color));
//                break;
//            case 3:
//                powerby.setBackgroundColor(mContext.getResources().getColor(R.color.theme3_color));
//                break;
//            case 4:
//                powerby.setBackgroundColor(mContext.getResources().getColor(R.color.theme4_color));
//                break;
//            case 5:
//                powerby.setBackgroundColor(mContext.getResources().getColor(R.color.theme5_color));
//                break;
//            case 6:
//                powerby.setBackgroundColor(mContext.getResources().getColor(R.color.theme6_color));
//                break;
//
//        }
//        return powerby;
//    }
}
