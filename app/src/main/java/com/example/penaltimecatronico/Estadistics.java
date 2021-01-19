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

import org.w3c.dom.Text;

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
        populateTable();
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
        getData();
        TableLayout scores = (TableLayout) findViewById(R.id.scores);
        scores.setStretchAllColumns(true);
        scores.bringToFront();
        TableRow encabezado= new TableRow(this);
        TextView fecha=new TextView(this);
        fecha.setText("Fecha");
        encabezado.addView(fecha);
        TextView goles=new TextView(this);
        goles.setText("Goles");
        encabezado.addView(goles);
        TextView tiros=new TextView(this);
        tiros.setText("Tiros");
        encabezado.addView(tiros);
        TextView puntos=new TextView(this);
        puntos.setText("Puntos");
        encabezado.addView(puntos);
        TextView acierto=new TextView(this);
        acierto.setText("% acierto");
        encabezado.addView(acierto);
        scores.addView(encabezado);
        for (Sesion s : values){
                TableRow tr=new TableRow(this);
                TextView date=new TextView(this);
                date.setText(s.getFecha());
                TextView goals=new TextView(this);
                goals.setText(s.getGoles());
                TextView shots=new TextView(this);
                shots.setText(s.getIntentos());
                TextView points=new TextView(this);
                points.setText(String.valueOf(Integer.parseInt(s.getGoles())*1.1));
                TextView accuracy=new TextView(this);
                accuracy.setText(String.valueOf(Integer.parseInt(s.getGoles())/Integer.parseInt(s.getIntentos())));
                tr.addView(date);
                tr.addView(goals);
                tr.addView(shots);
                tr.addView(points);
                tr.addView(accuracy);
                scores.addView(tr);
        }


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