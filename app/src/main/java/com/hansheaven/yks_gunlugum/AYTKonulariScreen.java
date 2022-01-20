package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AYTKonulariScreen extends AppCompatActivity {

    ImageView iv_aytback;
    Button btn_matematik, btn_gecerliDers, btn_edebiyat, btn_geometri, btn_fizik, btn_kimya, btn_biyoloji, btn_cografya, btn_tarih, btn_felsefe, btn_din;
    LinearLayout ly_dersler;
    RecyclerView recyclerView;
    ListViewItemAdaptor recyclerAdapter;

    //Database
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;

    Map<String, Map<String, Boolean>> aytDerslerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_aytkonulari);

        init();
        getData(tytDersler -> {
            clickOlaylari();
        });
    }

    public void init(){

        // Firebase datebase.
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();

        // AYT sayfasındaki geri işareti.
        iv_aytback = findViewById(R.id.iv_aytkonulari_geri);

        // Ders butonları.
        btn_gecerliDers = findViewById(R.id.btn_ayt_gecerliDers);
        btn_matematik = findViewById(R.id.btn_aytkonulari_matematik);
        btn_edebiyat = findViewById(R.id.btn_aytkonulari_edebiyat);
        btn_geometri = findViewById(R.id.btn_aytkonulari_geometri);
        btn_fizik = findViewById(R.id.btn_aytkonulari_fizik);
        btn_kimya = findViewById(R.id.btn_aytkonulari_kimya);
        btn_biyoloji = findViewById(R.id.btn_aytkonulari_biyoloji);
        btn_cografya = findViewById(R.id.btn_aytkonulari_cografya);
        btn_tarih = findViewById(R.id.btn_aytkonulari_tarih);
        btn_felsefe = findViewById(R.id.btn_aytkonulari_felsefe);
        btn_din = findViewById(R.id.btn_aytkonulari_din);

        //
        ly_dersler = findViewById(R.id.ly_ayt_dersler);

        //
        recyclerView = findViewById(R.id.recyclerViewAyt);
    }

    void setRecycler(Map<String, Boolean> myMap, String dersAdi) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new ListViewItemAdaptor(this, myMap, "AYTKonulari", dersAdi);
        recyclerView.setAdapter(recyclerAdapter);

    }

    //Tıklanabilir nesnelere tıklandığı zaman olacaklar.
    public void clickOlaylari(){
        iv_aytback.setOnClickListener(v -> {
            Intent go = new Intent(getApplicationContext(), MainScreen.class);
            startActivity(go);
        });

        btn_gecerliDers.setOnClickListener(v -> {
            recyclerView.setVisibility(View.GONE);
            ly_dersler.setVisibility(View.VISIBLE);
            btn_gecerliDers.setVisibility(View.GONE);
        });

        btn_matematik.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_matematik.getText().toString());
            setRecycler(aytDerslerMap.get("AYTMatematik"), "AYTMatematik");
        });

        btn_edebiyat.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_edebiyat.getText().toString());
            setRecycler(aytDerslerMap.get("AYTEdebiyat"), "AYTEdebiyat");
        });

        btn_geometri.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_geometri.getText().toString());
            setRecycler(aytDerslerMap.get("AYTGeometri"), "AYTGeometri");
        });

        btn_fizik.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_fizik.getText().toString());
            setRecycler(aytDerslerMap.get("AYTFizik"), "AYTFizik");
        });

        btn_kimya.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_kimya.getText().toString());
            setRecycler(aytDerslerMap.get("AYTKimya"), "AYTKimya");
        });

        btn_biyoloji.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_biyoloji.getText().toString());
            setRecycler(aytDerslerMap.get("AYTBiyoloji"), "AYTBiyoloji");
        });

        btn_cografya.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_cografya.getText().toString());
            setRecycler(aytDerslerMap.get("AYTCografya"), "AYTCografya");
        });

        btn_tarih.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_tarih.getText().toString());
            setRecycler(aytDerslerMap.get("AYTTarih"), "AYTTarih");
        });

        btn_felsefe.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_felsefe.getText().toString());
            setRecycler(aytDerslerMap.get("AYTFelsefe"), "AYTFelsefe");
        });

        btn_din.setOnClickListener(v -> {
            recyclerView.setVisibility(View.VISIBLE);
            ly_dersler.setVisibility(View.GONE);
            btn_gecerliDers.setVisibility(View.VISIBLE);
            btn_gecerliDers.setText(btn_din.getText().toString());
            setRecycler(aytDerslerMap.get("AYTDin"), "AYTDin");
        });
    }

    public void getData(final AYTKonulariScreen.GetDerslerCallback getDerslerCallback) {

        mReference = mReference.child("Kullanıcılar").child(mUser.getUid()).child("AYTKonulari");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                aytDerslerMap = new HashMap<>();

                for(DataSnapshot ders: snapshot.getChildren()){
                    Map<String, Boolean> aytKonularMap = new HashMap<>();

                    for(DataSnapshot konu: ders.getChildren()){
                        aytKonularMap.put(konu.getKey(), (Boolean) konu.getValue());

                    }

                    aytDerslerMap.put(ders.getKey(), aytKonularMap);
                }

                getDerslerCallback.onCallback(aytDerslerMap);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public interface GetDerslerCallback {
        void onCallback(Map<String, Map<String, Boolean>> aytDersler);
    }
}