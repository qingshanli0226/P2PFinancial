package com.example.p2pmonthhomework.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.common.ErrorCodes;
import com.example.p2pmonthhomework.HomePresenter;
import com.example.common.view.MyRoundView;
import com.example.p2pmonthhomework.MyApplication;
import com.example.p2pmonthhomework.R;
import com.example.p2pmonthhomework.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Fragmenthome extends BaseFragment implements IBaseView<HomeBean> {
    Banner mbanner;
    List<String> titles = new ArrayList();
    List<String> images = new ArrayList();
    private MyRoundView roundPro_home;
    private View layout_load;
    private View layout_home;
    private ImageView iv_image;
    private TextView tv_loadtext;

    private int flag = 0;


    private IBasePresenter iBasePresenter;
    private StartThread startThread;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    iv_image.startAnimation(MyApplication.getInstance().getRotateAnimaiton());
                    break;
                case 200:
                    tv_loadtext.setText("加载数据失败");
                    startThread.interrupt();
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@NotNull View view) {
        mbanner = view.findViewById(R.id.mbanner);
        roundPro_home = view.findViewById(R.id.roundPro_home);
        layout_load = view.findViewById(R.id.layout_load);
        layout_home = view.findViewById(R.id.layout_home);
        iv_image = view.findViewById(R.id.iv_image);
        tv_loadtext = view.findViewById(R.id.tv_loadtext);

        mbanner.setBannerAnimation(Transformer.ZoomOutSlide);

        if (flag == 0) {
            new Thread(roundPro_home).start();
            flag = 1;
        }
    }

    @Override
    public void initData() {
        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getData();
    }

    public void setBanner(List<HomeBean.ImageArrBean> imageArr){
        titles.clear();
        images.clear();

        titles.add("分享砍学费");
        titles.add("人脉总动员");
        titles.add("想不到你是这样的app");
        titles.add("购物节，爱不单行");

        for (int i = 0 ; i < imageArr.size(); i++){
            HomeBean.ImageArrBean imageArrBean = imageArr.get(i);
            String imaurl = imageArrBean.getIMAURL();
            images.add(imaurl);
        }

        mbanner.setImages(images);
        mbanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context)
                        .load(path)
                        .into(imageView);
            }
        });
        mbanner.setBannerTitles(titles);
        mbanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        mbanner.setBannerAnimation(Transformer.Stack);
        mbanner.setDelayTime(2000);
        mbanner.isAutoPlay(true);
        mbanner.start();
    }

    @Override
    public void onGetDataSuccess(HomeBean data) {
        List<HomeBean.ImageArrBean> imageArr = data.getImageArr();
        setBanner(imageArr);
        layout_home.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetDataListSuccess(List<HomeBean> data) {
    }

    @Override
    public void onGetDataFailed(String message, ErrorCodes codes) {
        Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
        startThread.flag2 = 1;
        tv_loadtext.setText("获取数据失败");

        layout_load.setVisibility(View.VISIBLE);
        layout_home.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        layout_load.setVisibility(View.VISIBLE);
        startThread = new StartThread();

        startThread.flag2 = 0;
        startThread.time = 0;

        startThread.start();
    }

    @Override
    public void hideLoading() {
        Log.e("####","停止");
        startThread.flag2 = 1;
        startThread.interrupt();

        layout_load.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iBasePresenter.detachView();
    }

    public class StartThread extends Thread {

        public int flag2 = 0;
        int time = 0;

        @Override
        public void run() {
            super.run();

            while (flag2==0){
                time+=2;
                Log.e("####","+1");

                if(time==6){
                    flag2 = 1;
                    handler.sendEmptyMessage(200);
                }else {
                    handler.sendEmptyMessage(100);
                }
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
