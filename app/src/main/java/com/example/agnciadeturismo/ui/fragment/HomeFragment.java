package com.example.agnciadeturismo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.ui.BuscarActivity;
import com.example.agnciadeturismo.ui.adapter.RecentementeAdapter;

public class HomeFragment extends Fragment {

    AutoCompleteTextView autoCompleteTextViewTransporte;
    RecyclerView recyclerViewRecentemente;
    Button buttonPesquisar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        atualizaAdapter();

        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BuscarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void atualizaAdapter() {
        RecentementeAdapter adapter = new RecentementeAdapter();
        recyclerViewRecentemente.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRecentemente.setAdapter(adapter);
    }

    private void initView(View view) {
        autoCompleteTextViewTransporte = view.findViewById(R.id.autoCompleteTextView_transporte);
        recyclerViewRecentemente = view.findViewById(R.id.recycler_recentemente);
        buttonPesquisar = view.findViewById(R.id.btn_pesquisarPacote);
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] tipo_transporte = getResources().getStringArray(R.array.tipo_transporte);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, tipo_transporte);
        autoCompleteTextViewTransporte.setAdapter(adapter);
    }
}