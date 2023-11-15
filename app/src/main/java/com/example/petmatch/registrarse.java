package com.example.petmatch;

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
import com.google.firebase.auth.UserProfileChangeRequest;
import androidx.annotation.NonNull;



public class registrarse extends AppCompatActivity {

    private EditText nombre, apellido, correo, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        nombre = findViewById(R.id.etNombre);
        apellido = findViewById(R.id.etApellido);
        correo = findViewById(R.id.etCorreo);
        password = findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    public void limpiarCampos(){
        nombre.setText("");
        apellido.setText("");
        correo.setText("");
        password.setText("");
    }

    public void registrarUsuario(View view){

        mAuth.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registrado Correctamente", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(nombre.getText().toString() + " " + apellido.getText().toString())
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Intent i = new Intent(getApplicationContext(), PerfilUsuario.class);
                                                startActivity(i);
                                                limpiarCampos();
                                            }
                                        }
                                    });
                        } else {

                            Toast.makeText(getApplicationContext(), "Autenticacion fallida", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
