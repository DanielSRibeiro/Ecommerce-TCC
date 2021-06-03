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
import com.example.agnciadeturismo.ui.activities.BuscarActivity;
import com.example.agnciadeturismo.ui.activities.DetalhesActivity;
import com.example.agnciadeturismo.ui.adapter.OnClickItemPacote;
import com.example.agnciadeturismo.ui.adapter.OfertaAdapter;

public class HomeFragment extends Fragment implements OnClickItemPacote {

    AutoCompleteTextView autoCompleteTextViewTransporte;
    RecyclerView recyclerViewOferta;
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
        OfertaAdapter adapter = new OfertaAdapter(this);
        recyclerViewOferta.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewOferta.setAdapter(adapter);
    }

    private void initView(View view) {
        autoCompleteTextViewTransporte = view.findViewById(R.id.autoCompleteTextView_transporte);
        recyclerViewOferta = view.findViewById(R.id.recycler_oferta);
        buttonPesquisar = view.findViewById(R.id.btn_pesquisarPacote);
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] tipo_transporte = getResources().getStringArray(R.array.tipo_transporte);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, tipo_transporte);
        autoCompleteTextViewTransporte.setAdapter(adapter);
    }

    @Override
    public void onClick(int posicao) {
        Intent intent = new Intent(getActivity(), DetalhesActivity.class);
        startActivity(intent);
    }
}