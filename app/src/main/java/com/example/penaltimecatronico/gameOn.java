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

public class gameOn extends AppCompatActivity { //clase que contiene el activity del menu con las opciones de juego
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
    public void inicializarBaseDeDatos(){ //Método que inicializa la base de datos y se la declara como referencia
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference();
    }

    public void botPatear(View v){ //meotod onClick que setea la variable de sistema kick dentro de la base de datos para que el Esp32 lea que el usuario ha pateado
        inicializarBaseDeDatos();
        reference.child("Sistema").child("kick").setValue(1);
    }

    /*metodo onClick que lee la dificultad que elige el usuario y la setea en una variable de sistema en el database para la lectura por el esp32 además de
    setearla al usuario que se encuentra logueado
     */
    public void butSet(View v){
        butonSet = (Button)findViewById(R.id.butSet);
        inicializarBaseDeDatos();
        dificultad = Integer.parseInt(dificil.getText().toString());
        reference.child("Sistema").child("nivel").setValue(dificultad);
        reference.child("users").child(LogInClient.activeUser).child("sesiones").child(LogInClient.sesionSistema).child("dificultad").setValue(dificultad);
        if(dificultad !=0){ //Una vez llenado el campo de dificultad y presionar el boton este se bloquea hasta terminada la partida
            butonSet.setEnabled(false);
        }

    }
    public void onclick(View view){ // metodo que permite activar el switch para que se lea desde el sensor EMG
        if(view.getId()==R.id.patea){
            if(switchE.isChecked()){
                Toast.makeText(gameOn.this,"Ha sido activado",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void botMenu(View v){ //metodo onClick que lleva a la actividad menu ahcia atras y setea el valor de dificultad de nuevo en 0 plsito para la proxima partida
        butonSet.setEnabled(true);
        dificil.setText("");
        reference.child("Sistema").child("nivel").setValue(0);
        Intent intent = new Intent(v.getContext(), Menu.class);
        startActivity(intent);
    }

}