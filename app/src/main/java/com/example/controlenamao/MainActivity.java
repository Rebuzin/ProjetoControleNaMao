package com.example.controlenamao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    MainHomeFragment mainHomeFragment = new MainHomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Métodos para o Navigation Drawer
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /**
         * Ver o motivo de isso quebrar o layout(sem fica aparentemente certo
         * "Corrigi" a quebra, mas o problema do layout continua
         *
         * **/
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.lnTeste, mainHomeFragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                break;

            case R.id.nav_freight:

                getSupportFragmentManager().beginTransaction().replace(R.id.lnTeste, new FreightFragment()).commit();
                break;

            case R.id.nav_veihcle:

                getSupportFragmentManager().beginTransaction().replace(R.id.lnTeste, new VeihcleFragment()).commit();
                break;

            case R.id.nav_expense:

                getSupportFragmentManager().beginTransaction().replace(R.id.lnTeste, new ExpenseFragment()).commit();
                break;

            case R.id.nav_about:

                getSupportFragmentManager().beginTransaction().replace(R.id.lnTeste, new AboutFragment()).commit();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Você saiu do aplicativo!", Toast.LENGTH_SHORT).show();
                finishAffinity();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}