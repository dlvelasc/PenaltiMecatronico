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
//clase o activity usada en la ventana de estadisticas del usuario
public class Estadistics extends AppCompatActivity {
    FirebaseDatabase rootNode;//referencias de la base de satos
    DatabaseReference reference;
TableLayout scores;//TableLayout correspondiente a donde se muestran la estadisticas

ArrayList<Sesion> sesions;//Estructura donde se guardan las sesiones del usuario correspondiente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadistics);//crecaion de vantana del activity
        initTable();//inicializa el tableview incluyendo sus encabezados
        getData();//llena la tqbla con los datos de las sesiones
        sesions=new ArrayList<>();//crea el array para las sesiones




    }

    public void getData() {
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        rootNode.getReference().child("users").child(LogInClient.activeUser).child("sesiones").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sn: snapshot.getChildren()){//todas las sesiones se encuentran dentro de snapchot
                    Sesion s=sn.getValue(Sesion.class);//se recorre sesion por sesion y se la pasa a un objeto tipo sesion
                            addToTable(s);//se aniade la sesion a la tabla
  }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    //crea la tabla y sus encabezados
public void initTable(){
    scores = (TableLayout) findViewById(R.id.scores);//obtiene el table layout del sml
    scores.setStretchAllColumns(true);//crea columnas flexibles
    scores.bringToFront();
    TableRow encabezado= new TableRow(getApplicationContext());//se crea fila del encabezado
    TextView fecha=new TextView(getApplicationContext());//se crea cuadro de texto que esta situado dentro de la tabla
    fecha.setText("Fecha");
    encabezado.addView(fecha);//sre agrega el cuadro de texto al encabezado
    TextView goles=new TextView(getApplicationContext());//se repiten los pasos previamente mencionados
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


public void addToTable(Sesion s){//refcive un obtejo tipo sesion, extrae la informacion del mismo y la tabula
    TableRow tr = new TableRow(getApplicationContext());//nueva fila correspondiente aesa sesion
    TextView date = new TextView(getApplicationContext());//se crea el texto de cada seccion de informacion de la sesion
    date.setText(s.getFecha().split("T")[0]);
    TextView goals = new TextView(getApplicationContext());
    goals.setText(String.valueOf(s.getGoles()));
    TextView shots = new TextView(getApplicationContext());
    shots.setText(String.valueOf(s.getIntentos()));
    TextView points = new TextView(getApplicationContext());
    points.setText(String.valueOf(s.getGoles() * 1.1));
    TextView accuracy = new TextView(getApplicationContext());
    accuracy.setText(String.valueOf(s.getGoles() / s.getIntentos()));
    tr.addView(date);//se agregan las cajas de texto a la fila de la sesion
    tr.addView(goals);
    tr.addView(shots);
    tr.addView(points);
    tr.addView(accuracy);
    scores.addView(tr);//se agrega la fila al table row

}


    public void botMenuEstadist(View v) {//metodo del boton para regresar al activity menu
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