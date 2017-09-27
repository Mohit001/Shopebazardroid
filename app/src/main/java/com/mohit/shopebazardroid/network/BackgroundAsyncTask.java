package com.mohit.shopebazardroid.network;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;

import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.dialog.ProgressDialog;
import com.mohit.shopebazardroid.listener.ApiResponse;
import com.mohit.shopebazardroid.utility.AppConstants;
import com.mohit.shopebazardroid.utility.Utility;

import java.util.HashMap;

public class BackgroundAsyncTask extends AsyncTask<Object, Void, String> {

    public static Context context;
    private String url;
    private ApiResponse apiResponse;
    private int code;
    private String dialogMessage;
    private boolean isGET;
    private boolean isTaskCancelled;
    private HashMap<String, String> postDataParams = new HashMap<String, String>();
    ProgressDialog dialog = null;
    int ddcode = 0;
    FragmentManager fragmentManager;


    public BackgroundAsyncTask(Context context, String url, String dialogMessage, int code, boolean isGET) {
        this.context = context;
        this.url = url;
        this.code = code;
        this.isGET = isGET;
    }

    public BackgroundAsyncTask(Context context, HashMap<String, String> postDataParams, String url, int code, boolean isGET) {

        this.context = context;
        this.url = url;
        this.code = code;
        this.isGET = isGET;
        this.postDataParams = postDataParams;

    }

    public BackgroundAsyncTask(Context context, String url, String dialogMessage, int code, boolean isGET, FragmentManager fragmentManager) {
        this.context = context;
        this.url = url;
        this.code = code;
        this.isGET = isGET;
        dialog = new ProgressDialog();
        dialog.setDialogMessage(dialogMessage);
        this.fragmentManager = fragmentManager;
    }

    public BackgroundAsyncTask(Context context, HashMap<String, String> postDataParams, String url, String dialogMessage, int code, boolean isGET, FragmentManager fragmentManager) {

        this.context = context;
        this.url = url;
        this.code = code;
        this.isGET = isGET;
        this.postDataParams = postDataParams;
        dialog = new ProgressDialog();
        dialog.setDialogMessage(dialogMessage);
        this.fragmentManager = fragmentManager;
    }


    public void cancelThisTask() {
        cancel(true);
        isTaskCancelled = true;
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Request URL :" + url);
        if (MyApplication.isConnectivityAvailable(context)) {

            try {


                if (dialog != null) {
                    dialog.show(fragmentManager, ProgressDialog.TAG);
                    dialog.setCancelable(false);
                    ddcode = 0;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            this.cancel(true);
            Utility.showCustomNetworkErrorDialog(context, AppConstants.DialogMessage.TITLE_CONNECTIVITY,
                    AppConstants.DialogMessage.CONNECTIVITY, AppConstants.DialogMessage.OK, AppConstants.DialogMessage.TRY, null,
                    AppConstants.DialogIdentifier.CONNECTIVITY, dialogMessage, url, code, isGET);
            ddcode = 1;
            return;
        }

    }

    @Override
    protected String doInBackground(Object... arg0) {
        // TODO Auto-generated method stub

        if (this.isCancelled())
            return null;

        apiResponse = (ApiResponse) arg0[0];
        String response = null;
        if (isGET) {
            response = Utility.getResponseString(url);//get method
        } else {
            response = Utility.getResponseStringUsingPostMethod(url, postDataParams);//post method
        }
        return response;
    }


    @Override
    protected void onPostExecute(String response) {


        try {
            if (this.dialog != null && this.dialog.isVisible()) {
                this.dialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            this.dialog = null;
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


    }


}
