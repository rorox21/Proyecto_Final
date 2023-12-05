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
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        historialListView = findViewById(R.id.historialListView);
        historialAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        historialListView.setAdapter(historialAdapter);

        mostrarHistorial();
    }

    private void mostrarHistorial() {
        DatabaseReference historialRef = databaseReference.child("actividades");

        historialRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot actividadSnapshot : dataSnapshot.getChildren()) {
                        Actividad actividad = actividadSnapshot.getValue(Actividad.class);
                        if (actividad != null) {
                            historialAdapter.add(actividad.toString());
                        }
                    }
                } else {
                    historialAdapter.add("No hay actividades registradas.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("HistorialActivity", "Error al recuperar datos: " + databaseError.getMessage());
            }
        });
    }
}
