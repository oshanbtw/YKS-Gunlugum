package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TYTKonulariScreen extends AppCompatActivity {

   private FirebaseAuth mAuth;
   private DatabaseReference mReferance;
   private FirebaseUser mUser;

   private Button btn_mat, btn_tur;
   private LinearLayout layout_mat, layout_tur;

    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_tytkonulari);

        init();
        acilincaOlacaklar();
        clickOlaylari();

    }

    public void init(){
        mAuth = FirebaseAuth.getInstance();
        mReferance = FirebaseDatabase.getInstance().getReference();

        btn_mat = (Button) findViewById(R.id.btn_tytkonulari_matematik);
        btn_tur = (Button) findViewById(R.id.btn_tytkonulari_turkce);

        layout_mat = (LinearLayout) findViewById(R.id.layout_kolon_tyt_mat);
        layout_tur = (LinearLayout) findViewById(R.id.layout_kolon_tyt_turkce);
    }

    public void acilincaOlacaklar(){

        mUser = mAuth.getCurrentUser();

        mReferance = FirebaseDatabase.getInstance().getReference("Kullanıcılar").child(mUser.getUid()).child("1TYTKonulari");

        mReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snp : snapshot.getChildren()){

                    //System.out.println(snp.getKey() + " = " + snp.getValue());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void clickOlaylari() {

        btn_mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layout_mat.getVisibility() == View.GONE){
                    layout_mat.setVisibility(View.VISIBLE);
                }
                else layout_mat.setVisibility(View.GONE);
            }
        });

        btn_tur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(layout_tur.getVisibility() == View.GONE){
                    layout_tur.setVisibility(View.VISIBLE);
                }
                else layout_tur.setVisibility(View.GONE);
            }
        });

    }

}