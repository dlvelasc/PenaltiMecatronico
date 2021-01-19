package com.example.penaltimecatronico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.time.LocalDateTime;

public class LogInClient extends AppCompatActivity {
    public EditText userET,passwordET;
    public static String activeUser;
    public static String sesionSistema;
    String username;
    String pass;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_sesion_client);
        userET = (EditText)findViewById(R.id.clientUserNText);
        passwordET = (EditText)findViewById(R.id.clientPassText);

    }
    public void inicializarBaseDeDatos(){
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference();
    }

    public void setSesion(String data1){
        String date = LocalDateTime.now().toString();
        sesionSistema = date.replace("-","").replace(":","").replace(".","");
        Toast.makeText(LogInClient.this,sesionSistema, Toast.LENGTH_SHORT).show();
        reference.child("Sistema").child("sesion").setValue(sesionSistema);
        DatabaseReference sesion = reference.child("users").child(data1).child("sesiones").child(sesionSistema);
        sesion.child("goles").setValue(0);
        sesion.child("intentos").setValue(0);
        sesion.child("fecha").setValue(date);
        sesion.child("dificultad").setValue(0);
    }

    public void BotInicio(View v){
        inicializarBaseDeDatos();
        username = userET.getText().toString().trim();
        pass = passwordET.getText().toString().trim();
        validarLogIn();

    }
    public void validarLogIn(){
        reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(username)){
                    UserHelperClass user = snapshot.child(username).getValue(UserHelperClass.class);
                    String data1 = user.getUsername();
                    String data2 = user.getPassword();
                    if(pass.equals(data2)){
                        activeUser = data1;
                        reference.child("Sistema").child("user").setValue(activeUser);
                        vacio();
                        Intent intent = new Intent(getApplicationContext(),Menu.class );
                        setSesion(data1);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LogInClient.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LogInClient.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void BotAtras2(View v){
        finish();

    }
    public void vacio(){
        userET.setText("");
        passwordET.setText("");
    }
}