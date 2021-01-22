package com.example.penaltimecatronico;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
//Activity correspondiente a la ventana de registro del usuario
public class Register extends AppCompatActivity {

    public EditText userET,emailET,passwordET;
    public String username, email, password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    public Button btn;
    UserHelperClass helperClass;
    @Override

    //crea la ventana
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_sesion);
        //se buscan referencias a los views del xml
        btn = (Button) findViewById(R.id.butSignUp);
        userET = (EditText)findViewById(R.id.userNText);
        emailET = (EditText)findViewById(R.id.mailText);
        passwordET = (EditText)findViewById(R.id.passwText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            //se envia los datos de los campos a la base de datos para completar el registro
            public void onClick(View view) {
                sendData();
            }
        });
    }
    //envia los datos a la base de datos
    public void sendData(){
        inicializarBaseDeDatos();//configuracion inicial de las referencias de la base de datos
        username = userET.getText().toString();
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        helperClass = new UserHelperClass(username, email, password);//crea un usuario con esos datos
        validarRegister();//valida los datos con la informacion de la base de datos
    }

    public void validarRegister() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(username)) {//si NO encuentra el usuario en la base de datos
                    reference.child(username).setValue(helperClass);//crea el usuario
                    Toast.makeText(Register.this, "Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
                    vacio();

                } else {//si encuentra el usuario
                    Toast.makeText(Register.this, "Ya se encuentra registrado", Toast.LENGTH_SHORT).show();//muestra el mensaje

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void inicializarBaseDeDatos(){
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference("users");
    }

    public void BotAtras(View v){
        finish();
    }
    public void vacio(){//vacia los campos previamente llenados por el usuario
        userET.setText("");
        emailET.setText("");
        passwordET.setText("");
    }
}