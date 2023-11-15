package com.example.petmatch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class Mejore_momentos extends AppCompatActivity {

    private MaterialButton subirImagen;
    private StorageReference storageReference;

    private static final int GALLERY_INTENT = 1;
    private List<Uri> selectedImageUris;  // Lista para almacenar URIs de imágenes seleccionadas
    private List<ImageView> imagePreviews; // Lista para almacenar ImageView para previsualización
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mejore_momentos);

        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        subirImagen = findViewById(R.id.btnSeleccionarImg);

        // Inicializa las listas
        selectedImageUris = new ArrayList<>();
        imagePreviews = new ArrayList<>();
        imagePreviews.add((ImageView) findViewById(R.id.imagePreview));
        imagePreviews.add((ImageView) findViewById(R.id.imagePreview2));
        imagePreviews.add((ImageView) findViewById(R.id.imagePreview3));
        imagePreviews.add((ImageView) findViewById(R.id.imagePreview4));


        subirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Abre la galería para seleccionar una imagen
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            selectedImageUris.add(uri);

            // Muestra la previsualización de la imagen
            int previewIndex = selectedImageUris.size() - 1;
            if (previewIndex < imagePreviews.size()) {
                imagePreviews.get(previewIndex).setVisibility(View.VISIBLE);
                imagePreviews.get(previewIndex).setImageURI(uri);
            }
        }
    }

    public void subir(View view) {
        if (currentUser != null) {
            String userId = currentUser.getUid();
            String userName = currentUser.getDisplayName();

            for (int i = 0; i < selectedImageUris.size(); i++) {
                final Uri selectedImageUri = selectedImageUris.get(i);
                if (selectedImageUri != null) {
                    StorageReference filePath = storageReference.child("fotosMascotas").child(userId + "_" + selectedImageUri.getLastPathSegment());
                    filePath.putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Mejore_momentos.this, "Se subió la foto correctamente", Toast.LENGTH_SHORT).show();
                            // Limpiar variables para la próxima selección
                            selectedImageUris.clear();
                            for (ImageView imageView : imagePreviews) {
                                imageView.setVisibility(View.GONE);
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "Seleccione al menos una imagen antes de subir", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // El usuario no está autenticado, maneja esto según tus requisitos
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }

    public void omitir(View view) {
        Intent omitirM = new Intent(this, Bienvenida.class);
        startActivity(omitirM);
    }
}



