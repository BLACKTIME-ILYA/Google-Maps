package com.sourceit.maps.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sourceit.maps.R;
import com.sourceit.maps.ui.Json.Result;

import static com.sourceit.maps.ui.ListActivity.SELECTED;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    LatLng bar;
    Location location;
    LocationManager locationManager;
    String providerName;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        intent = getIntent();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        providerName = locationManager.getBestProvider(criteria, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }

        location = locationManager.getLastKnownLocation(providerName);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (intent.getBooleanExtra(ListActivity.LIST, false) == true) {
            bar = new LatLng(Data.barsList.results.get(intent.getIntExtra(SELECTED, 0)).geometry.location.lat, Data.barsList.results.get(intent.getIntExtra(SELECTED, 0)).geometry.location.lng);
            googleMap.addMarker(new MarkerOptions().position(bar).title("Bar"));
        } else {
            for (Result result : Data.barsList.results) {
                googleMap.addMarker(new MarkerOptions().position(new LatLng(result.geometry.location.lat, result.geometry.location.lng)).title(result.name));
            }
        }
        googleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Current"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}
