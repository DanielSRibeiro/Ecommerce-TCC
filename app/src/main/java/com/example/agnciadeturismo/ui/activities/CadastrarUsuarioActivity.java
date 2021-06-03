package com.example.agnciadeturismo.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agnciadeturismo.R;


public class CadastrarUsuarioActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        initView();
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastrarUsuarioActivity.this, "Cadastrado com sucesso!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_cadastrar);
        buttonCadastrar = findViewById(R.id.btn_cadastrarUsuario);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}