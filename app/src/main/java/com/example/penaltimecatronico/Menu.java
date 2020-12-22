package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void botEstadistica(View v){
        Button btn = (Button) findViewById(R.id.buttonPoints);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Estadistics.class );
                startActivity(intent);
            }
        });
    }

    public void botJugar(View v){
        Button btn = (Button) findViewById(R.id.buttonPlay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),gameOn.class );
                startActivity(intent);
            }
        });
    }

}