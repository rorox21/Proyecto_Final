package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class activity_map extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Button volver;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        volver = findViewById(R.id.btnZoom);
        volver.setVisibility(View.INVISIBLE);
        home = findViewById(R.id.btnInicio);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 9;

        LatLng hospitalBulnes = new LatLng(-36.73924, -72.29629);
        mMap.addMarker(new MarkerOptions().position(hospitalBulnes).title("Hospital de Bulnes"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospitalBulnes, zoomLevel));

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hospitalBulnes, zoomLevel));
                volver.setVisibility(View.INVISIBLE);
                home.setVisibility(View.VISIBLE);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                volver.setVisibility(View.VISIBLE);
                home.setVisibility(View.INVISIBLE);
                LatLng markerPosition = marker.getPosition();
                float zoom = 18f;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, zoom));
                return true;
            }
        });
    }
}
