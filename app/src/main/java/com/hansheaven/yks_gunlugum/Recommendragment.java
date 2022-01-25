package com.hansheaven.yks_gunlugum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class Recommendragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_recommend, container, false);
        View rootView = inflater.inflate(R.layout.fragment_recommend, container, false);

        SliderView sliderView, sliderView2;
        int[] images = {R.drawable.reklam,
                R.drawable.reklam,
                R.drawable.reklam,
                R.drawable.reklam,
                R.drawable.reklam,
                R.drawable.reklam};

        int[] images2 = {R.drawable.karekok,
                R.drawable.limit,
                R.drawable.bilgisarmal,
                R.drawable.palme};



        sliderView = rootView.findViewById(R.id.image_slider);
        sliderView2 = rootView.findViewById(R.id.image_slider2);

        SliderAdapter sliderAdapter = new SliderAdapter(images);
        SliderAdapter sliderAdapter2 = new SliderAdapter(images2);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        sliderView2.setSliderAdapter(sliderAdapter2);
        sliderView2.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView2.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView2.startAutoCycle();

        return rootView;
    }
}
