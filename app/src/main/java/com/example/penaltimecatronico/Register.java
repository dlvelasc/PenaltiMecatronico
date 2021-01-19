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

public class Register extends AppCompatActivity {
    public EditText userET,emailET,passwordET;
    public String username, email, password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    public Button btn;
    UserHelperClass helperClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_sesion);
        btn = (Button) findViewById(R.id.butSignUp);
        userET = (EditText)findViewById(R.id.userNText);
        emailET = (EditText)findViewById(R.id.mailText);
        passwordET = (EditText)findViewById(R.id.passwText);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }
    public void sendData(){
        inicializarBaseDeDatos();
        username = userET.getText().toString();
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        helperClass = new UserHelperClass(username, email, password);
        validarRegister();
    }

    public void validarRegister() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(username)) {
                    reference.child(username).setValue(helperClass);
                    Toast.makeText(Register.this, "Se ha registrado exitosamente", Toast.LENGTH_SHORT).show();
                    vacio();

                } else {
                    Toast.makeText(Register.this, "Ya se encuentra registrado", Toast.LENGTH_SHORT).show();

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
    public void vacio(){
        userET.setText("");
        emailET.setText("");
        passwordET.setText("");
    }
}