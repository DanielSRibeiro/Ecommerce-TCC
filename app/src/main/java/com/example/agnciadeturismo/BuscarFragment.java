package com.example.agnciadeturismo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.security.Permission;

public class BuscarFragment extends Fragment {

    AutoCompleteTextView autoCompleteTextViewTransporte;
    RecyclerView recyclerViewRecentemente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        autoCompleteTextViewTransporte = view.findViewById(R.id.autoCompleteTextView_transporte);
        recyclerViewRecentemente = view.findViewById(R.id.recycler_recentemente);

        RecentementeAdapter adapter = new RecentementeAdapter();
//        recyclerViewRecentemente.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewRecentemente.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] tipo_transporte = getResources().getStringArray(R.array.tipo_transporte);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, tipo_transporte);
        autoCompleteTextViewTransporte.setAdapter(adapter);
    }
}