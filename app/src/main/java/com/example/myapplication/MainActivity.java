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
    private Button registrarActividadBtn, verHistorialBtn;  // Nuevos botones
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        // Inicializar los elementos de la interfaz de usuario
        tipoActividadEditText = findViewById(R.id.tipoActividadEditText);
        distanciaEditText = findViewById(R.id.distanciaEditText);
        tiempoEditText = findViewById(R.id.tiempoEditText);
        registrarActividadBtn = findViewById(R.id.registrarActividadBtn);
        verHistorialBtn = findViewById(R.id.verHistorialBtn);  // Nueva referencia

        registrarActividadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarActividad();
            }
        });

        verHistorialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir la actividad de historial
                startActivity(new Intent(MainActivity.this, historial.class));
            }
        });
    }

    // Método para registrar una actividad en Firebase
    private void registrarActividad() {
        // Validar la entrada del usuario
        if (!validarEntrada()) {
            return;
        }

        // Obtener datos del formulario
        String tipoActividad = tipoActividadEditText.getText().toString();
        double distancia = Double.parseDouble(distanciaEditText.getText().toString());
        int tiempo = Integer.parseInt(tiempoEditText.getText().toString());

        // Crear un objeto Map para almacenar los datos
        Map<String, Object> actividadMap = new HashMap<>();
        actividadMap.put("tipoActividad", tipoActividad);
        actividadMap.put("distancia", distancia);
        actividadMap.put("tiempo", tiempo);

        // Enviar datos al Realtime Database
        String key = databaseReference.child("actividades").push().getKey();
        databaseReference.child("actividades").child(key).setValue(actividadMap);

        // Éxito al enviar los datos
        Toast.makeText(MainActivity.this, "Actividad registrada con éxito", Toast.LENGTH_SHORT).show();
        limpiarCampos();
    }

    // Método para validar la entrada del usuario
    private boolean validarEntrada() {
        // Validar que los campos no estén vacíos
        if (tipoActividadEditText.getText().toString().isEmpty() ||
                distanciaEditText.getText().toString().isEmpty() ||
                tiempoEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Error: Completa todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Puedes agregar más validaciones según tus necesidades

        return true;
    }

    // Método para limpiar los campos después de registrar la actividad
    private void limpiarCampos() {
        tipoActividadEditText.setText("");
        distanciaEditText.setText("");
        tiempoEditText.setText("");
    }
}
