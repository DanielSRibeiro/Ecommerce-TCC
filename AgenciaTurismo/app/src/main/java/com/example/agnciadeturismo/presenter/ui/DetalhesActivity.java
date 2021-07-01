package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.model.CarrinhoDto;
import com.example.agnciadeturismo.viewmodel.CidadeViewModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalhesActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonComprar;
    TextView textViewNomePacote, textViewDescricao, textViewOrigem, textViewDestino, textViewDiaria;
    TextView textViewIdaVolta, textViewDataChegada, textViewDataSaida, textViewValor;
    ImageView ImageViewPacote;
    int codigo, codigoTransporte, categoria, hotel, viagem, destino, origem;
    String valor, img, nomePacote, nomeOrigem, nomeDestino;
    private static final String TAG = "DetalhesActivity";
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
                MainActivity.setListCarrinho(carrinho);
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
        if(bundle != null){
            codigo = bundle.getInt("codigo");
            codigoTransporte = bundle.getInt("tipoTransporte");
            categoria = bundle.getInt("categoria");
            viagem = bundle.getInt("viagem");
            hotel = bundle.getInt("hotel");
            String checkin = bundle.getString("checkin");
            String checkout = bundle.getString("checkout");
            nomePacote = bundle.getString("nomePacote");
            img = bundle.getString("img");
            valor = bundle.getString("valor");
            origem = bundle.getInt("origem");
            destino = bundle.getInt("destino");
            viewModel.getNomeCidade(origem, destino);

            String ano = checkin.substring(0,4);
            String mes = checkin.substring(5,7);
            String dia = checkin.substring(8,10);
            String horas = checkin.substring(11,19);
            String novoCheckin = dia+"/"+mes+"/"+ano+" às "+horas;

            String cAno = checkout.substring(0,4);
            String cMes = checkout.substring(5,7);
            String cDia = checkout.substring(8,10);
            String cHoras = checkout.substring(11,19);
            String novoCheckout = cDia+"/"+cMes+"/"+cAno+" às "+cHoras;

            textViewNomePacote.setText(nomePacote);
            textViewDescricao.setText(bundle.getString("descricao"));
            textViewDataChegada.setText("Data de chegada: "+novoCheckin);
            textViewDataSaida.setText("Data de saída: "+novoCheckout);
            Picasso.get().load("http://192.168.0.106/"+img).into(ImageViewPacote);
            textViewValor.setText("R$"+valor);
        }
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}