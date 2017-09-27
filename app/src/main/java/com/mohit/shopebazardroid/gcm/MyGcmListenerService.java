/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mohit.shopebazardroid.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.mohit.shopebazardroid.MyApplication;
import com.mohit.shopebazardroid.R;
import com.mohit.shopebazardroid.activity.Main.NavigationDrawerActivity;
import com.mohit.shopebazardroid.activity.history.OrderHistoryDetailActivity;
import com.mohit.shopebazardroid.utility.AppConstants;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MyGcmListenerService extends GcmListenerService {
    static String notititle;
    static String notidesc;
    static String notiURL;
    static NotificationManager mNotifyManager;
    static NotificationCompat.Builder mBuilder;
    private static final String TAG = "MyGcmListenerService";
    Bitmap largeBitmap = null;
    BufferedInputStream buf = null;
    Intent intent = null;
    PendingIntent pendingIntent = null;
    Context ctx = this;
    Notification.Builder builder = null;
    String msg;
    String type;
    String img;
    String order_id;

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {

        mBuilder = new NotificationCompat.Builder(ctx);

        Log.d(TAG, "messageData: " + data);
        try {
            String message = data.getString("message");
            if(!TextUtils.isEmpty(message)){

                JSONObject jsonObject = new JSONObject(message);
                if(jsonObject != null){
                    if(jsonObject.has("message"))
                        msg = jsonObject.getString("message");
                    else
                        msg = "";

                    if(jsonObject.has("notification_type"))
                        type = jsonObject.getString("notification_type");
                    else
                       type = "0";

                    if(jsonObject.has("image"))
                        img = jsonObject.getString("image");
                    else
                        img= "";

                    if(jsonObject.has("order_id"))
                        order_id = jsonObject.getString("order_id");
                    else
                        order_id = "0";

                    Log.d(TAG, "From: " + from);
                    Log.d(TAG, "Message: " + msg);
                    Log.d(TAG, "notification_type: " + type);
                    Log.d(TAG, "image: " + img);
                    Log.d(TAG, "order_id: " + order_id);


                    String showNotification = MyApplication.preferenceGetString(AppConstants.SharedPreferenceKeys.IS_NOTIFICATION, "1");

                    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        createNotificationWithOutImage(alarmSound, msg, type);

                    if (showNotification.equalsIgnoreCase("0")) {
                        return;
                    } else {


                        if (type.equalsIgnoreCase("1")) {

                            if (TextUtils.isEmpty(img)) {
                                sendNotification(msg, type);
                            } else {
                                // image is present, show notification with image

                                Bitmap bitmap = getBitmapFromURL(img);

                                showBigNotification(bitmap, R.drawable.ic_placeholder, R.drawable.sales_genie_white,
                                        getResources().getString(R.string.app_name), msg, alarmSound);
                            }
                        } else {
                            sendNotification(msg, type);
                        }

//            sendNotification(msg, type);

                    }
                }
            } else{
                msg = "message";
                type = "notification_type";
                img = "image";
                order_id = "13123";

                msg = "test notification";
                type =  "1";
                sendNotification(msg, type);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }




        // GetNotificationResponse getCityAndStateResponse = gson.fromJson(data.getString("message"), GetNotificationResponse.class);
        /*if(status != null) {
            if (!status.equals("pending")) {
                sendNotification(message,status);
            }
        }*/
    }

    private void sendNotification(String message, String status) {

        if (message != null) {
            createNotification(message, status);
        }

    }

    public void createNotification(String message, String status) {
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        createNotificationWithOutImage(alarmSound, message, status);

    }

    private void createNotificationWithOutImage(Uri alarmSound, String message, String status) {

        if (type.equalsIgnoreCase("1")) {

            intent = new Intent(this, NavigationDrawerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

        } else {

            intent = new Intent(this, OrderHistoryDetailActivity.class);
            intent.putExtra("notification", order_id);
            intent.putExtra("isFrom", "notif");
            pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            builder = new Notification.Builder(this);
            builder.setSmallIcon(R.drawable.sales_genie_white);
            builder.setTicker(message);
            builder.setContentTitle(getResources().getString(R.string.app_name));
            builder.setContentText(message);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            builder.setSound(alarmSound);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(01, builder.build());
            }
        }

    }

    private void showBigNotification(Bitmap bitmap, int icon, int smallIcon, String title, String message, Uri alarmSound) {

        try {

            intent = new Intent(this, NavigationDrawerActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            pendingIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(title);
            bigPictureStyle.setSummaryText(Html.fromHtml(message).toString());
            bigPictureStyle.bigPicture(bitmap);
            Notification notification;

            notification = mBuilder
                    .setSmallIcon(smallIcon)
                    .setTicker(title)
                    .setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(title)
                    .setContentIntent(pendingIntent)
                    /*.setSound(alarmSound)*/
                    .setStyle(bigPictureStyle)
                    //.setWhen(getTimeMilliSec(timeStamp))
                    .setLargeIcon(BitmapFactory.decodeResource(ctx.getResources(), icon))
                    //.setContentText(message)
                    .build();

            NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(new Random().nextInt(10), notification);

            //Config.NOTIFICATION_ID_BIG_IMAGE
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

