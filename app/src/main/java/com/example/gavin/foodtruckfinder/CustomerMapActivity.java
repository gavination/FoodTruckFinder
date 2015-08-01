package com.example.gavin.foodtruckfinder;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CustomerMapActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_map);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        double latitude;
        double longituge;
        // mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);

        //grab Location manager object
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //get criteria for the location manager class
        Criteria locationCriteria = new Criteria();

        //Get the name of the provider. let the api figure out what the best provider is for gps
        String provider = locationManager.getBestProvider(locationCriteria, true);

        //Get current location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        if (myLocation != null){
            //Getting the latitude here
            latitude = myLocation.getLatitude();
            //And getting the longitude
            longituge = myLocation.getLongitude();
        }
        else{
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            //Getting the latitude here
            latitude = myLocation.getLatitude();
            //And getting the longitude
            longituge = myLocation.getLongitude();
        }


        //Set the map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


        //Did you know Java has LatLng objects? me either!
        LatLng latLng = new LatLng(latitude, longituge);

        //Show current location on map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Zoom in just right
        mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longituge)).title("Your current location"));





    }


}
