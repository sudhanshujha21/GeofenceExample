package com.example.sudhanshuj.geofenceexample.utils;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

class Constants {



    public Constants() {
    }

    private static final String PACKAGE_NAME = "com.example.sudhanshuj.geofenceexample";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    private static final  long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     *  geofences expire after twelve hours.
     */

   public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
  public   static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km

    /**
     * Map for storing information about Chetu India
     */
   private static final HashMap<String, LatLng> CHETU_INDIA = new HashMap<>();

    static {
        CHETU_INDIA.put("CHETU", new LatLng(28.6214, 77.3789));

        // GIP MALL
        CHETU_INDIA.put("GIP", new LatLng(28.5675,77.3262));
    }



}
