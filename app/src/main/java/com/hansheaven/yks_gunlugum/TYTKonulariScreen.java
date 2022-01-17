package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TYTKonulariScreen extends AppCompatActivity {

    String[] mateDers = {"Sayılar","Sayı Basamakları","Bölme ve Bölünebilme","OBEB-OKEK","Rasyonel Sayılar","Basit Eşitsizlikler","Mutlak Değer",
   "Üslü Sayılar", "Köklü Sayılar", "Çarpanlara Ayırma", "Oran Orantı", "Denklem Çözme", "Problemler", "Kümeler-Kartezyen Çarpım", "Fonskiyonlar", "Permütasyon",
   "Kombinasyon", "Binom", "Olasılık", "İstatistik", "2.Dereceden Denklemler", "Karmaşık Sayılar", "Polinomlar"};
    List<String> matematikDersleri = new ArrayList<>();

    RecyclerView recyclerView;
    ListViewItemAdaptor recyclerAdapter;

    ImageView iv_tytback;
    Button btn_matematik;

    Boolean matDurum = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_tytkonulari);

        init();
        setRecycler(matematikDersleri);
        clickOlaylari();
    }

    public void init(){

        matematikDersleri.addAll(Arrays.asList(mateDers));

        iv_tytback = (ImageView) findViewById(R.id.iv_tytkonulari_geri);

        btn_matematik = (Button) findViewById(R.id.btn_tytkonulari_matematik);

    }

    void setRecycler(List<String> myList) {

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new ListViewItemAdaptor(this, myList);
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void clickOlaylari(){
        iv_tytback.setOnClickListener(v -> {
            Intent go = new Intent(getApplicationContext(), MainScreen.class);
            startActivity(go);
        });

        btn_matematik.setOnClickListener(v -> {
            if (matDurum){
                matDurum = false;
                recyclerView.setVisibility(View.VISIBLE);
            }
            else{
                matDurum = true;
                recyclerView.setVisibility(View.GONE);
            }

        });
    }

}