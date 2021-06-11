package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.agnciadeturismo.R;

public class DetalhesActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button buttonComprar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        initView();
        buttonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalhesActivity.this, "Adicionado no carrinho", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetalhesActivity.this, MainActivity.class);
                intent.putExtra("carrinho", "carrinho");
                startActivity(intent);
            }
        });

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_detalhes);
        buttonComprar = findViewById(R.id.btn_comprarPacote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}