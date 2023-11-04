package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void iniciarSesion(View view) {

        Intent iniciarS = new Intent(this, Login.class);
        startActivity(iniciarS);
    }

    public void registraseU(View view) {

        Intent registrase = new Intent(this, registrarse.class);
        startActivity(registrase);
    }
}