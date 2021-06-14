package com.example.agnciadeturismo.presenter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.agnciadeturismo.R;

public class DashboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textViewTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_dashboard);
        textViewTexto = findViewById(R.id.txt_texto);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Fragment fragment = null;
            switch (bundle.getString("activity")){
                case "Cartão":
                    fragment = new CartaoFragment();
                    break;
                case "Compras":
                    fragment = new CompraFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_dasboard, fragment)
                    .commit();
        }
    }

    public void setTitulo(String titulo, String texto){
        getSupportActionBar().setTitle(titulo);
        textViewTexto.setText(texto);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        intent.putExtra("activity", "perfil");
        startActivity(intent);
        return super.onSupportNavigateUp();
    }
}