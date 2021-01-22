package com.example.penaltimecatronico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
/*
Clase que setea el splash con el logo de la aplicacion al iniciarla
 */
public class intro extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(intro.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}