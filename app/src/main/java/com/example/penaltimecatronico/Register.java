package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    public EditText userET,emailET,passwordET;
    public String username, email, password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_sesion);
        btn = (Button) findViewById(R.id.butSignUp);
        userET = (EditText)findViewById(R.id.userNText);
        emailET = (EditText)findViewById(R.id.mailText);
        passwordET = (EditText)findViewById(R.id.passwText) ;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
                Intent intent = new Intent(view.getContext(),MainActivity.class );
                startActivity(intent);
            }
        });
    }
    public void sendData(){
        inicializarBaseDeDatos();
        username = userET.getText().toString();
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        UserHelperClass helperClass = new UserHelperClass(username, email, password);
        reference.child(username).setValue(helperClass);

    }

    public void inicializarBaseDeDatos(){
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference("users");
    }

    public void BotAtras(View v){
        Button btn = (Button) findViewById(R.id.buttonBack1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class );
                startActivity(intent);
            }
        });
    }
}