package com.example.p2pmonthhomework.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.p2pmonthhomework.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Fragment_home1 extends Fragment {
    Banner mbanner;
    List<String> titles = new ArrayList();
    List<Integer> images = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mbanner = view.findViewById(R.id.mbanner);

        mbanner.setBannerAnimation(Transformer.ZoomOutSlide);
        titles.add("分享砍学费");
        titles.add("人脉总动员");
        titles.add("想不到你是这样的app");
        titles.add("购物节，爱不单行");

        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);

        mbanner.setImages(images);
        mbanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((int) path);
            }
        });
        mbanner.setBannerTitles(titles);
        mbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mbanner.setDelayTime(2000);
        mbanner.isAutoPlay(true);
        mbanner.start();
    }
}
