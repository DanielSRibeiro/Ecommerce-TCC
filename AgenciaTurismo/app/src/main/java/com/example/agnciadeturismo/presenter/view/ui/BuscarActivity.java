package com.example.agnciadeturismo.presenter.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.data.repository.PacoteRepositoryTask;
import com.example.agnciadeturismo.model.PacoteDto;
import com.example.agnciadeturismo.presenter.view.adapter.PacoteAdapter;
import com.example.agnciadeturismo.presenter.view.adapter.OnClickItemPacote;
import com.example.agnciadeturismo.presenter.viewmodel.PacoteViewModel;

import java.util.ArrayList;

public class BuscarActivity extends AppCompatActivity implements OnClickItemPacote {

    Toolbar toolbar;
    RecyclerView recyclerViewPacote;
    PacoteViewModel pacoteViewModel;
    ArrayList<PacoteDto> listPacote = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        initView();
        initObserver();
    }

    private void initObserver() {
        pacoteViewModel.getPacote().observe(this, new Observer<ArrayList<PacoteDto>>() {
            @Override
            public void onChanged(ArrayList<PacoteDto> response) {
                listPacote = response;
                atualizaAdapter(listPacote);
            }
        });
    }

    private void initView() {
        pacoteViewModel = new ViewModelProvider(this, new PacoteViewModel.ViewModelFactory(new PacoteRepositoryTask())).get(PacoteViewModel.class);
        toolbar = findViewById(R.id.toolbar_buscar);
        recyclerViewPacote = findViewById(R.id.recycler_pacote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            getSupportActionBar().setTitle("Pacotes para "+bundle.getString("destino"));
            pacoteViewModel.getAllPacotes(
                    bundle.getInt("cdOrigem"),
                    bundle.getInt("cdDestino"),
                    bundle.getInt("tipo")
            );
        }
    }

    private void atualizaAdapter(ArrayList<PacoteDto> listPacote) {
        PacoteAdapter adapter = new PacoteAdapter(this, listPacote);
        recyclerViewPacote.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPacote.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(int posicao) {
        PacoteDto pacote = listPacote.get(posicao);
        Intent intent = new Intent(this, DetalhesActivity.class);
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