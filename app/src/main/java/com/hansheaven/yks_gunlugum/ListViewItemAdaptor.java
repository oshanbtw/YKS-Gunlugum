package com.hansheaven.yks_gunlugum;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListViewItemAdaptor extends RecyclerView.Adapter<ListViewItemAdaptor.MainViewHolder> {



    Context context ;
    List<String> dersList;

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;

    public ListViewItemAdaptor(Context context, List<String > dersList) {
        this.context = context;
        this.dersList = dersList;

        this.mAuth = FirebaseAuth.getInstance();
        this.mReference = FirebaseDatabase.getInstance().getReference();
        this.mUser = mAuth.getCurrentUser();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.itembirseyler,parent,false));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holder.dersKonulari.setText(dersList.get(position).toString());

        mReference = FirebaseDatabase.getInstance().getReference("Kullanıcılar").child(mUser.getUid()).child("1TYTKonulari");
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.konularLayout.setOnClickListener(v -> {
            holder.konularLayout.setBackgroundResource(R.drawable.gradient_topics_list_green);
        });


    }

    @Override
    public int getItemCount() {
        return dersList.size();
    }

    public static final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView dersKonulari;
        ConstraintLayout konularLayout;


        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            dersKonulari = itemView.findViewById(R.id.dersKonulari);
            konularLayout = itemView.findViewById(R.id.konularLayout);
        }
    }


}