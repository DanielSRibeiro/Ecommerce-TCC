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
import com.example.agnciadeturismo.viewmodel.ViewModelCliente;


public class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "CadastrarUsuarioActivit";
    Toolbar toolbar;
    Button buttonCadastrar;
    ViewModelCliente viewModelCliente;
    EditText editTextNome, editTextSenha, editTextTelefone, editTextCPF, editTextRG, editTextEmail;
    String nome, email, cpf, rg, telefone, senha, img;
    boolean alterar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
        initView();
        initObeserve();
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nome = editTextNome.getText().toString();
                cpf = editTextCPF.getText().toString();
                email = editTextEmail.getText().toString();
                rg = editTextRG.getText().toString();
                telefone = editTextTelefone.getText().toString();
                senha = editTextSenha.getText().toString();

                viewModelCliente.modificarCliente(alterar, nome, email, cpf, rg, telefone, senha, img);
            }
        });
    }

    private void initView() {
        viewModelCliente = new ViewModelProvider(this).get(ViewModelCliente.class);
        toolbar = findViewById(R.id.toolbar_cadastrar);
        buttonCadastrar = findViewById(R.id.btn_cadastrarUsuario);
        editTextNome = findViewById(R.id.edt_nome);
        editTextEmail = findViewById(R.id.edt_email);
        editTextCPF = findViewById(R.id.edt_cpf);
        editTextRG = findViewById(R.id.edt_rg);
        editTextTelefone = findViewById(R.id.edt_telefone);
        editTextSenha = findViewById(R.id.edt_senha);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            alterar = true;
            buttonCadastrar.setText("Alterar conta");
            getSupportActionBar().setTitle("Alterar sua conta");

            editTextCPF.setEnabled(false);
            editTextNome.setText(bundle.getString("nome"));
            editTextCPF.setText(bundle.getString("cpf"));
            editTextEmail.setText(bundle.getString("email"));
            editTextRG.setText(bundle.getString("rg"));
            editTextTelefone.setText(bundle.getString("tel"));
            editTextSenha.setText(bundle.getString("senha"));
            img = bundle.getString("img");
        }
    }

    private void initObeserve() {
        viewModelCliente.clienteCadastrado.observe(CadastrarUsuarioActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String toast) {
                Toast.makeText(CadastrarUsuarioActivity.this, toast, Toast.LENGTH_SHORT).show();
                Log.d("TAG", toast);
                if(toast == ViewModelCliente.cadastradoSucesso){
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}