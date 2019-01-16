package com.example.sudhanshuj.geofenceexample.activities;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sudhanshuj.geofenceexample.R;
import com.example.sudhanshuj.geofenceexample.backgroundservices.GeofenceRegistrationService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class GeofenceActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
        , ResultCallback<Status> ,GoogleMap.OnMapClickListener{

    private static final String GEOFENCE_ID = "Geofence";
    private static  final int NOTIFICATION_RESPONSIVENESS = 1000;
    private final double latitude =28.6214;
    private final double longitude= 77.3789;
    private final float radius = 99;


    private GoogleApiClient googleApiClient;

    private static final int REQUEST_LOCATION_PERMISSION_CODE = 101;

    private PendingIntent pendingIntent;

    private MarkerOptions markerOptions;

   // boolean isMonitoring = false;

    private Marker currentLocationMarker;

    private static final String TAG = GeofenceActivity.class.getSimpleName();

    private FusedLocationProviderClient mFusedLocationClient;
    private GeofencingClient mGeofencingClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_geofence);
        // initialize GoogleMaps
        initGMaps();

        }

    /**
     * Initialize the google map
     *
     */
    private void initGMaps() {
        mGeofencingClient = LocationServices.getGeofencingClient(GeofenceActivity.this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(GeofenceActivity.this);

        }
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION_CODE);
        }


    }

    /**
     *@param googleMap
     * Called when the map is ready to be used.
     * A non-null instance of a Google Map associated with the MapFragment.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady()");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMinZoomPreference(15);
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMapClickListener(this);
       /* boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e(TAG, "Style parsing failed.");
        }*/
        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Chetu India"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 17f));
       googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        //add circle
        googleMap.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .radius(radius)
                .strokeColor(Color.RED)
                .fillColor(Color.argb(20,0,255,0))
                .zIndex(55)
                .strokeWidth(6));
        //   circle.setVisible(true);

        // add polygon

        googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(32.974508,-97.334948),
                        new LatLng(32.973239,-97.329873),
                        new LatLng(32.970606,-97.332519),
                        new LatLng(32.970785, -97.335965))
                .strokeColor(Color.RED)
                .strokeWidth(7)
                .fillColor(Color.argb( 25,255,0,0)));

        // TODO:  add polygon for termination point

        googleMap.addPolygon(new PolygonOptions()
                .add(new LatLng(32.772467,-96.618180),
                        new LatLng(32.773177,-96.610702),
                        new LatLng(32.769388,-96.610638),
                        new LatLng(32.767913, -96.614325))
                .strokeColor(Color.RED)
                .strokeWidth(7)
                .fillColor(Color.argb( 25,255,0,0)));


    }
    /**
     * It's given the current latitude and longitude of the user.
     * It's monitor the user movements.
     */
    private void startLocationMonitor() {
        Log.d(TAG, "start location monitor");
        try {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    /*if(currentLocationMarker!=null){
                        currentLocationMarker.remove();
                    }*/
                    if(location!=null){
                        markerOptions = new MarkerOptions();
                        markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
                        markerOptions.title(getString(R.string.current_loc_txt));
                        // currentLocationMarker = mMap.addMarker(markerOptions);
                        Log.d(TAG, "Location Change Lat Lng " + location.getLatitude() + " " + location.getLongitude());
                    }



                }
            });

        } catch (SecurityException e) {
            Log.d(TAG, e.getMessage());
        }

    }
    /**
     * it's start the geofence
     */
    private void startGeofencing() {
        Log.d(TAG, "Start geofencing monitoring call");
        pendingIntent = getGeofencePendingIntent();
        GeofencingRequest geofencingRequest = new GeofencingRequest.Builder()
                .setInitialTrigger(Geofence.GEOFENCE_TRANSITION_ENTER)
                .addGeofence(getGeofence())
                .build();

        if (!googleApiClient.isConnected()) {
            Log.d(TAG, "Google Api Client is not Connected");

        } else {
            try {
                mGeofencingClient.addGeofences(geofencingRequest,pendingIntent).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Successfully Geofencing Connected");

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Failed to add Geofencing " + e.getMessage());

                    }
                });

                } catch (SecurityException e) {
                Log.d(TAG, e.getMessage());

            }

        }
       // isMonitoring = true;


    }

    // Create a Geofence
    private Geofence getGeofence() {
        Log.d(TAG, "createGeofence");
        return new Geofence.Builder()
                .setRequestId(GEOFENCE_ID)
                .setCircularRegion(latitude, longitude,radius)
                .setNotificationResponsiveness(NOTIFICATION_RESPONSIVENESS)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                //  .setLoiteringDelay(30000) // check every 30 seconds.
                .build();

    }
    private PendingIntent getGeofencePendingIntent() {
        Log.d(TAG, "createGeofencePendingIntent");
        if (pendingIntent != null) {
            return pendingIntent;
        }
        Intent intent = new Intent(this, GeofenceRegistrationService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


    }



    private void stopGeofencing() {
        pendingIntent = getGeofencePendingIntent();
        mGeofencingClient.removeGeofences(pendingIntent).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Stop geofencing");

            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Not stop geofencing");
            }
        });


        }

   @Override
    protected void onPause() {
        super.onPause();
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(GeofenceActivity.this);
        if (response != ConnectionResult.SUCCESS) {
            Log.d(TAG, "Google Play Service Not Available");
            GoogleApiAvailability.getInstance().getErrorDialog(GeofenceActivity.this, response, 1).show();
        } else {
            Log.d(TAG, "Google play service available");
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.reconnect();
    }
    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }
    @Override
    public void onResult(@NonNull Status status) {
        Log.i(TAG, "onResult: " + status);

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Google Api Client Connected");
      //  isMonitoring = true;
        startGeofencing();
        startLocationMonitor();
    }
    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Google Connection Suspended");

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //isMonitoring = false;
        Log.e(TAG, "Connection Failed:" + connectionResult.getErrorMessage());

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onMapClick(LatLng latLng) {
       Log.i(TAG,"latLog:" +latLng.toString());



    }
}


