package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_sesion);


    }

    public void BotRegistro(View v){
        Button btn = (Button) findViewById(R.id.butSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class );
                startActivity(intent);
            }
        });
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