package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);

        init();
        acilincaOlacaklar();
    }

    public void init(){
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListeneer);
    }
    public void acilincaOlacaklar(){
        Fragment fragment = new CountdownFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListeneer =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_countdown:
                            selectedFragment = new CountdownFragment();
                            break;
                        case R.id.nav_topics:
                            selectedFragment = new TopicsFragment();
                            break;
                        case R.id.nav_plan:
                            selectedFragment = new PlanFragment();
                            break;
                        case R.id.nav_recommend:
                            selectedFragment = new Recommendragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
            };
}