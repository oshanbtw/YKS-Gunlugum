package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

   private String[] mateDers = {"Sayılar","Sayı Basamakları","Bölme ve Bölünebilme","OBEB-OKEK","Rasyonel Sayılar","Basit Eşitsizlikler","Mutlak Değer",
   "Üslü Sayılar", "Köklü Sayılar", "Çarpanlara Ayırma", "Oran Orantı", "Denklem Çözme", "Problemler", "Kümeler-Kartezyen Çarpım", "Fonskiyonlar", "Permütasyon",
   "Kombinasyon", "Binom", "Olasılık", "İstatistik", "2.Dereceden Denklemler", "Karmaşık Sayılar", "Polinomlar"};
   private List<String> matematikDersleri = new ArrayList<>();


   private RecyclerView recyclerView;
   private ListViewItemAdaptor recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_tytkonulari);

        init();
        setRecycler(matematikDersleri);

    }

    public void init(){

        matematikDersleri.addAll(Arrays.asList(mateDers));

    }


    void setRecycler(List<String> myList) {

        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new ListViewItemAdaptor(this, myList);
        recyclerView.setAdapter(recyclerAdapter);

    }

}