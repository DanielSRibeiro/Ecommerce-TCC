package com.example.agnciadeturismo.presenter.ui;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.PacoteDto;
import com.example.agnciadeturismo.presenter.ui.BuscarActivity;
import com.example.agnciadeturismo.presenter.ui.DetalhesActivity;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemPacote;
import com.example.agnciadeturismo.presenter.adapter.OfertaAdapter;
import com.example.agnciadeturismo.viewmodel.PacoteViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnClickItemPacote {

    AutoCompleteTextView autoCompleteTextViewTransporte;
    RecyclerView recyclerViewOferta;
    Button buttonPesquisar;
    ArrayList<PacoteDto> listPacote = new ArrayList<>();
    PacoteViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initObserver();

        buttonPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BuscarActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initObserver() {
        viewModel.oferta.observe(getActivity(), new Observer<ArrayList<PacoteDto>>() {
            @Override
            public void onChanged(ArrayList<PacoteDto> response) {
                listPacote = response;
                atualizaAdapter(listPacote);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] tipo_transporte = getResources().getStringArray(R.array.tipo_transporte);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.dropdown_item, tipo_transporte);
        autoCompleteTextViewTransporte.setAdapter(adapter);
    }

    private void initView(View view) {
        viewModel = new ViewModelProvider(this).get(PacoteViewModel.class);
        autoCompleteTextViewTransporte = view.findViewById(R.id.autoCompleteTextView_transporte);
        recyclerViewOferta = view.findViewById(R.id.recycler_oferta);
        buttonPesquisar = view.findViewById(R.id.btn_pesquisarPacote);

        viewModel.getAllPacotesOferta();
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
        intent.putExtra("vaigem", pacote.getCdViagem());
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