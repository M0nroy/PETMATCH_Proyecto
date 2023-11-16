package com.example.petmatch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chat extends AppCompatActivity {

    private CircleImageView fotoPerfil;
    private TextView nombre;
    private RecyclerView rvMensaje;
    private EditText txtMensaje;
    private Button btnEnviar;
    private AdapterMensajes adapter;
    private ImageButton btnEnviarFoto;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private static final int PHOTO_SEND = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        fotoPerfil = (CircleImageView) findViewById(R.id.fotoPerfil);
        nombre = (TextView) findViewById(R.id.nombre);
        rvMensaje = (RecyclerView) findViewById(R.id.rvMensajes);
        txtMensaje = (EditText) findViewById(R.id.txtMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviarFoto = (ImageButton) findViewById(R.id.btnEnviarFoto);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("usuarios/chat");

        storage = FirebaseStorage.getInstance();
        storageReference= storage.getReference();

        adapter = new AdapterMensajes(this);
        LinearLayoutManager l = new LinearLayoutManager(this);
        rvMensaje.setLayoutManager(l);
        rvMensaje.setAdapter(adapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mensaje = txtMensaje.getText().toString().trim();


                if (!mensaje.isEmpty()) {

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                    String horaActual = sdf.format(new Date());

                    // Luego, utiliza horaActual en tu código
                    databaseReference.push().setValue(new Mensaje(mensaje, nombre.getText().toString(), "", "1", horaActual));
                    txtMensaje.setText("");
                }
            }
        });


        btnEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona una foto"),PHOTO_SEND);
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                setScrollBar();
            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Mensaje m = snapshot.getValue(Mensaje.class);
                adapter.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void setScrollBar(){
        rvMensaje.scrollToPosition(adapter.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK) {
            Uri u = data.getData();

            if (!TextUtils.isEmpty(nombre.getText().toString())) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                String horaActual = sdf.format(new Date());

                storageReference = storage.getReference("Imagenes_chat");
                final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());

                fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Mensaje m = new Mensaje("Recibio una foto", u.toString(), nombre.getText().toString(), "", "2", horaActual);
                        databaseReference.push().setValue(m);
                    }
                });

            } else {
                Toast.makeText(this, "El mensaje está vacío", Toast.LENGTH_SHORT).show();
            }
        }

    }
}