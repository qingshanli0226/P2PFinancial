package com.example.month6.view.fragments;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.common.diyviews.baseclass.BaseFragmentNetWork;
import com.example.common.diyviews.presenter.BasePresenter;
import com.example.month6.R;
import com.example.month6.databean.HomeData;
import com.example.month6.presenter.HomePresenter;
import com.example.month6.view.customviews.CustomProgressView;
import com.example.month6.view.customviews.CustomTopView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragmentNetWork<HomeData> {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.proGrossView)
    CustomProgressView proGrossView;
    @BindView(R.id.homeTitleView)
    CustomTopView homeTitleView;

    // 构造 获取viewid 设置P层对象 获取布局id 设置view
    public HomeFragment(Context fragmentContext) {
        super(fragmentContext);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    protected BasePresenter<HomeData> getPresenters() {
        return new HomePresenter();
    }

    @Override
    protected int getViewGroupLayoutId() {
        return R.id.rela;
    }

    @Override
    protected void initView() {
        setBanner();
    }

    @Override
    public void getDataSuccess(HomeData homeData) {
        //解析轮播图数据
        List<HomeData.ImageArr> imageArr = homeData.getImageArr();
        ArrayList<String> imgs = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();
        for (HomeData.ImageArr i : imageArr) {
            imgs.add(i.getIMAURL());
            titles.add(i.getID());
        }
        banner.setImages(imgs)
                .setBannerTitles(titles)
                .start();
        //子线程更新进度
        new Thread(()->{
            proGrossView.reFush(Integer.valueOf(homeData.getProInfo().getProgress()));
        }).start();
    }

    //设置轮播图
    private void setBanner() {
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
                .setIndicatorGravity(BannerConfig.CENTER);
    }

}
