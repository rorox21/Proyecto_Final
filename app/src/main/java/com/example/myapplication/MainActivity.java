package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EditText tipoActividadEditText, distanciaEditText, tiempoEditText;
    private Button registrarActividadBtn, verHistorialBtn, verHospitalesBtn;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        tipoActividadEditText = findViewById(R.id.tipoActividadEditText);
        distanciaEditText = findViewById(R.id.distanciaEditText);
        tiempoEditText = findViewById(R.id.tiempoEditText);
        registrarActividadBtn = findViewById(R.id.registrarActividadBtn);
        verHistorialBtn = findViewById(R.id.verHistorialBtn);
        verHospitalesBtn = findViewById(R.id.verHospitalesBtn);

        registrarActividadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarActividad();
            }
        });

        verHistorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HistorialActivity.class));
            }
        });

        verHospitalesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),activity_map.class);
                startActivity(intent);
            }
        });
    }

    private void registrarActividad() {
        String tipoActividad = tipoActividadEditText.getText().toString().trim();
        String distancia = distanciaEditText.getText().toString().trim();
        String tiempo = tiempoEditText.getText().toString().trim();

        if (!tipoActividad.isEmpty() && !distancia.isEmpty() && !tiempo.isEmpty()) {
            // Crear un objeto para almacenar la actividad
            Map<String, Object> actividadMap = new HashMap<>();
            actividadMap.put("tipoActividad", tipoActividad);
            actividadMap.put("distancia", distancia);
            actividadMap.put("tiempo", tiempo);

            String actividadId = databaseReference.child("actividades").push().getKey();

            databaseReference.child("actividades").child(actividadId).setValue(actividadMap);

            tipoActividadEditText.setText("");
            distanciaEditText.setText("");
            tiempoEditText.setText("");

            Toast.makeText(MainActivity.this, "Actividad registrada con éxito", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
