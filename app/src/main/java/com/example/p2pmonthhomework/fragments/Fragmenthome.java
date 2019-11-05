package com.example.p2pmonthhomework.fragments;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.mybasemodule.BaseFragment;
import com.example.p2pmonthhomework.MyRoundView;
import com.example.p2pmonthhomework.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Fragmenthome extends BaseFragment {
    Banner mbanner;
    List<String> titles = new ArrayList();
    List<Integer> images = new ArrayList();
    private MyRoundView roundPro_home;

    private int flag = 0;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@NotNull View view) {
        mbanner = view.findViewById(R.id.mbanner);
        roundPro_home = view.findViewById(R.id.roundPro_home);

        mbanner.setBannerAnimation(Transformer.ZoomOutSlide);

        if (flag == 0) {
            new Thread(roundPro_home).start();
            flag = 1;
        }
    }

    @Override
    public void initData() {

        titles.clear();
        images.clear();

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
