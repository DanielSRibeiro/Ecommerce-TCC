package com.example.agnciadeturismo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.agnciadeturismo.ui.CadastrarUsuarioActivity;
import com.example.agnciadeturismo.R;

public class LoginFragment extends Fragment {

    Button buttonLogin;
    TextView textViewCadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        buttonLogin = view.findViewById(R.id.btn_login);
        textViewCadastrar = view.findViewById(R.id.txt_cadastrarUsuario);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //realizar o login
                Fragment perfilFragment = new PerfilFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_home, perfilFragment)
                        .commit();
            }
        });

        textViewCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastrarUsuarioActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}