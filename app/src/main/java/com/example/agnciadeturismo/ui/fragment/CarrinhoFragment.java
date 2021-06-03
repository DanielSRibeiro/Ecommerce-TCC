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
import com.example.agnciadeturismo.ui.activities.DashboardActivity;
import com.example.agnciadeturismo.ui.adapter.CarrinhoAdapter;
import com.example.agnciadeturismo.ui.adapter.OnClickItemCarrinho;

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

    @Override
    public void onClickComprar(int posicao) {
        Toast.makeText(getActivity(), "Compra realizada com sucesso!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        intent.putExtra("nome", "Compras");
        startActivity(intent);

    }

    @Override
    public void onClickRemover(int posicao) {
        Toast.makeText(getActivity(), "Remover", Toast.LENGTH_SHORT).show();
    }
}