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
    public static String dificultad;
    Button butonSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_on);
        EditText dificil = (EditText)findViewById(R.id.dificultad);
        dificultad = dificil.getText().toString();
    }
    public void inicializarBaseDeDatos(){
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference();
    }

    public void botPatear(View v){
        inicializarBaseDeDatos();
        reference.child("Sistema").child("kick").setValue(1);
    }
    public void butSet(View v){
        butonSet = (Button)findViewById(R.id.butSet);
        inicializarBaseDeDatos();
        reference.child("Sistema").child("nivel").setValue(dificultad);
        if(!dificultad.isEmpty()){
            butonSet.setEnabled(false);
        }

    }

    public void botMenu(View v){
        butonSet.setEnabled(true);
        Intent intent = new Intent(v.getContext(), Menu.class);
        startActivity(intent);
    }
    public void vacio(){

    }
}