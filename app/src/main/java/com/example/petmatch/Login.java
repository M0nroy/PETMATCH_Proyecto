package com.example.petmatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;

public class Login extends AppCompatActivity {

    private EditText correo, password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.etCorreo);
        password = findViewById(R.id.etPassword);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // El usuario ya ha iniciado sesión, puedes redirigirlo a la pantalla de perfil directamente
            Intent i = new Intent(getApplicationContext(), Bienvenida .class);
            startActivity(i);
            finish();
        }
    }

    public void limpiarCampos() {
        correo.setText("");
        password.setText("");
    }

    public void iniciarSesion(View view) {
        // Obtener los valores de los campos de correo y contraseña
        String correoStr = correo.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();

        // Verificar que los campos no estén vacíos
        if (correoStr.isEmpty() || passwordStr.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return; // Salir del método si hay campos vacíos
        }

        // Continuar con el inicio de sesión si los campos no están vacíos
        mAuth.signInWithEmailAndPassword(correoStr, passwordStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Inicio correctamente", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(getApplicationContext(), Bienvenida.class);
                            startActivity(i);
                            limpiarCampos();
                            finish(); // Cerrar la actividad actual después de iniciar sesión
                        } else {
                            Toast.makeText(getApplicationContext(), "Autenticacion Fallida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
