package com.example.agnciadeturismo.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.agnciadeturismo.R;
import com.example.agnciadeturismo.ui.fragment.HomeFragment;
import com.example.agnciadeturismo.ui.fragment.CarrinhoFragment;
import com.example.agnciadeturismo.ui.fragment.LoginFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

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
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new HomeFragment())
                .commit();
    }
}