package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mejore_momentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mejore_momentos);
    }

    public void omitir(View view) {

        Intent omitirM = new Intent(this, Bienvenida.class);
        startActivity(omitirM);
    }
}