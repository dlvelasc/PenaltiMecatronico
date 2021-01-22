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


public class LogInClient extends AppCompatActivity { /*Clase que verifica el acceso de un usuario ya previamente registrado*/
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
    public void inicializarBaseDeDatos(){ //Método que inicializa la base de datos y se la declara como referencia
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference(); // delaracion de referencia
    }

    public void setSesion(String data1){
        String date = LocalDateTime.now().toString(); //extraer la fecha y hora para usarla como id de la sesion iniciada
        sesionSistema = date.replace("-","").replace(":","").replace(".","");
        reference.child("Sistema").child("sesion").setValue(sesionSistema); // setea como variable de Sistema la sesion actual del usuario jugando
        DatabaseReference sesion = reference.child("users").child(data1).child("sesiones").child(sesionSistema); // crea la sesion dentro del nodo de sesiones del usuario logueado
        sesion.child("goles").setValue(0); //
        sesion.child("intentos").setValue(1);
        sesion.child("fecha").setValue(date);
        sesion.child("dificultad").setValue(0);
    }

    public void BotInicio(View v){ //Boton inicio lleva al siguiente intent de menu una vez verificado el usuario
        inicializarBaseDeDatos();
        username = userET.getText().toString().trim();
        pass = passwordET.getText().toString().trim();
        validarLogIn(); // función que valida que el usuario exista

    }
    public void validarLogIn(){ //Metodo encargado de verificar que los datos ingresados sean de un usuario ya registrato
        reference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { //recorrer los valores de este nodo
                if(snapshot.hasChild(username)){
                    UserHelperClass user = snapshot.child(username).getValue(UserHelperClass.class); //obtener valores del usuario coincidente encontrado
                    String data1 = user.getUsername();
                    String data2 = user.getPassword();
                    if(pass.equals(data2)){
                        activeUser = data1; //set el username del usuario que está activo
                        //setear el nombre de usuario activo en la base de datos como una variable del nodo Sistema y el ESP-32 sepa a que path ir
                        reference.child("Sistema").child("user").setValue(activeUser);
                        vacio();
                        Intent intent = new Intent(getApplicationContext(),Menu.class);
                        setSesion(data1);
                        startActivity(intent); //inicio de otra actividad si la contraseña también está correcta
                    }else {
                        Toast.makeText(LogInClient.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show(); //Mensaje instantáneo que alerta al usuario de equivocarse
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
    public void BotAtras2(View v){ //Boton que lleva al menu principal
        finish();

    }
    public void vacio(){ // funcion que permite limpiar los campos de los EditText
        userET.setText("");
        passwordET.setText("");
    }
}