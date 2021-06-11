package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agnciadeturismo.R;

public class CadastrarCartoesActivity extends AppCompatActivity {

    Button buttonCadastrar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cartoes);

        initView();
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CadastrarCartoesActivity.this, "Cadastrado com sucesso!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initView() {
        buttonCadastrar = findViewById(R.id.btn_cadastrarCartoes);
        toolbar = findViewById(R.id.toolbar_cadastrar_cartoes);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}