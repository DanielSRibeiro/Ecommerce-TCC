package com.example.agnciadeturismo.presenter.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.model.ClienteDto;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment = new HomeFragment();
    static ClienteDto cliente = new ClienteDto(null, null, null, null, null, null, null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_buscar:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_carrinho:
                        fragment = new CarrinhoFragment();
                        break;
                    case R.id.nav_perfil:
                        fragment = new LoginFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, fragment)
                        .commit();
                return true;
            }
        });
    }

    private void initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            fragment = new CarrinhoFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, fragment)
                .commit();
    }

    public static ClienteDto setUsuario(ClienteDto novoCliente){
        return cliente = novoCliente;
    }

    public static ClienteDto getUsuario(){
        return cliente;
    }

}