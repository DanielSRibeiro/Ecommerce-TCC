package com.example.agnciadeturismo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, new BuscarFragment())
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.nav_buscar:
                        fragment = new BuscarFragment();
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
}