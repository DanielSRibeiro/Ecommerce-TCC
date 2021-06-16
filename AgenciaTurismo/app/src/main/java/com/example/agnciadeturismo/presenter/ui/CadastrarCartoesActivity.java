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
import android.widget.EditText;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.viewmodel.CartaoViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastrarCartoesActivity extends AppCompatActivity {

    Button buttonCadastrar;
    EditText editTextNomeCartao, editTextNumero, editTextNomeImpresso, editTextData, editTextCVV;
    Toolbar toolbar;
    CartaoViewModel cartaoViewModel;
    private static final String TAG = "CadastrarCartoesActivit";
    ClienteDto cliente = new ClienteDto(null, null, null, null, null, null, null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartoes);

        initView();
        initObserve();
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeCartao = editTextNomeCartao.getText().toString();
                String numero = editTextNumero.getText().toString();
                String nomeImpresso = editTextNomeImpresso.getText().toString();
                String cvv = editTextCVV.getText().toString();

                SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
                String data = in.format(new Date(editTextData.getText().toString()));

                cliente = MainActivity.getUsuario();
                if(cliente.getCpf() != null){
                    cartaoViewModel.cadastrarCartao(cliente.getCpf(), nomeCartao, nomeImpresso, numero, cvv, data);
                }else{
                    Log.d(TAG, "CPF inválido");
                }
            }
        });
    }

    private void initView() {
        cartaoViewModel = new ViewModelProvider(this).get(CartaoViewModel.class);
        buttonCadastrar = findViewById(R.id.btn_cadastrarCartoes);
        editTextNomeCartao = findViewById(R.id.edt_nomeCartao);
        editTextNumero = findViewById(R.id.edt_numeroCartao);
        editTextNomeImpresso = findViewById(R.id.edt_nomeImpresso);
        editTextData = findViewById(R.id.edt_dataValidade);
        editTextCVV = findViewById(R.id.edt_cvv);
        toolbar = findViewById(R.id.toolbar_cadastrar_cartoes);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initObserve() {
        cartaoViewModel.cadastrar.observe(CadastrarCartoesActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean resultado) {
                if(resultado){
                    Toast.makeText(CadastrarCartoesActivity.this, "Cadastrado com sucesso!!", Toast.LENGTH_SHORT).show();
                    mudarTela();
                }else{
                    Toast.makeText(CadastrarCartoesActivity.this, "O formato da data esta errado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void mudarTela() {
        Intent intent = new Intent(CadastrarCartoesActivity.this, DashboardActivity.class);
        intent.putExtra("activity", "Cartão");
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        mudarTela();
        return super.onSupportNavigateUp();
    }
}