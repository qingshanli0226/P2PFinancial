package com.example.p2pmonthhomework.fragments;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.base.BaseFragment;
import com.example.base.IBasePresenter;
import com.example.base.IBaseView;
import com.example.common.ErrorCodes;
import com.example.common.view.MyLoadingPage;
import com.example.p2pmonthhomework.HomePresenter;
import com.example.common.view.MyRoundView;
import com.example.p2pmonthhomework.R;
import com.example.p2pmonthhomework.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends BaseFragment implements IBaseView<HomeBean> {
    Banner mbanner;
    List<String> titles = new ArrayList();
    List<String> images = new ArrayList();
    private MyRoundView roundPro_home;
    private MyLoadingPage mLoadingPage;
    private View layout_home;
    public static int flag = 0;
    private final int HOME_REQUEST_CODE = 100;


    private IBasePresenter iBasePresenter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@NotNull View view) {
        mbanner = view.findViewById(R.id.mbanner);
        roundPro_home = view.findViewById(R.id.roundPro_home);
        mLoadingPage = view.findViewById(R.id.mLoadingPage);
        layout_home = view.findViewById(R.id.layout_home);

        mbanner.setBannerAnimation(Transformer.ZoomOutSlide);
    }

    @Override
    public void initData() {
        iBasePresenter = new HomePresenter();
        iBasePresenter.attachView(this);
        iBasePresenter.getData(HOME_REQUEST_CODE);
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
    public void onGetDataSuccess(int requestCode,HomeBean data) {
       if(requestCode==HOME_REQUEST_CODE){
           List<HomeBean.ImageArrBean> imageArr = data.getImageArr();
           setBanner(imageArr);
           layout_home.setVisibility(View.VISIBLE);

           HomeBean.ProInfoBean proInfo = data.getProInfo();
           setProgress(proInfo);
       }
    }

    private void setProgress(HomeBean.ProInfoBean proInfo) {
        String progress = proInfo.getProgress();
        roundPro_home.setProgress(Integer.parseInt(progress),flag);
        flag = 1;
    }

    @Override
    public void onGetDataListSuccess(int requestCode,List<HomeBean> data) {
    }

    @Override
    public void onGetDataFailed(int requestCode, ErrorCodes codes) {
        if(requestCode==HOME_REQUEST_CODE){
            Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
            mLoadingPage.interruptLoadingAnimation();
            mLoadingPage.setVisibility(View.VISIBLE);
            layout_home.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showLoading(int requestCode) {
        if(requestCode==HOME_REQUEST_CODE){
            mLoadingPage.startLoadingAnimation();
        }
    }

    @Override
    public void hideLoading(int requestCode) {
       if(requestCode==HOME_REQUEST_CODE){
           Log.e("####","停止");
           mLoadingPage.interruptLoadingAnimation();
           mLoadingPage.setLoadingPagedismiss();
       }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iBasePresenter.detachView();
    }



}
