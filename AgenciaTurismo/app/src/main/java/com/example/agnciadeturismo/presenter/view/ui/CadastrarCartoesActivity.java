package com.example.agnciadeturismo.presenter.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agnciadeturismo.data.repository.CartaoRepositoryTask;
import com.example.agnciadeturismo.presenter.view.services.MascaraServices;
import com.example.agnciadeturismo.presenter.view.services.UsuarioServices;
import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.presenter.viewmodel.CartaoViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastrarCartoesActivity extends AppCompatActivity {

    ClienteDto cliente = new ClienteDto(null, null, null, null, null, null, null, null);
    Toolbar toolbar;
    Button buttonCadastrar;
    EditText editTextNomeCartao, editTextNumero, editTextNomeImpresso, editTextData, editTextCVV;
    TextInputLayout inputNome, inputNumero, inputNomeImpresso, inputData, inputCVV;
    String nome, numero, impresso, cvv, data;
    boolean valido;
    CartaoViewModel cartaoViewModel;
    CartaoRepositoryTask repositoryTask = new CartaoRepositoryTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartoes);

        initView();
        initObserve();
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = editTextNomeCartao.getText().toString();
                numero = editTextNumero.getText().toString();
                impresso = editTextNomeImpresso.getText().toString();
                cvv = editTextCVV.getText().toString();
                cliente = UsuarioServices.getUsuario();
                data = editTextData.getText().toString();
                validarFormulario();

                if(valido){
                    SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
                    String dataValidade = in.format(new Date(data));
                    cartaoViewModel.cadastrarCartao(cliente.getCpf(), nome, impresso, numero, cvv, dataValidade);
                }
            }
        });
    }

    private void initView() {
        cartaoViewModel = new ViewModelProvider(this, new CartaoViewModel.ViewModelFactory(repositoryTask)).get(CartaoViewModel.class);
        buttonCadastrar = findViewById(R.id.btn_cadastrarCartoes);
        editTextNomeCartao = findViewById(R.id.edt_nomeCartao);
        editTextNumero = findViewById(R.id.edt_numeroCartao);
        editTextNomeImpresso = findViewById(R.id.edt_nomeImpresso);
        editTextData = findViewById(R.id.edt_dataValidade);
        editTextCVV = findViewById(R.id.edt_cvv);
        toolbar = findViewById(R.id.toolbar_cadastrar_cartoes);
        inputNome = findViewById(R.id.textInputLayoutNomeCarta);
        inputNumero = findViewById(R.id.textInputLayoutNumero);
        inputNomeImpresso = findViewById(R.id.textInputLayoutImpresso);
        inputData = findViewById(R.id.textInputLayoutDataCartao);
        inputCVV = findViewById(R.id.textInputLayoutCVV);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MascaraServices.Companion.maskFormatter(editTextNumero, "NNNN NNNN NNNN NNNN");
        MascaraServices.Companion.maskFormatter(editTextData, "NN/NN/NNNN");
    }

    private void initObserve() {
        cartaoViewModel.getCadastrar().observe(CadastrarCartoesActivity.this, new Observer<Boolean>() {
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

    private void validarFormulario() {
        valido = true;
        if(nome.isEmpty()){
            validacaoFormulario(inputNome, false);
        }else{
            validacaoFormulario(inputNome, true);
        }
        if(numero.length() < 16){
            validacaoFormulario(inputNumero, false);
        }else{
            validacaoFormulario(inputNumero, true);
        }
        if(impresso.isEmpty()){
            validacaoFormulario(inputNomeImpresso, false);
        }else{
            validacaoFormulario(inputNomeImpresso, true);
        }
        if(data.length() < 10){
            validacaoFormulario(inputData, false);
        }else{
            validacaoFormulario(inputData, true);
        }
        if(cvv.length() < 3){
            validacaoFormulario(inputCVV, false);
        }else{
            validacaoFormulario(inputCVV, true);
        }
    }

    private void validacaoFormulario(TextInputLayout input, boolean campo){
        if(campo){
            input.setError("");
        }else{
            input.setError("Obrigat??rio");
            valido = false;
        }
    }

    private void mudarTela() {
        Intent intent = new Intent(CadastrarCartoesActivity.this, DashboardActivity.class);
        intent.putExtra("activity", "Cart??o");
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        mudarTela();
        return super.onSupportNavigateUp();
    }
}