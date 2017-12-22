package com.example.vikhy.inclass12;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager mLocationManager;
    LocationListener mLocListener;
    boolean tracking = false;
    ArrayList<LatLng> latLngArrayList = new ArrayList<>();

    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 111;

    @Override
    protected void onResume() {
        super.onResume();

        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("GPS not enabled")
                    .setMessage("Would you turn on GPS?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mLocListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                latLngArrayList.add(new LatLng(location.getLatitude(), location.getLongitude()));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, // Activity
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, mLocListener);
        // Add a marker in Sydney and move the camera
      /*  LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
*/
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                if (tracking == false) {

                    tracking = true;

                    latLngArrayList.add(latLng);
                    Toast.makeText(MapsActivity.this, "Started tracking", Toast.LENGTH_SHORT).show();

                } else {
                    tracking = false;
                    Toast.makeText(MapsActivity.this, "Stopped tracking", Toast.LENGTH_SHORT).show();
                    mMap.addMarker(new MarkerOptions().position(new LatLng(latLngArrayList.get(0).latitude, latLngArrayList.get(0).longitude)).title("Start location"));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(latLngArrayList.get(latLngArrayList.size() - 1).latitude, latLngArrayList.get(latLngArrayList.size() - 1).longitude)).title("Ending location"));

                    LatLngBounds.Builder builder = new LatLngBounds.Builder();

                    ArrayList<Marker> markerArrayList = new ArrayList<>();
                    PolylineOptions polylineOptions = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
                    for (LatLng latLng1 : latLngArrayList
                            ) {


                        polylineOptions.add(latLng1);
                    }
                    Polyline polyline = mMap.addPolyline(polylineOptions);


                    for (LatLng latLng1 : latLngArrayList)

                        builder.include(new LatLng(latLng1.latitude, latLng1.longitude));

                    LatLngBounds bounds = builder.build();

                    int padding = 0; // offset from edges of the map in pixels
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);


                    mMap.moveCamera(cu);




                    /*for (LatLng latLng1 : latLngArrayList) {

                   *//*     LatLng point = new LatLng(latLng1.latitude, latLng1.longitude);
                        mMap.addMarker(new MarkerOptions().position(point).title("Marker in Sydney"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));

                     *//**//*   builder.include(new MarkerOptions().position(latLng1.latitude,latLng1.longitude));
                        builder.include(marker.getPosition());
                    *//**//*}
                    LatLngBounds bounds = builder.build();



                    for (LatLng latLng1 : latLngArrayList
                            ) {

                        polylineOptions.add(latLng1);
                    }*/


                    latLngArrayList = new ArrayList<>();

                }
            }
        });
    }

    // Get permission result
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted

                } else {
                    // permission was denied
                }
                return;
            }
        }

    }


}
