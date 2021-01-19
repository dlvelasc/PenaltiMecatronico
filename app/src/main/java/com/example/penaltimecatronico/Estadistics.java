package com.example.penaltimecatronico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Estadistics extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    ArrayList<Sesion> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadistics);
    }

    public void getData() {
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference().child("users").child(LogInClient.activeUser).child("sesiones");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                values = new ArrayList<>();

                for (DataSnapshot d : snapshot.getChildren()) {
                    Sesion s = d.getValue(Sesion.class);
                    values.add(s);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void populateTable() {
        TableLayout scores = (TableLayout) findViewById(R.id.scores);
        scores.setStretchAllColumns(true);
        

    }


    public void botMenuEstadist(View v) {
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Menu.class);
                startActivity(intent);
            }
        });


    }
}