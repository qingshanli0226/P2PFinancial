package com.example.p2invest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.base.BaseFragment;
import com.example.base.IBaseView;
import com.example.base.IBsePresenter;
import com.example.net.AppNetConfig;
import com.example.net.BannerData;
import com.example.p2invest.custor.MyProgress;
import com.example.p2invest.R;
import com.example.p2invest.presenter.Home_Presenter;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends BaseFragment implements IBaseView<BannerData> {
    private TextView tv_title;
    private ImageView iv_title_setting;
   private Banner banner;
   private ScrollView scrollView;
   MyProgress myProgress;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                myProgress.setProgress();
            }
        }
    };

    IBsePresenter iBsePresenter;
    @Override
    protected View getcontentview(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.home, container, false);
        myProgress=view.findViewById(R.id.my);
        tv_title=view.findViewById(R.id.tv_title);
        iv_title_setting=view.findViewById(R.id.iv_title_setting);
        banner=view.findViewById(R.id.banner);
        scrollView=view.findViewById(R.id.scroll);
        iv_title_setting.setVisibility(View.GONE);
        initData();
        setListener();
        return view;
    }
    public void initData() {
        iBsePresenter = new Home_Presenter();
        iBsePresenter.attachView(this);
        iBsePresenter.getData(AppNetConfig.INDEX);

        bannerlistener();
        threadstart();
        Log.i("wzy", "initData");

        iv_title_setting.setVisibility(View.GONE);
    }
    public void setListener() {

    }

    private void threadstart() {
        //在分线程中，实现进度的动态变化
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 85; i++) {
                    try {
                        Thread.sleep(20);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void bannerlistener() {
        ArrayList<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        String[] titles = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
        banner.setBannerTitles(Arrays.asList(titles));
        banner.isAutoPlay(true);
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(2000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setImageResource((Integer) path);
            }
        });
        banner.start();

    }
    @Override
    public void onGetDataSucces(BannerData data) {
        Log.i("onGetDataSucces", "onGetDataSucces: "+data.toString());
    }

    @Override
    public void onGetDataListSuccess(List<BannerData> data) {

        Log.i("onGetDataListSuccess", "onGetDataListSuccess: "+data.size());
    }

    @Override
    public void onGetDataFailed(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
