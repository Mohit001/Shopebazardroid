package com.mohit.shopebazardroid.network;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.dialog.ProgressDialog;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.utility.Utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpAsyncTask extends AsyncTask<Object, Void, String> {

    public static final String TAG = OKHttpAsyncTask.class.getSimpleName();

    public static Context context;
    private String url;
    private ApiResponse apiResponse;
    private int code;
    private boolean isGET;
    ProgressDialog dialog = null;
    private String requestJson="";
    int ddcode = 0;
    FragmentManager fragmentManager;
    OkHttpClient client;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public OKHttpAsyncTask(Context context, String url, String dialogMessage, int code, boolean isGET) {

        this.context = context;
        this.url = url;
        this.code = code;
        this.isGET = isGET;
        this.fragmentManager = null;
    }

    public OKHttpAsyncTask(Context context, String url, String dialogMessage, int code, boolean isGET, FragmentManager fragmentManager) {
        this.context = context;
        this.url = url;
        this.code = code;
        this.isGET = isGET;
        this.fragmentManager = fragmentManager;
        if(!TextUtils.isEmpty(dialogMessage)){
            dialog = new ProgressDialog();
            dialog.setDialogMessage(dialogMessage);
        } else {
            dialog = null;
        }

    }

    public OKHttpAsyncTask(Context context, String url, String requestJson, String dialogMessage, int code, boolean isGET, FragmentManager fragmentManager) {

        this.context = context;
        this.url = url;
        this.code = code;
        this.isGET = isGET;
        this.requestJson = requestJson;
        this.fragmentManager = fragmentManager;
        if(!TextUtils.isEmpty(dialogMessage)){
            dialog = new ProgressDialog();
            dialog.setDialogMessage(dialogMessage);
        } else {
            dialog = null;
        }

    }


    public void cancelThisTask() {
        cancel(true);
    }

    @Override
    protected void onPreExecute() {

        if (MyApplication.isConnectivityAvailable(context)) {

            try {
                client = new OkHttpClient().newBuilder()
                                .connectTimeout(60, TimeUnit.SECONDS)
                                .writeTimeout(60, TimeUnit.SECONDS)
                                .readTimeout(60, TimeUnit.SECONDS)
                                .build();

                if (dialog != null) {
                    dialog.show(fragmentManager, ProgressDialog.TAG);
                    dialog.setCancelable(false);
                    ddcode = 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
                this.cancelThisTask();
            }

        } else {
            this.cancel(true);
            ddcode = 1;
            return;
        }

    }

    @Override
    protected String doInBackground(Object... arg0) {
        // TODO Auto-generated method stub

        Log.d(TAG, "onPreExecute: "+url);

        if (this.isCancelled())
        {

            return null;

        }

        apiResponse = (ApiResponse) arg0[0];
        String responseJson = "";
        if (isGET) {
            Request request = new Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                responseJson = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Log.d(TAG, "doInBackground: "+requestJson);

            RequestBody body = RequestBody.create(JSON, requestJson);

            Request request = new Request.Builder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                responseJson = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                responseJson = "";
            }
        }
        return responseJson;
    }


    @Override
    protected void onPostExecute(String response) {

        if (this.dialog != null && this.dialog.isVisible()) {
            this.dialog.dismiss();
        }

        System.out.println("Response in ASYNC :" + response);
        response = Utility.replaceEmprtyToNullString(response);
        System.out.println("Refactor Response in ASYNC :" + response);

		/*if(ddcode == 0) {
            if (response.equals("")) {
				Utility.showCustomNetworkErrorDialog(context, AppConstants.DialogMessage.TITLE_CONNECTIVITY,
						AppConstants.DialogMessage.REQUEST_TIMEOUT, AppConstants.DialogMessage.OK, AppConstants.DialogMessage.TRY, null,
						AppConstants.DialogIdentifier.CONNECTIVITY, dialogMessage, url, code, isGET);
				//return;
			} else if (isTaskCancelled) {
				Utility.showCustomNetworkErrorDialog(context, AppConstants.DialogMessage.TITLE_CONNECTIVITY,
						AppConstants.DialogMessage.REQUEST_TIMEOUT, AppConstants.DialogMessage.OK, AppConstants.DialogMessage.TRY, null,
						AppConstants.DialogIdentifier.CONNECTIVITY, dialogMessage, url, code, isGET);
				return;
			}
		}*/
        apiResponse.apiResponsePostProcessing(response, code);

        if (this.dialog != null && this.dialog.isVisible()) {
            this.dialog.dismiss();
        }
    }


}
