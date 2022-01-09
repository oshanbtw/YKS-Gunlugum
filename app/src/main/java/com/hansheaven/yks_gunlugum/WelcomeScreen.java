package com.hansheaven.yks_gunlugum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    Button btn_giris, btn_kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_welcome);

        init();
        acilincaOlacaklar();
        clickOlaylari();
    }

    public void init(){

        btn_kayit = (Button) findViewById(R.id.btn_welcomeKayitOl);
        btn_giris = (Button) findViewById(R.id.btn_welcomeGirisYap);
    }

    public void acilincaOlacaklar(){

    }

    public void clickOlaylari(){

        btn_kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(WelcomeScreen.this, RegisterScreen.class);
                startActivity(go);
                finish();
            }
        });

        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(WelcomeScreen.this, LoginScreen.class);
                startActivity(go);
                finish();
            }
        });
    }

}