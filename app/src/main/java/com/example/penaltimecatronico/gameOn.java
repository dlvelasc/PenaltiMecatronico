package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gameOn extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String dificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_on);
        EditText dificil = (EditText)findViewById(R.id.dificultad);
        
    }
    public void inicializarBaseDeDatos(){
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference("users");
    }
    public void setDificultad(){
        inicializarBaseDeDatos();

    }

    public void botPatear(View v){
        inicializarBaseDeDatos();
        reference.child("Sistemas").child("kick").setValue(1);
    }

    public void botMenu(View v){
        Button btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
}