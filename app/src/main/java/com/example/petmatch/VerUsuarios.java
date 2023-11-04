package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class VerUsuarios extends AppCompatActivity {

    private TextView tvId,tvNombre, tvApellido, tvCorreo, tvPassword;

    ControladorDB admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuarios);

        // Inicializar los TextViews
        tvId = findViewById(R.id.tvId);
        tvNombre = findViewById(R.id.tvNombre); // Reemplaza "tvNombre" con el ID real de tu TextView en XML
        tvApellido = findViewById(R.id.tvApellido); // Reemplaza "tvApellido" con el ID real de tu TextView en XML
        tvCorreo = findViewById(R.id.tvCorreo); // Reemplaza "tvCorreo" con el ID real de tu TextView en XML
        tvPassword = findViewById(R.id.tvPassword); // Reemplaza "tvPassword" con el ID real de tu TextView en XML

        admin = new ControladorDB(this, "DBPetmatch", null, 1);
    }

    public void consulta(View view) {
        SQLiteDatabase db = admin.getWritableDatabase();



        Cursor fila = db.rawQuery("SELECT apellido, correo, password FROM usuarios ", null);

        if (fila.moveToFirst()) {
            tvApellido.setText(fila.getString(0));
            tvNombre.setText(fila.getString(1));
            // etPassword.setText(fila.getString(2)); // Descomenta esta línea si deseas mostrar la contraseña en un EditText
        } else {
            Toast.makeText(this, "Error, no existe el usuario", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


}
