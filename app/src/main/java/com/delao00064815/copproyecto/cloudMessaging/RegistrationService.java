package com.delao00064815.copproyecto.cloudMessaging;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.delao00064815.copproyecto.R;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by Luis on 24/06/2017.
 */

public class RegistrationService extends IntentService {
    public RegistrationService() {
        super("RegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID myID = InstanceID.getInstance(this);
        String registrationToken = null;
        try {
            registrationToken = myID.getToken(
                    getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE,
                    null
            );
            GcmPubSub subscription = GcmPubSub.getInstance(this);
            subscription.subscribe(registrationToken, "/topics/talleres", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("Registration Token", registrationToken);

    }
}
