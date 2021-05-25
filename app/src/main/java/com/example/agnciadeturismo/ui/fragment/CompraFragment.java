package com.example.agnciadeturismo.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.ui.DashboardActivity;

public class CompraFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compra, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        ((DashboardActivity) getActivity()).setTitulo("Compras","Compras do Usuário");
    }
}