package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInClient extends AppCompatActivity {
    public EditText userET,emailET,passwordET;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_sesion_client);
    }
    public void inicializarBaseDeDatos(){
        rootNode = FirebaseDatabase.getInstance();// obtener valores de el nodo principal
        reference = rootNode.getReference("users");
    }

    public void BotInicio(View v){
        Button btn = (Button) findViewById(R.id.buttonLogInClient);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Menu.class );
                UserHelperClass helperClass = new UserHelperClass();
                startActivity(intent);
            }
        });
    }

    public void BotAtras2(View v){
        Button btn = (Button) findViewById(R.id.buttonBack2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class );
                startActivity(intent);
            }
        });
    }
}