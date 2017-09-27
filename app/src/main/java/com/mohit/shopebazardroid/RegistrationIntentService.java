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

package com.mohit.shopebazardroid;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.mohit.shopebazardroid.gcm.QuickstartPreferences;
import com.mohit.shopebazardroid.utility.AppConstants;


public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegIntentService";
    private static final String[] TOPICS = {"global"};

    public RegistrationIntentService() {
        super(TAG);
    }

    //public static String gcm_token;
    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            /*InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_SenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i(TAG, "GCM Registration Token: " + token);*/

            String token = intent.getStringExtra("FirebaseToken");

            if (token != null) {
                    /* Intent i =new Intent(this,GcmService.class);
                     i.putExtra("token",""+token);
                     startService(i);*/
                MyApplication.preferencePutString(AppConstants.SharedPreferenceKeys.GCM_TOKEN, token);
            }
//            }
        } catch (Exception e) {
            Log.d(TAG, "Failed to complete token refresh", e);

        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }


}
