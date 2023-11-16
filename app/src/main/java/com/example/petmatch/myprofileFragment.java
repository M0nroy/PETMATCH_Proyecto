package com.example.petmatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link myprofileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class myprofileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //mis propios parametros
    private TextView nombre, correo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public myprofileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment myprofileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static myprofileFragment newInstance(String param1, String param2) {
        myprofileFragment fragment = new myprofileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void cerrarSesion(View view) throws Throwable {
            FirebaseAuth.getInstance().signOut();
            finalize();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_myprofile,container,false);
        nombre = v.findViewById(R.id.tvNombre);
        correo = v.findViewById(R.id.tvCorreo);

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

        // Inflate the layout for this fragment
        return v;
    }
}