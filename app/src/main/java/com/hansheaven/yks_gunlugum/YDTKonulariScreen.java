package com.hansheaven.yks_gunlugum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class YDTKonulariScreen extends AppCompatActivity {


    ImageView iv_ydback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_ydtkonulari);

        init();
        clickOlaylari();
    }

    public void init(){
        iv_ydback = (ImageView) findViewById(R.id.iv_ydtkonulari_geri);
    }

    public void clickOlaylari(){
        iv_ydback.setOnClickListener(v -> {
            Intent go = new Intent(getApplicationContext(), MainScreen.class);
            startActivity(go);
        });
    }

}