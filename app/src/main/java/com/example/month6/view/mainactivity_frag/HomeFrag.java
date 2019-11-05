package com.example.month6.view.mainactivity_frag;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.diyviews.ProGrossView;
import com.example.month6.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class HomeFrag extends Fragment {
    Context ctx;
    View view;
    private Banner banner;
    private ProGrossView proGrossView;

    public HomeFrag(Context ctx) {
        this.ctx = ctx;
    }

    Handler handler= new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            proGrossView.reFush();
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.Homefrag, null);
        proGrossView=view.findViewById(R.id.proGrossView);
        banner=view.findViewById(R.id.banner);
        //更新进度到90%
        updateProgress(0.9);
        setBanner();
        return view;
    }
    //进度条滚动线程,自动结束
    private void updateProgress(final double num){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (proGrossView.num>=(360*num)){
                        break;
                    }
                }
            }
        }).start();
    }
    //设置轮播图
    private void setBanner(){
        ArrayList<Bitmap> list=new ArrayList<>();
        list.add(BitmapFactory.decodeResource(ctx.getResources(),R.mipmap.i1));
        list.add(BitmapFactory.decodeResource(ctx.getResources(),R.mipmap.i2));
        list.add(BitmapFactory.decodeResource(ctx.getResources(),R.mipmap.i3));
        list.add(BitmapFactory.decodeResource(ctx.getResources(),R.mipmap.i4));
        ArrayList<String> titles=new ArrayList<>();
        titles.add("标题1");
        titles.add("标题2");
        titles.add("标题3");
        titles.add("标题4");
        banner.setImages(list)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        imageView.setImageBitmap((Bitmap) path);
                    }
                })
                .setBannerAnimation(Transformer.DepthPage)  //动画效果
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)//样式
                .setBannerTitles(titles)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();
    }
}
