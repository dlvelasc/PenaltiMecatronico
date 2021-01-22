package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Menu extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
    public void inicializarBaseDeDatos(){  //Método que inicializa la base de datos y se la declara como referencia
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
    }

    public void botEstadistica(View v){ //inicia otra actividad donde se encuentran las sesiones que tiene el jugador y sus resultados en cada una
        Button btn = (Button) findViewById(R.id.buttonPoints);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Estadistics.class );
                startActivity(intent);
            }
        });
    }
    public void botJugar(View v){ //inicia otra actividad  con un menu para el momento de jugar
        Intent intent = new Intent(v.getContext(),gameOn.class);
        startActivity(intent);
    }
    public void logOut(View view){ //cierra la sesion y reinicia las variables de sistema al valor inicial para dar paso a que otro usuario se loguee
        Button btn = (Button) findViewById(R.id.logout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inicializarBaseDeDatos();
                reference.child("Sistema").child("sesion").setValue("0");
                reference.child("Sistema").child("user").setValue("");
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }




}