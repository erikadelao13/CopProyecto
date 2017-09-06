package com.delao00064815.copproyecto.cloudMessaging;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.delao00064815.copproyecto.R;
import com.delao00064815.copproyecto.SessionManager;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Luis on 24/06/2017.
 */

public class RegistrationService extends IntentService {
    SessionManager session;
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;
    public RegistrationService() {
        super("RegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        HttpURLConnection conn = null;
        URL url = null;
        InstanceID myID = InstanceID.getInstance(this);
        String registrationToken = null;
        session = new SessionManager(this);
        try {
            registrationToken = myID.getToken(
                    getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE,
                    null
            );
            GcmPubSub subscription = GcmPubSub.getInstance(this);
            subscription.subscribe(registrationToken, "/topics/talleres", null);
            if(session.isLoggedIn()){

                try {

                    // Enter URL address where your php file resides
                    url = new URL("http://copuca-com.stackstaging.com/WebServer/subscription.php");
                    // gamespm-com.stackstaging.com
                    // Setup HttpURLConnection class to send and receive data from php and mysql
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setReadTimeout(READ_TIMEOUT);
                    conn.setConnectTimeout(CONNECTION_TIMEOUT);
                    conn.setRequestMethod("POST");

                    // setDoInput and setDoOutput method depict handling of both send and receive
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    // Append parameters to URL
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("carnet", session.getUserDetails().get(SessionManager.KEY_CARNET));
                    String query = builder.build().getEncodedQuery();

                    // Open connection for sending data
                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();
                    int response_code = conn.getResponseCode();

                    // Check if successful connection made
                    if (response_code == HttpURLConnection.HTTP_OK) {

                        // Read data sent from server
                        InputStream input = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        StringBuilder result = new StringBuilder();
                        String line;

                        result.append("/topics/carrera/");
                        while ((line = reader.readLine()) != null) {
                            result.append(line);
                        }

                        //subscription.subscribe(registrationToken, result.toString(), null);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(conn != null) conn.disconnect();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("Registration Token", registrationToken);

    }
}
