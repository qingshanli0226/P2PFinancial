package com.example.p2pfiancial.fragment.homefragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.base.BaseFragment;
import com.example.p2pfiancial.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeFragment extends BaseFragment {

    private ImageView mIvTitleBack;
    private TextView mTvTitle;
    private ImageView mIvTitleSetting;
    private Banner mBanner;

    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mIvTitleBack = view.findViewById(R.id.iv_title_back);
        mTvTitle = view.findViewById(R.id.tv_title);
        mIvTitleSetting = view.findViewById(R.id.iv_title_setting);
        mBanner = view.findViewById(R.id.mBanner);


        //设置banner图片
        ArrayList images = new ArrayList();
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher);

        //设置标题集合
        String[] titles = {"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImages(images)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        imageView.setImageResource((int)path);
                    }
                })
        .setBannerTitles(Arrays.asList(titles))
        .isAutoPlay(true)
        .setDelayTime(1500)
        .setIndicatorGravity(BannerConfig.CENTER)
        .start();
    }

    @Override
    protected void initTopTitle() {
        mIvTitleBack.setVisibility(View.GONE);
        mTvTitle.setText("首页");
        mIvTitleSetting.setVisibility(View.GONE);
    }
}
