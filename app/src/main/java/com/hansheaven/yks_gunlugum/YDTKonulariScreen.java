package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class YDTKonulariScreen extends AppCompatActivity {


    ImageView iv_ydback;
    RecyclerView recyclerView;
    ListViewItemAdaptor recyclerAdapter;

    //Database
    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;

    Map<String, Map<String, Boolean>> ydtDerslerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_ydtkonulari);

        init();
        getData(tytDersler -> {
            setRecycler(ydtDerslerMap.get("Ingilizce"));
            clickOlaylari();
        });
    }

    public void init(){
        // Firebase datebase.
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference();

        iv_ydback = findViewById(R.id.iv_ydtkonulari_geri);
        recyclerView = findViewById(R.id.recyclerViewYdt);
    }

    void setRecycler(Map<String, Boolean> myMap) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new ListViewItemAdaptor(this, myMap, "YDTKonulari", "Ingilizce");
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void clickOlaylari(){
        iv_ydback.setOnClickListener(v -> {
            Intent go = new Intent(getApplicationContext(), MainScreen.class);
            startActivity(go);
        });
    }


    public void getData(final YDTKonulariScreen.GetDerslerCallback getDerslerCallback) {

        mReference = mReference.child("Kullanıcılar").child(mUser.getUid()).child("YDTKonulari");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                ydtDerslerMap = new HashMap<>();


                for(DataSnapshot ders: snapshot.getChildren()){
                    Map<String, Boolean> ydtKonularMap = new HashMap<>();

                    for(DataSnapshot konu: ders.getChildren()){
                        ydtKonularMap.put(konu.getKey(), (Boolean) konu.getValue());

                    }

                    ydtDerslerMap.put(ders.getKey(), ydtKonularMap);
                }


                getDerslerCallback.onCallback(ydtDerslerMap);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public interface GetDerslerCallback {
        void onCallback(Map<String, Map<String, Boolean>> ydtDersler);
    }

}