package com.example.agnciadeturismo.presenter.ui;

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
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.presenter.adapter.CarrinhoAdapter;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemCarrinho;

public class CarrinhoFragment extends Fragment implements OnClickItemCarrinho {

    RecyclerView recyclerViewCarrinho;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carrinho, container, false);
        initView(view);
        atualizaAdapter();

        return view;
    }

    private void initView(View view) {
        recyclerViewCarrinho = view.findViewById(R.id.recycler_carrinho);
    }

    private void atualizaAdapter() {
        CarrinhoAdapter adapter = new CarrinhoAdapter(this);
        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCarrinho.setAdapter(adapter);
    }

    private void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickComprar(int posicao) {
        ClienteDto cliente  = ((MainActivity) getActivity()).getUsuario();
        if(cliente.getCpf() != null){
            toast("Selecionar Cartão para realizar a compra");
            Intent intent = new Intent(getActivity(), DashboardActivity.class);
            intent.putExtra("activity", "Cartão");
            startActivity(intent);
        }else{
            toast("É necessário realizar o login para poder comprar");
        }
    }

    @Override
    public void onClickRemover(int posicao) {
        toast("Remover");
        atualizaAdapter();
    }
}