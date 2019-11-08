package com.example.month6.view.fragments;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.common.diyviews.baseclass.BaseNetFragment;
import com.example.common.diyviews.presenter.DiyPresenter;
import com.example.month6.R;
import com.example.month6.databean.HomeData;
import com.example.month6.presenter.HomePresenter;
import com.example.month6.view.diyview.ProGrossView;
import com.example.month6.view.diyview.TitleView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFrag extends BaseNetFragment<HomeData> {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.proGrossView)
    ProGrossView proGrossView;
    @BindView(R.id.homeTitleView)
    TitleView homeTitleView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    proGrossView.reFush();
                    break;
            }
        }
    };


    public HomeFrag(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragmenthome;
    }

    @Override
    protected void initView() {
        diyPresenter.getGetData();
    }

    @Override
    protected DiyPresenter<HomeData> getPresenters() {
        return new HomePresenter();
    }

    @Override
    public void setDataSuccess(HomeData homeData) {
        //解析轮播图数据
        List<HomeData.ImageArr> imageArr = homeData.getImageArr();
        ArrayList<String> imgs = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for (HomeData.ImageArr i : imageArr) {
            imgs.add(i.getIMAURL());
            titles.add(i.getID());
        }
        Log.e("xxx", "图片" + imgs.size());
        setBanner(imgs, titles);
        //更新进度到90%
        updateProgress(0.9);
    }

    //设置轮播图
    private void setBanner(ArrayList imgs, ArrayList<String> titles) {
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context)
                        .load((String) path)
                        .into(imageView);
            }
        })
                .setBannerAnimation(Transformer.DepthPage)  //动画效果
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)//样式
                .setIndicatorGravity(BannerConfig.CENTER)
                .setImages(imgs)
                .setBannerTitles(titles)
                .start();
    }

    //进度条滚动线程,自动结束
    private void updateProgress(final double num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (!(proGrossView.num >= (360 * num)));
            }
        }).start();
    }

}
