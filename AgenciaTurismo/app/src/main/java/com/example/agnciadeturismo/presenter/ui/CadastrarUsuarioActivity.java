package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.ClienteDto;
import com.example.agnciadeturismo.viewmodel.ClienteViewModel;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "CadastrarUsuarioActivit";
    Toolbar toolbar;
    Button buttonCadastrar;
    ClienteViewModel clienteViewModel;
    EditText editTextNome, editTextSenha, editTextTelefone, editTextCPF, editTextRG, editTextEmail;
    TextInputLayout inputNome, inputSenha, inputTelefone, inputCPF, inputRG, inputEmail;
    String nome, email, cpf, rg, telefone, senha, img;
    boolean alterar = false, valido;
    ClienteDto cliente = new ClienteDto(null, null, null, null, null, null, null, null);

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
                validarFormulario();

                if(valido){
                    clienteViewModel.modificarCliente(alterar, nome, email, cpf, rg, telefone, senha, img);
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
        if(cpf.length() < 14){
            validacaoFormulario(inputCPF, false);
        }else{
            validacaoFormulario(inputCPF, true);
        }
        if(email.isEmpty()){
            validacaoFormulario(inputEmail, false);
        }else{
            validacaoFormulario(inputEmail, true);
        }
        if(rg.length() < 9){
            validacaoFormulario(inputRG, false);
        }else{
            validacaoFormulario(inputRG, true);
        }
        if(telefone.length() < 11){
            validacaoFormulario(inputTelefone, false);
        }else{
            validacaoFormulario(inputTelefone, true);
        }
        if(senha.length() < 3){
            validacaoFormulario(inputSenha, false);
        }else{
            validacaoFormulario(inputSenha, true);
        }
    }

    private void validacaoFormulario(TextInputLayout input, boolean campo){
        if(campo){
            input.setError("");
        }else{
            input.setError("Campo obrigatório");
            valido = false;
        }
    }

    private void initView() {
        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);
        toolbar = findViewById(R.id.toolbar_cadastrar);
        buttonCadastrar = findViewById(R.id.btn_cadastrarUsuario);
        editTextNome = findViewById(R.id.edt_nome);
        editTextEmail = findViewById(R.id.edt_email);
        editTextCPF = findViewById(R.id.edt_cpf);
        editTextRG = findViewById(R.id.edt_rg);
        editTextTelefone = findViewById(R.id.edt_telefone);
        editTextSenha = findViewById(R.id.edt_senha);

        inputNome = findViewById(R.id.textInputLayoutNome);
        inputEmail = findViewById(R.id.textInputLayoutEmail);
        inputCPF = findViewById(R.id.textInputLayoutCPF);
        inputRG = findViewById(R.id.textInputLayoutRG);
        inputTelefone = findViewById(R.id.textInputLayoutTelefone);
        inputSenha = findViewById(R.id.textInputLayoutSenha);

        MaskFormatter();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cliente = MainActivity.getUsuario();
        if(cliente.getCpf() != null){
            alterar = true;
            buttonCadastrar.setText("Alterar conta");
            getSupportActionBar().setTitle("Alterar sua conta");

            editTextCPF.setEnabled(false);
            editTextNome.setText(cliente.getNome());
            editTextCPF.setText(cliente.getCpf());
            editTextEmail.setText(cliente.getEmail());
            editTextRG.setText(cliente.getRg());
            editTextTelefone.setText(cliente.getTelefone());
            editTextSenha.setText(cliente.getSenha());
            img = cliente.getImg();
        }
    }

    private void MaskFormatter() {
        SimpleMaskFormatter maskCPF = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(editTextCPF, maskCPF);
        editTextCPF.addTextChangedListener(mtw);

        SimpleMaskFormatter maskTel = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtwTel = new MaskTextWatcher(editTextTelefone, maskTel);
        editTextTelefone.addTextChangedListener(mtwTel);
    }

    private void initObeserve() {
        clienteViewModel.cadastrado.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean cadastrado) {
                if(cadastrado == true){
                    Toast.makeText(CadastrarUsuarioActivity.this, ClienteViewModel.cadastradoSucesso, Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(CadastrarUsuarioActivity.this, "Esse CPF já foi cadastrado!!", Toast.LENGTH_SHORT).show();
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