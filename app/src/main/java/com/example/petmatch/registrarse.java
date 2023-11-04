package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registrarse extends AppCompatActivity {

    private EditText etNombre, etApellido, etCorreo, etPassword;
    private String nombre,apellido,correo,password;
    ControladorDB admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);

        admin = new ControladorDB(this,"DBPetmatch",null,1);
    }

    public void limpiarCampos(){
        etNombre.setText("");
        etApellido.setText("");
        etCorreo.setText("");
        etPassword.setText("");
    }

    public void registrarse(View view) {
        SQLiteDatabase db = admin.getWritableDatabase();

        nombre=etNombre.getText().toString();
        apellido=etApellido.getText().toString();
        correo=etCorreo.getText().toString();
        password=etPassword.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre",nombre);
        registro.put("apellido",apellido);
        registro.put("correo",correo);
        registro.put("password",password);

        long resultado = db.insert("usuarios",null,registro);

        if (resultado != -1) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            limpiarCampos();
            db.close();

            Intent proposito = new Intent(this, Proposito.class);
            startActivity(proposito);
        } else {
            Toast.makeText(this, "Error al registrarse", Toast.LENGTH_SHORT).show();
        }
    }

}