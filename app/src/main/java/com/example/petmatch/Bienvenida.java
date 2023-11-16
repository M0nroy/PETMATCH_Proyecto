package com.example.petmatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.petmatch.databinding.ActivityBienvenidaBinding;
import com.example.petmatch.databinding.ActivityMainBinding;

public class Bienvenida extends AppCompatActivity {

    ActivityBienvenidaBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityBienvenidaBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        replaceFragment();
        mainBinding.bottomNavigationView.setOnItemSelectedListener(item->
        {

            if(  item.getItemId()==R.id.mi_perfil){
                replaceFragment(new myprofileFragment());
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
}