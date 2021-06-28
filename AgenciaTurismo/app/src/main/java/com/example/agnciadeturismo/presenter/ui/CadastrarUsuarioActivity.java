package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
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
    boolean alterar = false, valido, validoCPF;
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
                    if(validarCPF()){
                        Toast.makeText(CadastrarUsuarioActivity.this, "true", Toast.LENGTH_SHORT).show();
                        clienteViewModel.modificarCliente(alterar, nome, email, cpf, rg, telefone, senha, img);
                    }else{
                        Toast.makeText(CadastrarUsuarioActivity.this, "CPF inválido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    private boolean validarCPF() {
        String s1, s2, s3, s4, s5, s6, s7, s8, s9, confere = "";
        int n1, n2, n3, n4, n5, n6, n7, n8, n9, verificador1, verificador2;
        s1 = cpf.substring(0,1); n1 = Integer.parseInt(s1);
        s2 = cpf.substring(1,2); n2 = Integer.parseInt(s2);
        s3 = cpf.substring(2,3); n3 = Integer.parseInt(s3);
        s4 = cpf.substring(4,5); n4 = Integer.parseInt(s4);
        s5 = cpf.substring(5,6); n5 = Integer.parseInt(s5);
        s6 = cpf.substring(6,7); n6 = Integer.parseInt(s6);
        s7 = cpf.substring(8,9); n7 = Integer.parseInt(s7);
        s8 = cpf.substring(9,10); n8 = Integer.parseInt(s8);
        s9 = cpf.substring(10,11); n9 = Integer.parseInt(s9);

        if(n1 == n2 && n2 == n3 && n3 == n4 && n4 == n5 && n5 == n6 && n6 == n7 && n7 == n8 && n8 == n9){
            validoCPF = false;
            Log.d(TAG, "esse CPF: "+confere);
        }else{
            verificador1 = (n1*10 + n2*9 + n3*8 + n4*7 + n5*6 + n6*5 + n7*4 + n8*3 + n9*2);
            if((verificador1 % 11) < 2){
                verificador1 = 0;
            }else{
                verificador1 = 11 - (verificador1 % 11);
            }

            verificador2 = (n1*11 + n2*10 + n3*9 + n4*8 + n5*7 + n6*6 + n7*5 + n8*4 + n9*3 + verificador1 * 2);

            if((verificador2 % 11) < 2){
                verificador2 = 0;
            }else{
                verificador2 = 11 - (verificador2 % 11);
            }

            confere = (s1 + s2 + s3 + "."+ s4 + s5 + s6 + "."+ s7 +s8 + s9 + "-" + verificador1 + "" + verificador2);

            if(confere.equals(cpf)){
                validoCPF = true;
            }else{
                validoCPF = false;
            }

            Log.d(TAG, "CPF: "+confere);
        }

        return validoCPF;
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