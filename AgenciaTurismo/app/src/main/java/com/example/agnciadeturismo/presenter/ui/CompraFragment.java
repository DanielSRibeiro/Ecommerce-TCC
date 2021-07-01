package com.example.agnciadeturismo.presenter.ui;

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

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.CarrinhoDto;
import com.example.agnciadeturismo.model.ReservaDto;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemCompra;
import com.example.agnciadeturismo.presenter.adapter.CompraAdapter;
import com.example.agnciadeturismo.services.CarrinhoServices;
import com.example.agnciadeturismo.services.UsuarioServices;
import com.example.agnciadeturismo.viewmodel.ItemReservaViewModel;
import com.example.agnciadeturismo.viewmodel.ReservaViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CompraFragment extends Fragment implements OnClickItemCompra {

    private static final String TAG = "CompraFragment";
    RecyclerView recyclerViewCompra;
    double valorTotal;
    String cpf, data;
    int codigoCartao;
    ReservaViewModel reservaViewModel;
    ItemReservaViewModel itemReservaViewModel;
    ArrayList<ReservaDto> listReserva = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compra, container, false);
        initView(view);
        initObserve();

        return view;
    }

    private void initObserve() {
        reservaViewModel.cadastrado.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean cadastrado) {
                if(cadastrado){
                    Log.d(TAG, "Reserva cadastrada com sucesso");
                    reservaViewModel.getCodigoReserva(codigoCartao, cpf, valorTotal, 1, data);
                }else{
                    Log.d(TAG, "Erro na Reserva");
                }
            }
        });

        reservaViewModel.codigoReserva.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer codigoReserva) {
                if(codigoReserva != -1){
                    reservaViewModel.getAllReserva(cpf);
                    ArrayList<CarrinhoDto> listPacote = CarrinhoServices.getListCarrinho();
                    for(CarrinhoDto carinho : listPacote){
                        valorTotal += carinho.getValor();
                        int pacote = carinho.getCdPacote();
                        int quantidade = carinho.getQuantidade();
                        double unitario = carinho.getValorUnitario();
                        itemReservaViewModel.cadastrarItemReserva(pacote, cpf, codigoReserva, quantidade, unitario);
                    }
                    CarrinhoServices.clearListCarrinho();
                }else{
                    Log.d(TAG, "Erro no codigoReserva:"+codigoReserva);
                }
            }
        });

        itemReservaViewModel.cadastrado.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean cadastradoItem) {
                if(cadastradoItem){
                    Log.d(TAG, "Sucesso ao cadastro");
                }else{
                    Log.d(TAG, "Erro");
                }
            }
        });

        reservaViewModel.reservas.observe(getActivity(), new Observer<ArrayList<ReservaDto>>() {
            @Override
            public void onChanged(ArrayList<ReservaDto> response) {
                listReserva = response;
                atualizaAdapter(listReserva);
            }
        });
    }

    private void initView(View view) {
        reservaViewModel = new ViewModelProvider(getActivity()).get(ReservaViewModel.class);
        itemReservaViewModel = new ViewModelProvider(getActivity()).get(ItemReservaViewModel.class);
        recyclerViewCompra = view.findViewById(R.id.recycler_compra);
        ((DashboardActivity) getActivity()).setTitulo("Reservas","Reservas compradas");
        cpf = UsuarioServices.getUsuario().getCpf();

        Bundle bundle = getActivity().getIntent().getExtras();
        if(bundle != null){
            if(CarrinhoServices.getListCarrinho().size() > 0){
                codigoCartao = bundle.getInt("cdCartao");
                ArrayList<CarrinhoDto> listPacote = CarrinhoServices.getListCarrinho();
                for(CarrinhoDto carinho : listPacote){
                    valorTotal += carinho.getValor();
                }
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                data = dateFormat.format(date);

                reservaViewModel.cadastrarReserva(codigoCartao,cpf, valorTotal, 1, data);
            }
        }

        reservaViewModel.getAllReserva(cpf);
    }

    private void atualizaAdapter(ArrayList<ReservaDto> listReserva) {
        CompraAdapter adapter = new CompraAdapter(this, listReserva);
        recyclerViewCompra.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewCompra.setAdapter(adapter);
    }

    @Override
    public void onClick(int posicao) {
        ReservaDto reserva = listReserva.get(posicao);
        Intent intent = new Intent(getActivity(), ItensReservaActivity.class);
        intent.putExtra("cdReserva", reserva.getCd());
        startActivity(intent);
    }
}