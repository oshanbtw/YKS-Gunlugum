package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TYTKonulariScreen extends AppCompatActivity {

    //Ders konuları
    String[] mateDers = {"Sayılar","Sayı Basamakları","Bölme ve Bölünebilme","OBEB-OKEK","Rasyonel Sayılar","Basit Eşitsizlikler","Mutlak Değer",
   "Üslü Sayılar", "Köklü Sayılar", "Çarpanlara Ayırma", "Oran Orantı", "Denklem Çözme", "Problemler", "Kümeler-Kartezyen Çarpım", "Fonskiyonlar", "Permütasyon",
   "Kombinasyon", "Binom", "Olasılık", "İstatistik", "2.Dereceden Denklemler", "Karmaşık Sayılar", "Polinomlar"};

    List<String> matematikDersleri = new ArrayList<>();

    RecyclerView recyclerView;
    ListViewItemAdaptor recyclerAdapter;

    ImageView iv_tytback;
    Button btn_matematik, btn_gecerliDers;
    LinearLayout ly_dersler;


    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;

    Map<String, Map<String, Boolean>> derslerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_tytkonulari);

        init();
        clickOlaylari();
        getData(tytDersler -> {
            setRecycler(derslerMap.get("TYTMatematik"));
        });
    }

    public void init(){

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();

        matematikDersleri.addAll(Arrays.asList(mateDers));

        iv_tytback = findViewById(R.id.iv_tytkonulari_geri);

        btn_matematik = findViewById(R.id.btn_tytkonulari_matematik);
        btn_gecerliDers = findViewById(R.id.btn_gecerliDers);

        ly_dersler = findViewById(R.id.ly_dersler);

        recyclerView = findViewById(R.id.recyclerView);
    }

    void setRecycler(Map<String, Boolean> myMap) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new ListViewItemAdaptor(this, myMap);
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void clickOlaylari(){
        iv_tytback.setOnClickListener(v -> {
            Intent go = new Intent(getApplicationContext(), MainScreen.class);
            startActivity(go);
        });

        btn_matematik.setOnClickListener(v -> {
           recyclerView.setVisibility(View.VISIBLE);
           ly_dersler.setVisibility(View.GONE);
           btn_gecerliDers.setVisibility(View.VISIBLE);
           btn_gecerliDers.setText(btn_matematik.getText().toString());
        });

        btn_gecerliDers.setOnClickListener(v -> {
            recyclerView.setVisibility(View.GONE);
            ly_dersler.setVisibility(View.VISIBLE);
            btn_gecerliDers.setVisibility(View.GONE);
        });
    }

    public void getData(final GetDerslerCallback getDerslerCallback) {

        mReference = mReference.child("Kullanıcılar").child(mUser.getUid()).child("TYTKonulari");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                derslerMap = new HashMap<>();

                for(DataSnapshot ders: snapshot.getChildren()){
                    Map<String, Boolean> konularMap = new HashMap<>();

                    for(DataSnapshot konu: ders.getChildren()){
                        konularMap.put(konu.getKey(), (Boolean) konu.getValue());

                    }

                    derslerMap.put(ders.getKey(), konularMap);
                }

                getDerslerCallback.onCallback(derslerMap);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public interface GetDerslerCallback {
        void onCallback(Map<String, Map<String, Boolean>> tytDersler);
    }

}