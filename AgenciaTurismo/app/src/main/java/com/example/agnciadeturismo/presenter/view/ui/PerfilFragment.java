package com.example.agnciadeturismo.presenter.view.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.data.repository.ClienteRepositoryTask;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.presenter.viewmodel.ClienteViewModel;
import com.example.agnciadeturismo.presenter.view.services.UsuarioServices;
import com.squareup.picasso.Picasso;

public class PerfilFragment extends Fragment {

    RelativeLayout layoutCompras, layoutCartoes, layoutSair;
    ImageView imageViewCliente;
    LinearLayout linearLayoutPerfil;
    ClienteDto cliente;
    ClienteViewModel clienteViewModel;
    TextView textViewNomeUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        initView(view);
        onClick();

        return view;
    }

    private void initView(View view) {
        clienteViewModel = new ViewModelProvider(getActivity(), new ClienteViewModel.ViewModelFactory(new ClienteRepositoryTask())).get(ClienteViewModel.class);
        layoutCompras = view.findViewById(R.id.layoutCompras);
        layoutCartoes = view.findViewById(R.id.layoutCartoes);
        layoutSair = view.findViewById(R.id.layoutSair);
        linearLayoutPerfil = view.findViewById(R.id.linearPerfil);
        imageViewCliente = view.findViewById(R.id.img_usuario);
        textViewNomeUsuario = view.findViewById(R.id.txt_nomeUsuario);
        cliente = UsuarioServices.getUsuario();
        if(cliente.getImg() != "-1" && cliente.getImg() != null && cliente.getImg() != ""){
            Picasso.get().load(cliente.getImg()).into(imageViewCliente);
        }
        textViewNomeUsuario.setText(cliente.getNome());
    }

    private void onClick() {
        layoutSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioServices.clearUsuario();
                SharedPreferences preferences = getActivity().getSharedPreferences(LoginFragment.LOGIN_SHARED, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(LoginFragment.TA_LOGADO_SHARED, false);
                editor.apply();

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
                mudarTela("Cart??o", intent);
            }
        });
        linearLayoutPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastrarUsuarioActivity.class);
                mudarTela("", intent);
            }
        });
    }

    private void mudarTela(String activity, Intent intent){
        intent.putExtra("activity", activity);
        startActivity(intent);
    }
}