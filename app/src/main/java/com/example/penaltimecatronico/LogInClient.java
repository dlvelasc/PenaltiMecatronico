package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogInClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.star_sesion_client);
    }

    public void BotInicio(View v){
        Button btn = (Button) findViewById(R.id.buttonLogInClient);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Menu.class );
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