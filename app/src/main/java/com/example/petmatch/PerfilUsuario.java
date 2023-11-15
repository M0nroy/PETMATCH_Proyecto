package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class PerfilUsuario extends AppCompatActivity {

    private TextView nombre, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        nombre = findViewById(R.id.tvNombre);
        correo = findViewById(R.id.tvCorreo);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String name = user.getDisplayName();
            String email = user.getEmail();

            if (name != null) {
                nombre.setText("Nombre: " + name);
            }

            if (email != null) {
                correo.setText("Correo: " + email);
            }

            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();
        }
    }

    public void cerrarSesion(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
