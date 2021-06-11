package com.example.agnciadeturismo.presenter.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.presenter.ui.DashboardActivity;
import com.example.agnciadeturismo.presenter.adapter.CompraAdapter;

public class CompraFragment extends Fragment {

    RecyclerView recyclerViewCompra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compra, container, false);
        initView(view);
        atualizaAdapter();

        return view;
    }

    private void initView(View view) {
        recyclerViewCompra = view.findViewById(R.id.recycler_compra);
        ((DashboardActivity) getActivity()).setTitulo("Compras","Compras do Usuário");
    }

    private void atualizaAdapter() {
        CompraAdapter adapter = new CompraAdapter();
        recyclerViewCompra.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCompra.setAdapter(adapter);
    }
}