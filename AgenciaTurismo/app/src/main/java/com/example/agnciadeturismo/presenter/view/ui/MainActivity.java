package com.example.agnciadeturismo.presenter.view.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.agnciadeturismo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment fragment = new HomeFragment();

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
            String activity = bundle.getString("activity");
            if(activity.equals("carrinho")){
                fragment = new CarrinhoFragment();
            }else if(activity.equals("perfil")){
                fragment = new PerfilFragment();
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_home, fragment)
                .commit();
    }

}