package com.example.agnciadeturismo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.ui.CadastrarCartoesActivity;
import com.example.agnciadeturismo.ui.DashboardActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartaoFragment extends Fragment {

    FloatingActionButton fabCadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartao, container, false);
        initView(view);

        fabCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastrarCartoesActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        fabCadastrar = view.findViewById(R.id.fab_cadastrar);
        ((DashboardActivity) getActivity()).setTitulo("Cartão", "Selecionar o Cartão para efetuar a compra");
    }
}