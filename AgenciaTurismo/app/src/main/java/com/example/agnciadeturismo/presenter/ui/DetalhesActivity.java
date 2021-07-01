package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.data.api.RetrofitTask;
import com.example.agnciadeturismo.model.CarrinhoDto;
import com.example.agnciadeturismo.services.CarrinhoServices;
import com.example.agnciadeturismo.services.UsuarioServices;
import com.example.agnciadeturismo.viewmodel.CidadeViewModel;
import com.squareup.picasso.Picasso;

public class DetalhesActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonComprar;
    TextView textViewNomePacote, textViewDescricao, textViewOrigem, textViewDestino, textViewDiaria;
    TextView textViewIdaVolta, textViewDataChegada, textViewDataSaida, textViewValor;
    ImageView ImageViewPacote;
    int codigo, codigoTransporte, categoria, hotel, viagem, destino, origem;
    String valor, img, nomePacote, nomeOrigem, nomeDestino;
    CidadeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        initView();
        initObserver();
        buttonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarrinhoDto carrinho = new CarrinhoDto(
                        -1, -1, codigo, "-1",
                        Double.parseDouble(valor), Double.parseDouble(valor), 1, img, nomeOrigem+" para "+nomeDestino, nomePacote, codigoTransporte
                );
                CarrinhoServices.setListCarrinho(carrinho);
                Toast.makeText(DetalhesActivity.this, "Adicionado no carrinho", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetalhesActivity.this, MainActivity.class);
                intent.putExtra("activity", "carrinho");
                startActivity(intent);
            }
        });
    }
    private void initView() {
        viewModel = new ViewModelProvider(this).get(CidadeViewModel.class);
        toolbar = findViewById(R.id.toolbar_detalhes);
        textViewNomePacote = findViewById(R.id.txt_nomeDetalhes);
        textViewDescricao = findViewById(R.id.txt_descricaoDetalhes);
        textViewOrigem = findViewById(R.id.txt_origemDetalhes);
        textViewDestino = findViewById(R.id.txt_destinoDetalhes);
        textViewDataChegada = findViewById(R.id.txt_dataChegada);
        textViewDataSaida = findViewById(R.id.txt_dataSaida);
        textViewValor = findViewById(R.id.txt_valorDetalhes);
        textViewIdaVolta = findViewById(R.id.txt_idaVolta);
        buttonComprar = findViewById(R.id.btn_comprarPacote);
        ImageViewPacote = findViewById(R.id.img_detalhes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        codigo = bundle.getInt("codigo");
        codigoTransporte = bundle.getInt("tipoTransporte");
        categoria = bundle.getInt("categoria");
        viagem = bundle.getInt("viagem");
        hotel = bundle.getInt("hotel");
        nomePacote = bundle.getString("nomePacote");
        img = bundle.getString("img");
        valor = bundle.getString("valor");
        origem = bundle.getInt("origem");
        destino = bundle.getInt("destino");
        viewModel.getNomeCidade(origem, destino);

        textViewNomePacote.setText(nomePacote);
        textViewDescricao.setText(bundle.getString("descricao"));
        textViewDataChegada.setText("Data de chegada: "+transformarData(bundle.getString("checkin")));
        textViewDataSaida.setText("Data de saída: "+transformarData(bundle.getString("checkout")));
        textViewValor.setText("R$"+valor);
        Picasso.get().load("http://"+RetrofitTask.IP+"/"+img).into(ImageViewPacote);
    }

    private void initObserver() {
        viewModel.nomeOrigem.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String response) {
                nomeOrigem = response;
                textViewOrigem.setText("Origem: "+nomeOrigem);
            }
        });
        viewModel.nomeDestino.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String response) {
                nomeDestino = response;
                textViewDestino.setText("Destino: "+nomeDestino);
            }
        });
    }

    private String transformarData(String dataBanco){
        String ano = dataBanco.substring(0,4);
        String mes = dataBanco.substring(5,7);
        String dia = dataBanco.substring(8,10);
        String horas = dataBanco.substring(11,19);
        return dia+"/"+mes+"/"+ano+" às "+horas;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}