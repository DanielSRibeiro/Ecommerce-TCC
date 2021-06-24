package com.example.agnciadeturismo.presenter.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.CarrinhoDto;
import com.example.agnciadeturismo.presenter.adapter.CarrinhoAdapter;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemCarrinho;

import java.util.ArrayList;

public class CarrinhoFragment extends Fragment implements OnClickItemCarrinho {

    RecyclerView recyclerViewCarrinho;
    ArrayList<CarrinhoDto> listCarrinho;
    Button buttonCarrinho;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);
        initView(view);
        buttonCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                intent.putExtra("activity", "Cartão");
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView(View view) {
        recyclerViewCarrinho = view.findViewById(R.id.recycler_carrinho);
        buttonCarrinho = view.findViewById(R.id.btn_comprarCarrinho);
        listCarrinho = MainActivity.getListCarrinho();

        if(listCarrinho.size() < 1 ){
            buttonCarrinho.setVisibility(View.INVISIBLE);
        }else{
            buttonCarrinho.setVisibility(View.VISIBLE);
        }

        atualizaAdapter(listCarrinho);
    }

    private void atualizaAdapter(ArrayList<CarrinhoDto> listCarrinho) {
        CarrinhoAdapter adapter = new CarrinhoAdapter(this, listCarrinho);
        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCarrinho.setAdapter(adapter);
    }

    @Override
    public void onClickRemover(int posicao) {
        listCarrinho.remove(posicao);
        atualizaAdapter(listCarrinho);

        if(listCarrinho.size() < 1 ){
            buttonCarrinho.setVisibility(View.INVISIBLE);
        }
    }
}