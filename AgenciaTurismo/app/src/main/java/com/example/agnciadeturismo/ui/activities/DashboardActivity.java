package com.example.agnciadeturismo.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.ui.fragment.CartaoFragment;
import com.example.agnciadeturismo.ui.fragment.CompraFragment;

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
            switch (bundle.getString("nome")){
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
        finish();
        return super.onSupportNavigateUp();
    }
}