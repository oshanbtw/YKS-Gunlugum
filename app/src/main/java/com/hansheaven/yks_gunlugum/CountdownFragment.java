package com.hansheaven.yks_gunlugum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class CountdownFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_countdown, container, false);
        View rootView = inflater.inflate(R.layout.fragment_countdown, container, false);

        CountdownView geriSayimTYT = rootView.findViewById(R.id.geriSayimTYT);
        CountdownView geriSayimAYT = rootView.findViewById(R.id.geriSayimAYT);
        CountdownView geriSayimYDT = rootView.findViewById(R.id.geriSayimYDT);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        String tarihTYT = "18-06-2022 07:15:00"; // Sınav saati aslında 10.15 fakat kütüphane 3 saat ileriyi gösteriyor.
        String tarihAYT = "19-06-2022 07:15:00";
        String tarihYDT = "19-06-2022 12:45:00";
        Date guncel = new Date();

        try{
            Date dateTYT = sdf.parse(tarihTYT);
            Date dateAYT = sdf.parse(tarihAYT);
            Date dateYDT = sdf.parse(tarihYDT);

            long guncelTarih = guncel.getTime();

            long hedefTarihTYT = dateTYT.getTime();
            long hedefTarihAYT = dateAYT.getTime();
            long hedefTarihYDT = dateYDT.getTime();

            long kalanTarihTYT = hedefTarihTYT - guncelTarih;
            long kalanTarihAYT= hedefTarihAYT - guncelTarih;
            long kalanTarihYDT = hedefTarihYDT - guncelTarih;

            geriSayimTYT.start(kalanTarihTYT);
            geriSayimAYT.start(kalanTarihAYT);
            geriSayimYDT.start(kalanTarihYDT);

        }catch (ParseException e ){
            e.printStackTrace();
        }


        return rootView;
    }
}
