package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    private ListView historialListView;
    private DatabaseReference databaseReference;
    private ArrayAdapter<String> historialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // Inicializar Firebase
        try {
            FirebaseApp.initializeApp(this);
            Log.d("HistorialActivity", "Firebase inicializado correctamente");
        } catch (Exception e) {
            Log.e("HistorialActivity", "Error al inicializar Firebase: " + e.getMessage());
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        historialListView = findViewById(R.id.historialListView);
        historialAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        historialListView.setAdapter(historialAdapter);

        // Obtener y mostrar el historial
        mostrarHistorial();
    }

    private void mostrarHistorial() {
        DatabaseReference historialRef = databaseReference.child("actividades");

        historialRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("HistorialActivity", "DataSnapshot: " + dataSnapshot);

                if (dataSnapshot.exists()) {
                    Log.d("HistorialActivity", "Existen datos");

                    for (DataSnapshot actividadSnapshot : dataSnapshot.getChildren()) {
                        try {
                            Actividad actividad = actividadSnapshot.getValue(Actividad.class);
                            if (actividad != null) {
                                Log.d("HistorialActivity", "Actividad: " + actividad.toString());
                                historialAdapter.add(actividad.toString());
                            }
                        } catch (Exception e) {
                            Log.e("HistorialActivity", "Error al obtener datos: " + e.getMessage());
                        }
                    }
                } else {
                    Log.d("HistorialActivity", "No existen datos");
                    historialAdapter.add("No hay actividades registradas.");
                }

                // Log para verificar si se está actualizando la lista
                Log.d("HistorialActivity", "Tamaño del historial después de actualizar: " + historialAdapter.getCount());
                Log.d("HistorialActivity", "Lista de actividades después de actualizar: " + historialAdapter.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("HistorialActivity", "Error al recuperar datos: " + databaseError.getMessage());
            }
        });
    }
}
