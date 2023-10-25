package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler manejador = new Handler();

        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                Intent objVentana = new Intent(MainActivity.this, inicio.class);
                startActivity(objVentana);
            }
        }, 3000);
    }
}