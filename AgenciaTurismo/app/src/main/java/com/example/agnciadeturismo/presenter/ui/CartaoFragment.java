package com.example.agnciadeturismo.presenter.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.CartaoDto;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.presenter.ui.CadastrarCartoesActivity;
import com.example.agnciadeturismo.presenter.ui.DashboardActivity;
import com.example.agnciadeturismo.presenter.adapter.CartaoAdapter;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemCarrinho;
import com.example.agnciadeturismo.viewmodel.ViewModelCartao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CartaoFragment extends Fragment implements OnClickItemCarrinho {

    FloatingActionButton fabCadastrar;
    RecyclerView recyclerViewCartao;
    String cpf = "";
    ViewModelCartao viewModelCartao;
    ArrayList<CartaoDto> listCartao = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cartao, container, false);
        initView(view);
        initObserver();

        fabCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CadastrarCartoesActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initObserver() {
        viewModelCartao.cartao.observe(getActivity(), new Observer<ArrayList<CartaoDto>>() {
            @Override
            public void onChanged(ArrayList<CartaoDto> list) {
                listCartao = list;
                atualizaAdapter(list);
            }
        });

        viewModelCartao.excluir.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean resultado) {
                if(resultado == true){
                    Toast.makeText(getActivity(), "Excluido com Sucesso!!", Toast.LENGTH_SHORT).show();
                    viewModelCartao.init(cpf);
                }
            }
        });
    }

    private void initView(View view) {
        viewModelCartao = new ViewModelProvider(getActivity()).get(ViewModelCartao.class);
        fabCadastrar = view.findViewById(R.id.fab_cadastrar);
        recyclerViewCartao = view.findViewById(R.id.recycler_cartao);
        ((DashboardActivity) getActivity()).setTitulo("Cartão", "Selecionar o Cartão para efetuar a compra");

        Bundle bundle =  getActivity().getIntent().getExtras();
        if(bundle != null){
           cpf = bundle.getString("cpf");
           viewModelCartao.init(cpf);
        }
    }

    private void atualizaAdapter(ArrayList<CartaoDto> listCartao) {
        CartaoAdapter adapter = new CartaoAdapter(this, listCartao);
        recyclerViewCartao.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCartao.setAdapter(adapter);
    }

    @Override
    public void onClickComprar(int posicao) {
        Toast.makeText(getActivity(), "Compra realizada com sucesso!!!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        intent.putExtra("activity", "Compras");
        intent.putExtra("cpf", cpf);
        startActivity(intent);
    }

    @Override
    public void onClickRemover(int codigo) {
        AlertDialog.Builder msg = new AlertDialog.Builder(getActivity());
        msg.setMessage("Realmente deseja excluir ?");
        msg.setNegativeButton("Não", null);
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModelCartao.deletarCartao(cpf, codigo);
            }
        });
        msg.show();
    }
}