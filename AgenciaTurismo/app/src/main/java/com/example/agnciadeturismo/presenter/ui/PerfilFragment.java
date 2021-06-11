package com.example.agnciadeturismo.presenter.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.viewmodel.ViewModelCliente;

public class PerfilFragment extends Fragment {

    RelativeLayout layoutCompras, layoutCartoes, layoutSair;
    TextView textViewNome;
    ClienteDto cliente;
    ViewModelCliente viewModelCliente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        initView(view);
        onClick();

        return view;
    }

    private void initView(View view) {
        viewModelCliente = new ViewModelProvider(getActivity()).get(ViewModelCliente.class);
        layoutCompras = view.findViewById(R.id.layoutCompras);
        layoutCartoes = view.findViewById(R.id.layoutCartoes);
        layoutSair = view.findViewById(R.id.layoutSair);
        textViewNome = view.findViewById(R.id.txt_nomeUsuario);

        cliente = ((MainActivity) getActivity()).getUsuario();
        if(cliente.getCpf() != null){
            textViewNome.setText(cliente.getNome());
        }
    }

    private void onClick() {
        layoutSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setUsuario(new ClienteDto(null, null, null, null, null, null, null, null));
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_home, new LoginFragment())
                        .commit();
            }
        });

        layoutCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                mudarTela("Compras", intent);
            }
        });

        layoutCartoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                mudarTela("Cartão", intent);
            }
        });

        textViewNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastrarUsuarioActivity.class);
                mudarTela("", intent);
            }
        });
    }

    private void mudarTela(String activity, Intent intent){
        intent.putExtra("activity", activity);
        intent.putExtra("nome", cliente.getNome());
        intent.putExtra("cpf", cliente.getCpf());
        intent.putExtra("email", cliente.getEmail());
        intent.putExtra("rg", cliente.getRg());
        intent.putExtra("tel", cliente.getTelefone());
        intent.putExtra("senha", cliente.getSenha());
        intent.putExtra("img", cliente.getImg());
        startActivity(intent);
    }
}