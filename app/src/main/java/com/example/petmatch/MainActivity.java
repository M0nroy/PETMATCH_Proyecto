package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Verificar si hay un usuario autenticado
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                Intent intent;
                if (currentUser != null) {
                    // Si hay un usuario autenticado, ir a PerfilUsuario
                    intent = new Intent(MainActivity.this, Bienvenida.class);
                } else {
                    // Si no hay un usuario autenticado, ir a Login
                    intent = new Intent(MainActivity.this, Login.class);
                }

                finish();
                startActivity(intent);
            }
        }, 3000);  // Retraso de 3 segundos
    }
}
