package com.hansheaven.yks_gunlugum;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataBase {

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;

    boolean isChecked;

    public DataBase (){
        this.mAuth = FirebaseAuth.getInstance();
        this.mReference = FirebaseDatabase.getInstance().getReference();
        this.mUser = mAuth.getCurrentUser();
    }

    public void checkKono(String sinavIsmi){
        mReference = FirebaseDatabase.getInstance().getReference("Kullanıcılar").child(mUser.getUid()).child(sinavIsmi);
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snp : snapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
