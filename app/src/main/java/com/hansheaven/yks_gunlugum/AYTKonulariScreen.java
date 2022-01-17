package com.hansheaven.yks_gunlugum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AYTKonulariScreen extends AppCompatActivity {

    ImageView iv_aytback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_aytkonulari);

        init();
        clickOlaylari();
    }

    public void init(){
        iv_aytback = (ImageView) findViewById(R.id.iv_aytkonulari_geri);
    }

    public void clickOlaylari(){
        iv_aytback.setOnClickListener(v -> {
            Intent go = new Intent(getApplicationContext(), MainScreen.class);
            startActivity(go);
        });
    }
}