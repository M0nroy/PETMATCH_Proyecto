package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Informacion_mascota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_mascota);
    }

    public void mejoresMomentos(View view) {

        Intent mejoresM = new Intent(this, Mejore_momentos.class);
        startActivity(mejoresM);
    }
}