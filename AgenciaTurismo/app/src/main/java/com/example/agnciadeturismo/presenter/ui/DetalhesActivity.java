package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalhesActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonComprar;
    TextView textViewNomePacote, textViewDescricao, textViewOrigem, textViewDestino, textViewDiaria;
    TextView textViewIdaVolta, textViewDataChegada, textViewDataSaida, textViewValor;
    ImageView ImageViewPacote;
    int codigo, tipoTransporte, categoria;
    private static final String TAG = "DetalhesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        initView();
        buttonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalhesActivity.this, "Adicionado no carrinho", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetalhesActivity.this, MainActivity.class);
                intent.putExtra("activity", "carrinho");
                startActivity(intent);
            }
        });

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_detalhes);
        textViewNomePacote = findViewById(R.id.txt_nomeDetalhes);
        textViewDescricao = findViewById(R.id.txt_descricaoDetalhes);
        textViewOrigem = findViewById(R.id.txt_origemDetalhes);
        textViewDestino = findViewById(R.id.txt_destinoDetalhes);
        textViewDiaria = findViewById(R.id.txt_diariasDetalhes);
        textViewDataChegada = findViewById(R.id.txt_dataChegada);
        textViewDataSaida = findViewById(R.id.txt_dataSaida);
        textViewValor = findViewById(R.id.txt_valorDetalhes);
        textViewIdaVolta = findViewById(R.id.txt_idaVolta);
        buttonComprar = findViewById(R.id.btn_comprarPacote);
        ImageViewPacote = findViewById(R.id.img_detalhes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            codigo = bundle.getInt("codigo");
            tipoTransporte = bundle.getInt("tipoTransporte");
            categoria = bundle.getInt("categoria");
            codigo = bundle.getInt("codigo");
            textViewOrigem.setText("Origem: "+bundle.getString("origem"));
            textViewDestino.setText("Destino: "+bundle.getString("destino"));
            textViewNomePacote.setText(bundle.getString("nomePacote"));
            textViewDescricao.setText(bundle.getString("descricao"));

//            textViewDiaria.setText("diária:"+bundle.getString(""));
//            SimpleDateFormat in = new SimpleDateFormat("dd-MM-yyyy HH:MM:SS");
//            String data = bundle.getString("checkin");
//            String dataChegada = in.format(new Date(data));
//            Log.d(TAG, data);
//            Log.d(TAG, dataChegada);

            textViewDataChegada.setText("Data de chegada: "+bundle.getString("checkin"));
            textViewDataSaida.setText("Data de saída: "+bundle.getString("checkout"));
            Picasso.get().load("http://192.168.0.106/"+bundle.getString("img")).into(ImageViewPacote);
            textViewValor.setText("R$"+bundle.getString("valor"));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}