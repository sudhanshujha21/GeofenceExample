package com.example.sudhanshuj.geofenceexample.backgroundservices;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;

import com.example.sudhanshuj.geofenceexample.R;
import com.example.sudhanshuj.geofenceexample.activities.GeofenceActivity;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;
import java.util.ArrayList;
import java.util.List;


public class GeofenceRegistrationService extends IntentService {

    private static final String TAG = "GeoIntentService";
    private static final String CHANNEL_ID = "channel_01";
    private static final String GEOFENCE_ID = "Geofence";


    public GeofenceRegistrationService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        assert geofencingEvent != null;
        if (geofencingEvent.hasError()) {
            Log.d(TAG, "GeofencingEvent error " + geofencingEvent.getErrorCode());
        } else {
            int transaction = geofencingEvent.getGeofenceTransition();
            List<Geofence> geofences = geofencingEvent.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            if (transaction == Geofence.GEOFENCE_TRANSITION_ENTER && geofence.getRequestId().equals(GEOFENCE_ID)) {
                Log.d(TAG, "You are inside");
            } else {
                Log.d(TAG, "You are outside");
            }
            String geofenceTransitionDetails = getGeofenceTrasitionDetails(transaction, geofences);

            sendNotification(geofenceTransitionDetails);


        }
    }


    // Create a detail message with Geofences received
    private String getGeofenceTrasitionDetails(int geoFenceTransition, List<Geofence> triggeringGeofences) {
        // get the ID of each geofence triggered
        ArrayList<String> triggeringGeofencesList = new ArrayList<>();
        for (Geofence geofence : triggeringGeofences) {
            triggeringGeofencesList.add(geofence.getRequestId());
        }
        String status = null;
        if (geoFenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER)
            status = "Entering ";
        else if (geoFenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT)
            status = "Exiting ";
        return status + TextUtils.join(", ", triggeringGeofencesList);
    }

    // Send a notification
    private void sendNotification(String msg) {
        Log.i(TAG, "sendNotification: " + msg);

        // Intent to start the main Activity
        //Create the intent that’ll fire when the user taps the notification//
        Intent notificationIntent = new Intent();
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(GeofenceActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        // PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        PendingIntent notificationPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Creating and sending Notification
        NotificationManager notificationMng = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.app_name);
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            mChannel.enableVibration(true);
            mChannel.setShowBadge(true);
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            //Create the channel for the notification
            if (notificationMng != null) {
                notificationMng.createNotificationChannel(mChannel);
                // notificationId is a unique int for each notification that you must define
                notificationMng.notify(0, createNotification(msg, notificationPendingIntent));
            }

        } else {
            if (notificationMng != null) {
                // notificationId is a unique int for each notification that you must define
                notificationMng.notify(0, createNotification(msg, notificationPendingIntent));
            }

        }

    }


    // Create a notification
    private Notification createNotification(String msg, PendingIntent notificationPendingIntent) {
        Log.i(TAG, "createNotification: " + msg);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        notificationBuilder.setSmallIcon(R.mipmap.geofence_icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.geofence_icon))
                .setContentTitle(msg)
               .setContentText(getString(R.string.geofence_txt))
                .setWhen(System.currentTimeMillis())
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(notificationPendingIntent)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setColor(Color.RED)
                .setChannelId(CHANNEL_ID)
                .setAutoCancel(true);
        return notificationBuilder.build();

    }




    private static String getErrorString(int errorCode) {
        switch (errorCode) {
            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return "GeoFence not available";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return "Too many GeoFences";
            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return "Too many pending intents";
            default:
                return "Unknown error.";

        }

    }

    private String getTransitionString(int transitionType) {
        switch (transitionType) {
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return "location entered";
            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return "location exited";
            case Geofence.GEOFENCE_TRANSITION_DWELL:
                return "dwell at location";
            default:
                return "location transition";
        }

    }
}


