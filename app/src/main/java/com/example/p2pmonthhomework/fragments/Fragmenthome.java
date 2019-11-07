package com.example.p2pmonthhomework.fragments;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.p2pmonthhomework.HomePresenter;
import com.example.common.MyRoundView;
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

    private int flag = 0;

    private IBasePresenter iBasePresenter;

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
        mbanner.setDelayTime(2000);
        mbanner.isAutoPlay(true);
        mbanner.start();
    }

    @Override
    public void onGetDataSuccess(HomeBean data) {
        Toast.makeText(getContext(), "获取数据成功", Toast.LENGTH_SHORT).show();
        List<HomeBean.ImageArrBean> imageArr = data.getImageArr();
        setBanner(imageArr);
    }

    @Override
    public void onGetDataListSuccess(List<HomeBean> data) {
//        Log.d("LQS", data.toString());
        Toast.makeText(getContext(), "获取数据成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataFailed(String message) {
        Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iBasePresenter.detachView();
    }
}
