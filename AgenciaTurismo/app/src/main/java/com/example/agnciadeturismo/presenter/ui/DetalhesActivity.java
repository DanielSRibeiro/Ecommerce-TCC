package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetalhesActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonComprar;
    TextView textViewNomePacote, textViewDescricao, textViewOrigem, textViewDestino, textViewDiaria;
    TextView textViewIdaVolta, textViewDataChegada, textViewDataSaida, textViewValor;
    ImageView ImageViewPacote;
    int codigo, codigoTransporte, categoria, hotel, viagem, destino, origem;
    String valor, img, nomePacote;
    private static final String TAG = "DetalhesActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        initView();
        buttonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClienteDto cliente  = MainActivity.getUsuario();
                if(cliente.getCpf() != null){
                    CarrinhoDto carrinho = new CarrinhoDto(
                            -1, -1, codigo, cliente.getCpf(),
                            Double.parseDouble(valor), Double.parseDouble(valor), 1, img, "", nomePacote, codigoTransporte
                    );
                    MainActivity.setListCarrinho(carrinho);
                    Toast.makeText(DetalhesActivity.this, "Adicionado no carrinho", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetalhesActivity.this, MainActivity.class);
                    intent.putExtra("activity", "carrinho");
                    startActivity(intent);
                }else{
                    Toast.makeText(DetalhesActivity.this, "É necessário realizar o login para poder comprar", Toast.LENGTH_SHORT).show();
                }
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

//            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//            String data = dateFormat.format(checkin);

            Log.d(TAG, "Data: "+checkin);

            textViewOrigem.setText("Origem: "+origem);
            textViewDestino.setText("Destino: "+destino);
            textViewNomePacote.setText(nomePacote);
            textViewDescricao.setText(bundle.getString("descricao"));
            textViewDataChegada.setText("Data de chegada: "+checkin);
            textViewDataSaida.setText("Data de saída: "+checkout);
            Picasso.get().load("http://192.168.0.106/"+img).into(ImageViewPacote);
            textViewValor.setText("R$"+valor);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}