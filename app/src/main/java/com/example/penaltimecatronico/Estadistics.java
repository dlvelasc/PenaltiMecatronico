package com.example.penaltimecatronico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
TableLayout scores;
Estadistics con;
ArrayList<Sesion> sesions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadistics);
        initTable();
        getData();
        sesions=new ArrayList<>();




    }

    public void getData() {
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        rootNode.getReference().child("users").child(LogInClient.activeUser).child("sesiones").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sn: snapshot.getChildren()){
                    Sesion s=sn.getValue(Sesion.class);
                            addToTable(s);
  }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
public void initTable(){
    scores = (TableLayout) findViewById(R.id.scores);
    scores.setStretchAllColumns(true);
    scores.bringToFront();
    TableRow encabezado= new TableRow(getApplicationContext());
    TextView fecha=new TextView(getApplicationContext());
    fecha.setText("Fecha");
    encabezado.addView(fecha);
    TextView goles=new TextView(getApplicationContext());
    goles.setText("Goles");
    encabezado.addView(goles);
    TextView tiros=new TextView(getApplicationContext());
    tiros.setText("Tiros");
    encabezado.addView(tiros);
    TextView puntos=new TextView(getApplicationContext());
    puntos.setText("Puntos");
    encabezado.addView(puntos);
    TextView acierto=new TextView(getApplicationContext());
    acierto.setText("% acierto");
    encabezado.addView(acierto);
    scores.addView(encabezado);

}


public void addToTable(Sesion s){
    TableRow tr = new TableRow(getApplicationContext());
    TextView date = new TextView(getApplicationContext());
    date.setText(s.getFecha().split("T")[0]);
    TextView goals = new TextView(getApplicationContext());
    goals.setText(String.valueOf(s.getGoles()));
    TextView shots = new TextView(getApplicationContext());
    shots.setText(String.valueOf(s.getIntentos()));
    TextView points = new TextView(getApplicationContext());
    points.setText(String.valueOf(s.getGoles() * 1.1));
    TextView accuracy = new TextView(getApplicationContext());
    accuracy.setText(String.valueOf(s.getGoles() / s.getIntentos()));
    tr.addView(date);
    tr.addView(goals);
    tr.addView(shots);
    tr.addView(points);
    tr.addView(accuracy);
    scores.addView(tr);

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