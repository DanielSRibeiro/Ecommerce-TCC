package com.example.agnciadeturismo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.ui.activities.CadastrarCartoesActivity;
import com.example.agnciadeturismo.ui.activities.DashboardActivity;
import com.example.agnciadeturismo.ui.activities.MainActivity;
import com.example.agnciadeturismo.ui.adapter.CartaoAdapter;
import com.example.agnciadeturismo.ui.adapter.OfertaAdapter;
import com.example.agnciadeturismo.ui.adapter.OnClickItemCartao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartaoFragment extends Fragment implements OnClickItemCartao {

    FloatingActionButton fabCadastrar;
    RecyclerView recyclerViewCartao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartao, container, false);
        initView(view);
        atualizaAdapter();

        fabCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastrarCartoesActivity.class);
                intentCadastrar(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        fabCadastrar = view.findViewById(R.id.fab_cadastrar);
        recyclerViewCartao = view.findViewById(R.id.recycler_cartao);
        ((DashboardActivity) getActivity()).setTitulo("Cartão", "Selecionar o Cartão para efetuar a compra");
    }

    private void atualizaAdapter() {
        CartaoAdapter adapter = new CartaoAdapter(this);
        recyclerViewCartao.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCartao.setAdapter(adapter);
    }

    private void intentCadastrar(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onClick(int posicao) {
        Toast.makeText(getActivity(), "Adicionado no carrinho", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("carrinho", "carrinho");
        intentCadastrar(intent);
    }
}