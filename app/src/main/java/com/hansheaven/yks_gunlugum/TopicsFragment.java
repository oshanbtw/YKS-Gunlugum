package com.hansheaven.yks_gunlugum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TopicsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_topics, container, false);
        View rootView = inflater.inflate(R.layout.fragment_topics, container, false);

        Intent go = new Intent(getActivity(), TYTKonulariScreen.class);
        startActivity(go);

        return rootView;
    }
}
