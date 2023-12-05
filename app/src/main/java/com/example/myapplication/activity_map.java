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
        
        agregarMarcador(-36.60601809807034, -72.10568388828969, "Centro Médico Crecersalud", 12f);
        agregarMarcador(-36.43004686225497, -71.9600747177866, "Hospital de San Carlos", 14f);
        agregarMarcador(-36.60845343277807, -72.09280747545738, "Hospital Clínico Herminda Martín", 13f);
    }

    private void agregarMarcador(double latitud, double longitud, String titulo, float zoom) {
        LatLng ubicacion = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(ubicacion).title(titulo));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, zoom));
    }
}
