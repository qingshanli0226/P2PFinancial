package com.bw.jinrong.controller.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bw.jinrong.R;
import com.bw.view.RoundProgress;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {

    private int id;

    public HomeFragment(int id) {
        this.id = id;
    }

    private ImageView iv_title_back;
    private TextView tv_title;
    private ImageView iv_title_setting;
    private Banner banner;
    private TextView tv_home_product;
    private RoundProgress roundPro_home;
    private TextView tv_home_yearrate;

    private int currentProress;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            roundPro_home.setMax(100);
            for (int i = 0; i < currentProress; i++){
                roundPro_home.setProgress(i + 1);

                SystemClock.sleep(20);
                //强制重绘
                //主线程，分线程，都可以如此调用
                roundPro_home.postInvalidate();
            }
        }
    };

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment initView(inflater.inflate(R.layout.fragment_home, container, false));
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

//    private class GlideImageLoader extends ImageLoader {
//        @Override
//        public void displayImage(Context context, Object path, ImageView imageView) {
//            Glide.with(context).load((String) path).into(imageView);
//        }
//    }
}