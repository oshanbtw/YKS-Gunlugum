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
import java.util.Map;



public class ListViewItemAdaptor extends RecyclerView.Adapter<ListViewItemAdaptor.MainViewHolder> {



    Context context ;
    Map<String, Boolean> dersList;
    String sinavAdi;
    String dersAdi;

    FirebaseAuth mAuth;
    DatabaseReference mReference;
    FirebaseUser mUser;

    public ListViewItemAdaptor(Context context, Map<String, Boolean> dersList, String sinavAdi, String dersAdi) {
        this.context = context;
        this.dersList = dersList;
        this.sinavAdi = sinavAdi;
        this.dersAdi = dersAdi;

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

        holder.dersKonulari.setText(dersList.keySet().toArray()[position].toString());
        if((Boolean) dersList.values().toArray()[position]){
            holder.konularLayout.setBackgroundResource(R.drawable.gradient_topics_list_green);
        }
        else{
            holder.konularLayout.setBackgroundResource(R.drawable.gradient_topics_list);
        }

        holder.konularLayout.setOnClickListener(v -> {
            String konuAdi = holder.dersKonulari.getText().toString();

            if((Boolean) this.dersList.values().toArray()[holder.getLayoutPosition()]){
                this.dersList.put(konuAdi, false);
                holder.konularLayout.setBackgroundResource(R.drawable.gradient_topics_list);
                mReference.child("Kullanıcılar").child(mUser.getUid()).child(sinavAdi).child(dersAdi).child(konuAdi).setValue(false);
            }
            else{
                this.dersList.put(konuAdi, true);
                holder.konularLayout.setBackgroundResource(R.drawable.gradient_topics_list_green);
                mReference.child("Kullanıcılar").child(mUser.getUid()).child(sinavAdi).child(dersAdi).child(konuAdi).setValue(true);
            }
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