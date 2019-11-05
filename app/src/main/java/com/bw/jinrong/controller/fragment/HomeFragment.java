package com.bw.jinrong.controller.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bw.jinrong.R;
import com.bw.jinrong.controller.bean.Image;
import com.bw.jinrong.controller.bean.Index;
import com.bw.jinrong.controller.bean.Product;
import com.bw.jinrong.controller.common.AppNetConfig;
import com.bw.jinrong.controller.common.BaseFragment;
import com.bw.jinrong.controller.ui.RoundProgress;
import com.loopj.android.http.RequestParams;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

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

//    public HomeFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment initView(inflater.inflate(R.layout.fragment_home, container, false));
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }

    @NotNull
    @Override
    protected RequestParams getParams() {
        return null;
    }

    @NotNull
    @Override
    protected String getUrl() {
//        return null;
        return new AppNetConfig().getBASE_URL();
    }

    private Index index;

    @Override
    protected void initData(@NotNull String content) {

        if (!TextUtils.isEmpty(content)){
            index = new Index();
            //解析json数据:GSON/FASTJSON
            JSONObject jsonObject = JSON.parseObject(content);
            //解析json对象
            String proInfo = jsonObject.getString("proInfo");
            Product product = JSON.parseObject(proInfo, Product.class);
            //解析json数组数据
            String imageArr = jsonObject.getString("imageArr");
            List<Image> images = jsonObject.parseArray(imageArr, Image.class);
            index.product = product;
            index.images = images;

            //更新页面数据
            tv_home_product.setText(product.name);
            tv_home_yearrate.setText(product.yearRate + "%");
            //获取数据中的进度值
            currentProress = Integer.parseInt(index.product.progress);

            //在分线程中，实现进度的动态变化
            new Thread(runnable).start();

            //设置banner样式
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片地址构成的集合
            ArrayList<String> imagesUrl = new ArrayList<String>(index.images.size());

            for (int i = 0;i < index.images.size();i++){
                imagesUrl.add(index.images.get(i).IMAPAURL);
            }
            banner.setImages(imagesUrl);
            //设置banner动画效果
            banner.setBannerAnimation(Transformer.DepthPage);
            //设置标题集合（当banner样式有显示title时）
            String[] titles = new String[]{"分享砍学费","人脉总动员","想不到你是这样的app","购物节，爱不单行"};
            banner.setBannerTitles(Arrays.asList(titles));
            //设置自动轮播，默认为true
            banner.isAutoPlay(true);
            //设置轮播时间
            banner.setDelayTime(1500);
            //设置指示器位置(当banner模式中有指示器时)
            banner.setIndicatorGravity(BannerConfig.CENTER);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }

    }

    @Override
    protected void initTitle() {
        iv_title_back.setVisibility(View.GONE);
        tv_title.setText("首页");
        iv_title_setting.setVisibility(View.GONE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
