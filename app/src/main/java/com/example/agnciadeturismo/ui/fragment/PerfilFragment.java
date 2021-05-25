package com.example.agnciadeturismo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.ui.DashboardActivity;

public class PerfilFragment extends Fragment {

    RelativeLayout layoutCompras, layoutCartoes, layoutSair;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        layoutCompras = view.findViewById(R.id.layoutCompras);
        layoutCartoes = view.findViewById(R.id.layoutCartoes);
        layoutSair = view.findViewById(R.id.layoutSair);

        onClick();

        return view;
    }

    private void onClick() {
        layoutCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDashboard("Compras");
            }
        });

        layoutCartoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDashboard("Cartão");
            }
        });
        layoutSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new LoginFragment())
                        .commit();
            }
        });
    }

    private void onClickDashboard(String nome){
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        intent.putExtra("nome", nome);
        startActivity(intent);
    }
}