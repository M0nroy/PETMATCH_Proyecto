package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.petmatch.databinding.ActivityBienvenidaBinding;
import com.example.petmatch.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Bienvenida extends AppCompatActivity {

    ActivityBienvenidaBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityBienvenidaBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        replaceFragment(new myprofileFragment());
        mainBinding.bottomNavigationView.setOnItemSelectedListener(item->
        {

            if(  item.getItemId()==R.id.mi_perfil){
                replaceFragment(new myprofileFragment());
            }
            else if( item.getItemId()==R.id.salir){
                cerrarSesion();
            }
            return true;
        });
    }

private void replaceFragment(Fragment fragment){

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    fragmentTransaction.replace(R.id.container_parent,fragment);

    fragmentTransaction.commit();

}

    public void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}