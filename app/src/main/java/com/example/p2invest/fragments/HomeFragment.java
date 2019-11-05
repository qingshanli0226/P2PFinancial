package com.example.p2invest.fragments;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.base.BaseFragment;
import com.example.net.BannerData;
import com.example.net.OkhttpUntils;
import com.example.p2invest.BannerLoader;
import com.example.p2invest.custor.MyProgress;
import com.example.p2invest.R;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {
    private TextView tv_title;
    private ImageView iv_title_setting;
   private Banner banner;
   private ScrollView scrollView;
   MyProgress myProgress;
   int num=1;

   private Handler handler=new Handler(){
       @Override
       public void handleMessage(@NonNull Message msg) {
           super.handleMessage(msg);
           Log.i("wzy", "Handler");
           if (msg.what==0){
               Log.i("wzy", "handleMessage");
               myProgress.refresh();
           }

       }
   };


    @Override
    protected View getcontentview(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.home, container, false);
        myProgress=new MyProgress(getActivity());
        tv_title=view.findViewById(R.id.tv_title);
        iv_title_setting=view.findViewById(R.id.iv_title_setting);
        banner=view.findViewById(R.id.banner);
        scrollView=view.findViewById(R.id.scroll);
        iv_title_setting.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void initData() {
        bannerlistener();
        threadstart();
        Log.i("wzy", "initData");

        iv_title_setting.setVisibility(View.GONE);
    }

    @Override
    public void setListener() {

    }

    private void threadstart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Log.i("wzy", "threadstart");
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void bannerlistener() {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        ArrayList<Integer> list=new ArrayList<>();
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);
        list.add(R.mipmap.ic_launcher);


//        ArrayList<String> s=new ArrayList<>();
//
//        String getdata = new OkhttpUntils().getdata();
//        BannerData bannerData=new Gson().fromJson(getdata,BannerData.class);
//        for (int i = 0; i < bannerData.getImageArr().size(); i++) {
//            s.add(bannerData.getImageArr().get(i).getIMAURL());
//        }



        ArrayList<String> strings = new ArrayList<>();
        strings.add("图片一");
        strings.add("图片二");
        strings.add("图片三");
        strings.add("图片四");
        banner.setBannerTitles(strings);
        banner.isAutoPlay(true);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
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
}
