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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.PacoteDto;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemPacote;
import com.example.agnciadeturismo.presenter.adapter.OfertaAdapter;
import com.example.agnciadeturismo.viewmodel.CidadeViewModel;
import com.example.agnciadeturismo.viewmodel.PacoteViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnClickItemPacote {

    private static final String TAG = "HomeFragment";
    AutoCompleteTextView autoCompleteTextViewTransporte, autoCompleteTextViewOrigem, autoCompleteTextViewDestino;
    RecyclerView recyclerViewOferta;
    Button buttonPesquisar;
    ArrayList<PacoteDto> listPacote = new ArrayList<>();
    PacoteViewModel pacoteViewModel;
    CidadeViewModel cidadeViewModel;
    int cdOrigem = -1, cdDestino = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initObserver();

        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cidadeViewModel.getCodigoCidade(autoCompleteTextViewOrigem.getText().toString(), autoCompleteTextViewDestino.getText().toString());
            }
        });

        return view;
    }

    private void initObserver() {
        pacoteViewModel.oferta.observe(getActivity(), new Observer<ArrayList<PacoteDto>>() {
            @Override
            public void onChanged(ArrayList<PacoteDto> response) {
                listPacote = response;
                atualizaAdapter(listPacote);
            }
        });

        cidadeViewModel.todasCidades.observe(getActivity(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] listCidades) {
                ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, listCidades);
                autoCompleteTextViewOrigem.setAdapter(adapter);
                autoCompleteTextViewDestino.setAdapter(adapter);
                Log.d(TAG, "Sucesso");
            }
        });

        cidadeViewModel.codigoOrigem.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer codigoOrigem) {
                if(codigoOrigem != null){
                    cdOrigem = codigoOrigem;
                    buscarPacote();
                }else{
                    cdOrigem = -1;
                }
            }
        });

        cidadeViewModel.codigoDestino.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer codigoDestino) {
                if(codigoDestino != null){
                    cdDestino = codigoDestino;
                    buscarPacote();
                }else{
                    cdDestino = -1;
                }
            }
        });
    }

    private void buscarPacote() {
        if (cdOrigem != -1 && cdDestino != -1) {
            int transporte = -1;
            if(autoCompleteTextViewTransporte.getText().toString().equals("Avião")){
                transporte = 1;
            }else if(autoCompleteTextViewTransporte.getText().toString().equals("Cruzeiro")){
                transporte = 2;
            }else if(autoCompleteTextViewTransporte.getText().toString().equals("Ônibus")){
                transporte = 3;
            }
            Intent intent = new Intent(getActivity(), BuscarActivity.class);
            intent.putExtra("tipo", transporte);
            intent.putExtra("cdOrigem", cdOrigem);
            intent.putExtra("cdDestino", cdDestino);
            intent.putExtra("destino", autoCompleteTextViewDestino.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] tipo_transporte = getResources().getStringArray(R.array.tipo_transporte);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, tipo_transporte);
        autoCompleteTextViewTransporte.setAdapter(adapter);

    }

    private void initView(View view) {
        pacoteViewModel = new ViewModelProvider(this).get(PacoteViewModel.class);
        cidadeViewModel = new ViewModelProvider(this).get(CidadeViewModel.class);
        autoCompleteTextViewTransporte = view.findViewById(R.id.autoCompleteTextView_transporte);
        autoCompleteTextViewOrigem = view.findViewById(R.id.autoCompleteTextView_origem);
        autoCompleteTextViewDestino = view.findViewById(R.id.autoCompleteTextView_destino);
        recyclerViewOferta = view.findViewById(R.id.recycler_oferta);
        buttonPesquisar = view.findViewById(R.id.btn_pesquisarPacote);

        pacoteViewModel.getAllPacotesOferta();
        cidadeViewModel.getAllCidades();
    }

    private void atualizaAdapter(ArrayList<PacoteDto> listPacote) {
        OfertaAdapter adapter = new OfertaAdapter(this, listPacote);
        recyclerViewOferta.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewOferta.setAdapter(adapter);
    }

    @Override
    public void onClick(int posicao) {
        PacoteDto pacote = listPacote.get(posicao);
        Intent intent = new Intent(getActivity(), DetalhesActivity.class);
        intent.putExtra("codigo", pacote.getCd());
        intent.putExtra("viagem", pacote.getCdViagem());
        intent.putExtra("hotel", pacote.getCdHotel());
        intent.putExtra("categoria", pacote.getCdCategoria());
        intent.putExtra("tipoTransporte", pacote.getCdTipoTranporte());
        intent.putExtra("origem", pacote.getCdOrigem());
        intent.putExtra("destino", pacote.getCdDestino());
        intent.putExtra("nomePacote", pacote.getNomePacote());
        intent.putExtra("descricao", pacote.getDescricaoPacote());
        intent.putExtra("checkin", pacote.getDtCheckin());
        intent.putExtra("checkout", pacote.getDtCheckout());
        intent.putExtra("img", pacote.getImg());
        intent.putExtra("valor", pacote.getVlPacote());
        startActivity(intent);
    }
}