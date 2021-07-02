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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.data.repository.CartaoRepositoryTaskK;
import com.example.agnciadeturismo.model.CartaoDto;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.presenter.adapter.CartaoAdapter;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemCartao;
import com.example.agnciadeturismo.services.CarrinhoServices;
import com.example.agnciadeturismo.services.UsuarioServices;
import com.example.agnciadeturismo.viewmodel.CartaoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CartaoFragment extends Fragment implements OnClickItemCartao {

    private static final String TAG = "CartaoFragment";
    FloatingActionButton fabCadastrar;
    RecyclerView recyclerViewCartao;
    String cpf = "";
    CartaoViewModel cartaoViewModel;
    CartaoRepositoryTaskK repositoryTask = new CartaoRepositoryTaskK();
    ArrayList<CartaoDto> listCartao = new ArrayList<>();
    ClienteDto cliente = new ClienteDto();

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

    private void initView(View view) {
        cartaoViewModel = new ViewModelProvider(getActivity(), new CartaoViewModel.ViewModelFactory(repositoryTask)).get(CartaoViewModel.class);
        fabCadastrar = view.findViewById(R.id.fab_cadastrar);
        recyclerViewCartao = view.findViewById(R.id.recycler_cartao);
        ((DashboardActivity) getActivity()).setTitulo("Cartão", "Selecionar o Cartão para efetuar a compra");

        cliente = UsuarioServices.getUsuario();
        cpf = cliente.getCpf();
        cartaoViewModel.init(cpf);
    }

    private void initObserver() {
        cartaoViewModel.getCartao().observe(getActivity(), new Observer<ArrayList<CartaoDto>>() {
            @Override
            public void onChanged(ArrayList<CartaoDto> list) {
                listCartao = list;
                atualizaAdapter(list);
            }
        });

        cartaoViewModel.getExcluir().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean resultado) {
                if(resultado){
                    Toast.makeText(getActivity(), "Excluido com Sucesso!!", Toast.LENGTH_SHORT).show();
                    cartaoViewModel.init(cpf);
                }
            }
        });
    }

    private void atualizaAdapter(ArrayList<CartaoDto> listCartao) {
        CartaoAdapter adapter = new CartaoAdapter(this, listCartao);
        recyclerViewCartao.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCartao.setAdapter(adapter);
    }

    @Override
    public void onClickComprar(int codigo) {
        if(CarrinhoServices.getListCarrinho().size() < 1){
            Toast.makeText(getActivity(), "O Carrinho está vazio", Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Clica em confirmar para efetuar a compra");
            builder.setNegativeButton("Não", null);
            builder.setPositiveButton("Comfirmar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Compra realizada com sucesso!!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    intent.putExtra("activity", "Compras");
                    intent.putExtra("cdCartao", codigo);
                    startActivity(intent);
                }
            });
            builder.show();
        }
    }

    @Override
    public void onClickRemover(int codigo) {
        AlertDialog.Builder msg = new AlertDialog.Builder(getActivity());
        msg.setMessage("Realmente deseja excluir ?");
        msg.setNegativeButton("Não", null);
        msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cartaoViewModel.deletarCartao(cpf, codigo);
            }
        });
        msg.show();
    }
}