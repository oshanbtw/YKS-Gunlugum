package com.hansheaven.yks_gunlugum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopicsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_topics, container, false);
        View rootView = inflater.inflate(R.layout.fragment_topics, container, false);

        LinearLayout ly_tyt, ly_ayt, ly_ydt;

        ly_tyt = rootView.findViewById(R.id.ly_topics_tyt);
        ly_ayt = rootView.findViewById(R.id.ly_topics_ayt);
        ly_ydt = rootView.findViewById(R.id.ly_topics_ydt);

        ly_tyt.setOnClickListener(v -> {
            Intent go = new Intent(getContext(), TYTKonulariScreen.class);
            startActivity(go);
        });

        ly_ayt.setOnClickListener(v -> {
            Intent go = new Intent(getContext(), AYTKonulariScreen.class);
            startActivity(go);
        });

        ly_ydt.setOnClickListener(v -> {
            Intent go = new Intent(getContext(), YDTKonulariScreen.class);
            startActivity(go);
        });

        return rootView;
    }
}
