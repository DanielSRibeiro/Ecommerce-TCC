package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.presenter.adapter.PacoteAdapter;
import com.example.agnciadeturismo.presenter.adapter.OnClickItemPacote;

public class BuscarActivity extends AppCompatActivity implements OnClickItemPacote {

    Toolbar toolbar;
    RecyclerView recyclerViewPacote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        initView();
        atualizaAdapter();

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_buscar);
        recyclerViewPacote = findViewById(R.id.recycler_pacote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void atualizaAdapter() {
        PacoteAdapter adapter = new PacoteAdapter(this);
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
        Toast.makeText(this, "Detalhes", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(BuscarActivity.this, DetalhesActivity.class);
        startActivity(intent);
    }
}