package com.example.agnciadeturismo.presenter.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.data.repository.ItensReservaRepositoryTask;
import com.example.agnciadeturismo.model.ItensReservaDto;
import com.example.agnciadeturismo.presenter.view.adapter.ItensAdapter;
import com.example.agnciadeturismo.presenter.viewmodel.ItensReservaViewModel;
import com.example.agnciadeturismo.services.UsuarioServices;

import java.util.ArrayList;

public class ItensReservaActivity extends AppCompatActivity {

    String cpf;
    int codigoReserva;
    RecyclerView recyclerViewItens;
    ItensReservaViewModel itemReservaViewModel;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_reserva);

        initView();
        itemReservaViewModel.getItensReserva().observe(this, new Observer<ArrayList<ItensReservaDto>>() {
            @Override
            public void onChanged(ArrayList<ItensReservaDto> response) {
                atualizaAdapter(response);
            }
        });
    }


    private void initView() {
        itemReservaViewModel = new ViewModelProvider(this, new ItensReservaViewModel.ViewModelFactory(new ItensReservaRepositoryTask())).get(ItensReservaViewModel.class);
        recyclerViewItens = findViewById(R.id.recycler_itens);
        toolbar = findViewById(R.id.toolbar_itens);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cpf = UsuarioServices.getUsuario().getCpf();
        Bundle bundle = getIntent().getExtras();
        codigoReserva = bundle.getInt("cdReserva");

        itemReservaViewModel.getAllItemReserva(cpf, codigoReserva);
    }

    private void atualizaAdapter(ArrayList<ItensReservaDto> listItens) {
        ItensAdapter adapter = new ItensAdapter(listItens);
        recyclerViewItens.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItens.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}