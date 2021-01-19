package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class gameOn extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    int dificultad;
    Button butonSet;
    EditText dificil;
    Switch switchE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_on);
        dificil = (EditText)findViewById(R.id.dificultad);
        switchE = (Switch)findViewById(R.id.patea);

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
        dificultad = Integer.parseInt(dificil.getText().toString());
        reference.child("Sistema").child("nivel").setValue(dificultad);
        reference.child("users").child(LogInClient.activeUser).child("sesiones").child(LogInClient.sesionSistema).child("dificultad").setValue(dificultad);
        if(dificultad !=0){
            butonSet.setEnabled(false);
        }

    }
    public void onclick(View view){
        if(view.getId()==R.id.patea){
            if(switchE.isChecked()){
                Toast.makeText(gameOn.this,"Ha sido activado",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void botMenu(View v){
        butonSet.setEnabled(true);
        dificil.setText("");
        reference.child("Sistema").child("nivel").setValue(0);
        Intent intent = new Intent(v.getContext(), Menu.class);
        startActivity(intent);
    }
    public void vacio(){

    }
}